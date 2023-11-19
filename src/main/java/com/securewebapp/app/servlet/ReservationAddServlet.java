package com.securewebapp.app.servlet;

import com.securewebapp.app.api.Endpoint;
import com.securewebapp.app.api.Pages;
import com.securewebapp.app.repository.ReservationRepository;
import com.securewebapp.app.helper.InputValidator;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;

public class ReservationAddServlet extends HttpServlet {
    private final String reservationAddPage = Pages.reservationAdd;
    private final String reservationActionPage = Pages.reservationAction;
    private final String loginEndpoint = Endpoint.login;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String userSessionId = req.getRequestedSessionId();

        if (userSessionId != null) {
            HttpSession session = req.getSession(false);

            if (session != null) {
                String csrfToken = (String) session.getAttribute("csrfToken");

                req.setAttribute("csrfToken", csrfToken);
                req.getRequestDispatcher(reservationAddPage)
                        .forward(req, resp);
            } else {
                resp.sendRedirect(loginEndpoint);
            }
        } else {
            resp.sendRedirect(loginEndpoint);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            String userSessionId = req.getRequestedSessionId();

            if (userSessionId != null) {
                HttpSession session = req.getSession(false);

                if (session != null) {
                    String userId = (String) session.getAttribute("userId");
                    String csrfToken = (String) session.getAttribute("csrfToken");
                    String requestedCsrfToken = req.getParameter("token");

                    if (!csrfToken.equals(requestedCsrfToken)) {
                        req.setAttribute("msg", "error");
                        req.getRequestDispatcher(reservationActionPage)
                                .forward(req, resp);
                        return;
                    }

                    String reservationDate = req.getParameter("date");
                    String reservationTime = req.getParameter("time");
                    String reservationLocation = req.getParameter("location");
                    String reservationVehicleNo = req.getParameter("vehicleno");
                    String reservationMileage = req.getParameter("mileage");
                    String reservationMessage = req.getParameter("message");

                    if (reservationDate != null
                            && reservationTime != null
                            && reservationLocation != null
                            && reservationVehicleNo != null
                            && reservationMileage != null
                            && reservationMessage != null
                    ) {
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
                            postValidatedData.put("userName", userId);

                            ReservationRepository reservationRepository = new ReservationRepository();
                            if (reservationRepository.addReservationDetails(postValidatedData)) {
                                req.setAttribute("msg", "success");
                                req.getRequestDispatcher(reservationActionPage)
                                        .forward(req, resp);
                            } else {
                                req.setAttribute("msg", "error");
                                req.getRequestDispatcher(reservationActionPage)
                                        .forward(req, resp);
                            }
                        } else {
                            req.setAttribute("msg", "error");
                            req.getRequestDispatcher(reservationActionPage)
                                    .forward(req, resp);
                        }
                    } else {
                        req.setAttribute("msg", "error");
                        req.getRequestDispatcher(reservationActionPage)
                                .forward(req, resp);
                    }
                } else {
                    resp.sendRedirect(loginEndpoint);
                }
            } else {
                resp.sendRedirect(loginEndpoint);
            }
        } catch (Exception ex) {
            throw new ServletException();
        }
    }
}
