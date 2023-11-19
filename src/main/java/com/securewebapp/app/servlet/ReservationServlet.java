package com.securewebapp.app.servlet;

import com.securewebapp.app.api.Endpoint;
import com.securewebapp.app.api.Pages;
import com.securewebapp.app.repository.ReservationRepository;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class ReservationServlet extends HttpServlet {
    private final String reservationPage = Pages.reservation;
    private final String reservationActionPage = Pages.reservationAction;
    private final String loginEndpoint = Endpoint.login;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<HashMap<String, Object>> reservationsDetails;
        String userSessionId = req.getRequestedSessionId();

        if(userSessionId != null){
            HttpSession session = req.getSession(false);

            if (session != null) {
                String userId = (String) session.getAttribute("userId");
                String csrfToken = (String) session.getAttribute("csrfToken");

                ReservationRepository reservationRepository = new ReservationRepository();
                reservationsDetails = reservationRepository
                        .getReservationsDetails(userId);

                if(reservationsDetails != null){
                    if (!reservationsDetails.isEmpty()){
                        req.setAttribute("csrfToken", csrfToken);
                        req.setAttribute("reservationsDetails", reservationsDetails);
                        req.getRequestDispatcher(reservationPage)
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
