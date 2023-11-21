package com.securewebapp.app.servlet;

import com.securewebapp.app.api.Endpoint;
import com.securewebapp.app.api.Pages;
import com.securewebapp.app.helper.InputValidator;
import com.securewebapp.app.repository.ReservationRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReservationDeleteServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(ReservationDeleteServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        try {
            String userSessionId = req.getRequestedSessionId();

            if(userSessionId != null){
                HttpSession session = req.getSession(false);

                if (session != null && session.getId().equals(userSessionId)) {
                    String userId = (String) session.getAttribute("userId");
                    String csrfToken = (String) session.getAttribute("csrfToken");
                    String requestedCsrfToken = req.getParameter("token");

                    if(!csrfToken.equals(requestedCsrfToken)) {
                        req.setAttribute("msg", "error");
                        req.getRequestDispatcher(Pages.reservationAction)
                                .forward(req, res);
                        return;
                    }

                    String bookingId = req.getParameter("bid");

                    if(bookingId != null && InputValidator.isNumeric(bookingId)){
                        ReservationRepository reservationRepository = new ReservationRepository();
                        boolean result = reservationRepository.deleteReservationDetailsById(bookingId, userId);

                        if(!result){
                            req.setAttribute("msg", "error");
                            req.getRequestDispatcher(Pages.reservationAction)
                                    .forward(req, res);
                        }

                        res.sendRedirect(Endpoint.reservation);
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
        } catch (ServletException | IOException | NullPointerException ex){
            logger.log(Level.SEVERE, "An error occurred: " + ex.getMessage(), ex);
            res.sendRedirect(Endpoint.root);
        }
    }
}
