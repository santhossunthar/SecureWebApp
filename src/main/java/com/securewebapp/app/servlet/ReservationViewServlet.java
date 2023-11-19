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

public class ReservationViewServlet extends HttpServlet {
    private final String reservationViewPage = Pages.reservationView;
    private final String reservationActionPage = Pages.reservationAction;
    private final String loginEndpoint = Endpoint.login;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
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
                            .forward(req, resp);
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
                                .forward(req, resp);
                    } else {
                        req.setAttribute("msg", "empty");
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
    }
}
