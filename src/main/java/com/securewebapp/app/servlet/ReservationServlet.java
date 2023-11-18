package com.securewebapp.app.servlet;

import com.securewebapp.app.repository.ReservationRepository;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class ReservationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<HashMap<String, Object>> reservationsDetails;
        String userSessionId = req.getRequestedSessionId();

        if(userSessionId != null){
            HttpSession session = req.getSession(false);

            if (session != null) {
                String userId = (String) session.getAttribute("userId");
                ReservationRepository reservationRepository = new ReservationRepository();
                reservationsDetails = reservationRepository
                        .getReservationsDetails(userId);

                if(reservationsDetails != null){
                    if (!reservationsDetails.isEmpty()){
                        req.setAttribute("reservationsDetails", reservationsDetails);
                        req.getRequestDispatcher("/WEB-INF/jsp/reservation.jsp")
                                .forward(req, resp);
                    } else {
                        req.setAttribute("msg", "empty");
                        req.getRequestDispatcher("/WEB-INF/jsp/reservation_action.jsp")
                                .forward(req, resp);
                    }
                } else {
                    req.setAttribute("msg", "error");
                    req.getRequestDispatcher("/WEB-INF/jsp/reservation_action.jsp")
                            .forward(req, resp);
                }
            } else {
                resp.sendRedirect("/login");
            }
        } else {
            resp.sendRedirect("/login");
        }
    }
}
