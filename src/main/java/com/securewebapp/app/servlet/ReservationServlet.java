package com.securewebapp.app.servlet;

import com.securewebapp.app.auth.JwtCredential;
import com.securewebapp.app.auth.JwtPrincipal;
import com.securewebapp.app.repository.ReservRepository;
import com.securewebapp.app.helper.InputValidator;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;

public class ReservationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String userSessionId = req.getRequestedSessionId();

        if(userSessionId != null) {
            HttpSession session = req.getSession(false);

            if (session != null) {
                req.getRequestDispatcher("/WEB-INF/jsp/reservation.jsp")
                        .forward(req, resp);
            } else {
                resp.sendRedirect("/login");
            }
        } else {
            resp.sendRedirect("/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            String userSessionId = req.getRequestedSessionId();

            if(userSessionId != null) {
                HttpSession session = req.getSession(false);

                if (session != null) {
                    String reservationDate = req.getParameter("date");
                    String reservationTime = req.getParameter("time");
                    String reservationLocation = req.getParameter("location");
                    String reservationVehicleNo = req.getParameter("vehicleno");
                    String reservationMileage = req.getParameter("mileage");
                    String reservationMessage = req.getParameter("message");

                    if(reservationDate == null
                            || reservationTime == null
                            || reservationLocation == null
                            || reservationVehicleNo == null
                            || reservationMileage == null
                            || reservationMessage == null
                    ) {
                        req.setAttribute("msg", "failed");
                        req.getRequestDispatcher("/WEB-INF/jsp/reservation_post_action.jsp")
                                .forward(req, resp);
                        return;
                    }

                    HashMap<String, String> postValidatedData = new HashMap<>();
                    if (InputValidator.isValidDate(reservationDate)
                            && InputValidator.isNumeric(reservationTime)
                            && InputValidator.isAlphanumeric(reservationLocation)
                            && InputValidator.isAlphanumeric(reservationVehicleNo)
                            && InputValidator.isNumeric(reservationMileage)
                            && InputValidator.isAlphanumeric(reservationMessage)
                    ) {
                        postValidatedData.put("reservationDate", reservationDate);
                        postValidatedData.put("reservationTime", reservationTime);
                        postValidatedData.put("reservationLocation", reservationLocation);
                        postValidatedData.put("reservationVehicleNo", reservationVehicleNo);
                        postValidatedData.put("reservationMileage", reservationMileage);
                        postValidatedData.put("reservationMessage", reservationMessage);

                        String idToken;
                        Cookie[] cookies = req.getCookies();
                        if (cookies != null) {
                            for (Cookie cookie : cookies) {
                                if ("token".equals(cookie.getName())) {
                                    idToken = cookie.getValue();
                                    JwtCredential jwtCredential = new JwtCredential(idToken);
                                    JwtPrincipal jwtPrincipal = jwtCredential.getAuth0JwtPrincipal();
                                    postValidatedData.put("userName", jwtPrincipal.getName());
                                    break;
                                }
                            }
                        } else {
                            req.setAttribute("msg", "failed");
                            req.getRequestDispatcher("/WEB-INF/jsp/reservation_post_action.jsp")
                                    .forward(req, resp);
                            return;
                        }
                    } else {
                        req.setAttribute("msg", "failed");
                        req.getRequestDispatcher("/WEB-INF/jsp/reservation_post_action.jsp")
                                .forward(req, resp);
                        return;
                    }

                    ReservRepository reservRepository = new ReservRepository();
                    if(reservRepository.addReservationDetails(postValidatedData)){
                        req.setAttribute("msg", "success");
                        req.getRequestDispatcher("/WEB-INF/jsp/reservation_post_action.jsp")
                                .forward(req, resp);
                    } else {
                        req.setAttribute("msg", "failed");
                        req.getRequestDispatcher("/WEB-INF/jsp/reservation_post_action.jsp")
                                .forward(req, resp);
                    }
                } else {
                    resp.sendRedirect("/login");
                }
            } else {
                resp.sendRedirect("/login");
            }
        }catch (Exception ex){
            throw new ServletException();
        }
    }
}
