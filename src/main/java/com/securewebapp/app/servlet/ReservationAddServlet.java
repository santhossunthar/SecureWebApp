package com.securewebapp.app.servlet;

import com.securewebapp.app.api.Endpoint;
import com.securewebapp.app.api.Pages;
import com.securewebapp.app.repository.ReservationRepository;
import com.securewebapp.app.helper.InputValidator;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReservationAddServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(ReservationAddServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        try{
            String userSessionId = req.getRequestedSessionId();

            if (userSessionId != null) {
                HttpSession session = req.getSession(false);

                if (session != null) {
                    String csrfToken = (String) session.getAttribute("csrfToken");

                    req.setAttribute("csrfToken", csrfToken);
                    req.getRequestDispatcher(Pages.reservationAdd)
                            .forward(req, res);
                } else {
                    res.sendRedirect(Endpoint.login);
                }
            } else {
                res.sendRedirect(Endpoint.login);
            }
        }catch (ServletException | IOException ex){
            logger.log(Level.SEVERE, "An error occurred: " + ex.getMessage(), ex);
            res.sendRedirect(Endpoint.root);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        try {
            String userSessionId = req.getRequestedSessionId();

            if (userSessionId != null) {
                HttpSession session = req.getSession(false);

                if (session != null && session.getId().equals(userSessionId)) {
                    String userId = (String) session.getAttribute("userId");
                    String csrfToken = (String) session.getAttribute("csrfToken");
                    String requestedCsrfToken = req.getParameter("token");

                    if (!csrfToken.equals(requestedCsrfToken)) {
                        req.setAttribute("msg", "error");
                        req.getRequestDispatcher(Pages.reservationAction)
                                .forward(req, res);
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
                                req.getRequestDispatcher(Pages.reservationAction)
                                        .forward(req, res);
                            } else {
                                req.setAttribute("msg", "error");
                                req.getRequestDispatcher(Pages.reservationAction)
                                        .forward(req, res);
                            }
                        } else {
                            req.setAttribute("msg", "error");
                            req.getRequestDispatcher(Pages.reservationAction)
                                    .forward(req, res);
                        }
                    } else {
                        req.setAttribute("msg", "error");
                        req.getRequestDispatcher(Pages.reservationAction)
                                .forward(req, res);
                    }
                } else {
                    res.sendRedirect(Endpoint.login);
                }
            } else {
                res.sendRedirect(Endpoint.login);
            }
        } catch (ServletException | IOException | NullPointerException ex) {
            logger.log(Level.SEVERE, "An error occurred: " + ex.getMessage(), ex);
            res.sendRedirect(Endpoint.root);
        }
    }
}
