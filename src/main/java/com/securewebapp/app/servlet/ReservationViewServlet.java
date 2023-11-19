package com.securewebapp.app.servlet;

import com.securewebapp.app.api.Endpoint;
import com.securewebapp.app.api.Pages;
import com.securewebapp.app.repository.ReservationRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReservationViewServlet extends HttpServlet {
    private final String reservationViewPage = Pages.reservationView;
    private final String reservationActionPage = Pages.reservationAction;
    private final String rootPath = Endpoint.root;
    private final String loginEndpoint = Endpoint.login;
    private static final Logger logger = Logger.getLogger(ReservationAddServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        try{
            List<HashMap<String, Object>> reservationsDetails;
            String userSessionId = req.getRequestedSessionId();

            if(userSessionId != null){
                HttpSession session = req.getSession(false);

                if (session != null) {
                    String userId = (String) session.getAttribute("userId");
                    String csrfToken = (String) session.getAttribute("csrfToken");
                    String requestedCsrfToken = req.getParameter("token");

                    if(!csrfToken.equals(requestedCsrfToken)) {
                        req.setAttribute("msg", "error");
                        req.getRequestDispatcher(reservationActionPage)
                                .forward(req, res);
                        return;
                    }

                    String bookingId = req.getParameter("bid");

                    ReservationRepository reservationRepository = new ReservationRepository();
                    reservationsDetails = reservationRepository
                            .getReservationsDetails(userId, bookingId);

                    if(reservationsDetails != null){
                        if (!reservationsDetails.isEmpty()){
                            req.setAttribute("reservationsDetails", reservationsDetails);
                            req.setAttribute("csrfToken", csrfToken);
                            req.getRequestDispatcher(reservationViewPage)
                                    .forward(req, res);
                        } else {
                            req.setAttribute("msg", "empty");
                            req.getRequestDispatcher(reservationActionPage)
                                    .forward(req, res);
                        }
                    } else {
                        req.setAttribute("msg", "error");
                        req.getRequestDispatcher(reservationActionPage)
                                .forward(req, res);
                    }
                } else {
                    res.sendRedirect(loginEndpoint);
                }
            } else {
                res.sendRedirect(loginEndpoint);
            }
        } catch (ServletException | IOException ex){
            logger.log(Level.SEVERE, "An error occurred: " + ex.getMessage(), ex);
            res.sendRedirect(rootPath);
        }
    }
}
