package com.securewebapp.app.servlet;

import com.securewebapp.app.api.Endpoint;
import com.securewebapp.app.api.Pages;
import com.securewebapp.app.repository.ReservationRepository;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReservationServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(ReservationServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        try {
            List<HashMap<String, Object>> reservationsDetails;
            String userSessionId = req.getRequestedSessionId();

            if(userSessionId != null){
                HttpSession session = req.getSession(false);

                if (session != null && session.getId().equals(userSessionId)) {
                    String userId = (String) session.getAttribute("userId");
                    String csrfToken = (String) session.getAttribute("csrfToken");

                    ReservationRepository reservationRepository = new ReservationRepository();
                    reservationsDetails = reservationRepository
                            .getReservationsDetails(userId);

                    if(reservationsDetails != null){
                        if (!reservationsDetails.isEmpty()){
                            req.setAttribute("csrfToken", csrfToken);
                            req.setAttribute("reservationsDetails", reservationsDetails);
                            req.getRequestDispatcher(Pages.reservation)
                                    .forward(req, res);
                        } else {
                            req.setAttribute("msg", "empty");
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
