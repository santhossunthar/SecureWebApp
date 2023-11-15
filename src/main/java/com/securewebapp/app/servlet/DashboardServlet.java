package com.securewebapp.app.servlet;

import com.auth0.SessionUtils;
import com.securewebapp.app.auth.JwtCredential;
import com.securewebapp.app.auth.JwtPrincipal;
import com.securewebapp.app.repository.ReservRepository;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class DashboardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<HashMap<String, Object>> reservationsDetails = null;

        String userSessionId = req.getRequestedSessionId();

        if(userSessionId != null){
            HttpSession session = req.getSession(false);

            if (session != null) {
                String userId = (String) session.getAttribute("userId");
                System.out.println(userId);
                ReservRepository reservRepository = new ReservRepository();
                reservationsDetails = reservRepository
                        .getReservationsDetails(userId);
            }
        }

        if(reservationsDetails == null){
            req.setAttribute("msg", "empty");
            req.getRequestDispatcher("/WEB-INF/jsp/dashboard_action.jsp")
                    .forward(req, resp);
            return;
        }

        req.setAttribute("reservationsDetails", reservationsDetails);
        req.getRequestDispatcher("/WEB-INF/jsp/dashboard.jsp")
                .forward(req, resp);
    }
}
