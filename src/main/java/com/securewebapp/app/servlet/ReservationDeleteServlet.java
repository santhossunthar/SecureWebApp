package com.securewebapp.app.servlet;

import com.securewebapp.app.repository.ReservationRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ReservationDeleteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String userSessionId = req.getRequestedSessionId();

        if(userSessionId != null){
            HttpSession session = req.getSession(false);

            if (session != null) {
                String userId = (String) session.getAttribute("userId");
                String csrfToken = (String) session.getAttribute("csrfToken");
                String requestedCsrfToken = req.getParameter("token");

                if(!csrfToken.equals(requestedCsrfToken)) {
                    req.setAttribute("msg", "error");
                    req.getRequestDispatcher("/WEB-INF/jsp/reservation_action.jsp")
                            .forward(req, resp);
                    return;
                }

                String bookingId = req.getParameter("bid");

                ReservationRepository reservationRepository = new ReservationRepository();
                boolean result = reservationRepository.deleteReservationDetailsById(bookingId, userId);

                if(!result){
                    req.setAttribute("msg", "error");
                    req.getRequestDispatcher("/WEB-INF/jsp/reservation_action.jsp")
                            .forward(req, resp);
                }

                resp.sendRedirect("/reservation");
            } else {
                resp.sendRedirect("/login");
            }
        } else {
            resp.sendRedirect("/login");
        }
    }
}
