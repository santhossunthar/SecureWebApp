package com.securewebapp.app.servlet;

import com.securewebapp.app.auth.JwtCredential;
import com.securewebapp.app.auth.JwtPrincipal;
import com.securewebapp.app.repository.ReservRepository;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class DashboardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String idToken;
        List<HashMap<String, Object>> reservationsDetails = null;
        Cookie[] cookies = req.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    idToken = cookie.getValue();
                    JwtCredential jwtCredential = new JwtCredential(idToken);
                    JwtPrincipal jwtPrincipal = jwtCredential.getAuth0JwtPrincipal();
                    ReservRepository reservRepository = new ReservRepository();
                    reservationsDetails = reservRepository
                            .getReservationsDetails(jwtPrincipal.getName());
                    break;
                }
            }
        }

        req.setAttribute("reservationsDetails", reservationsDetails);
        req.getRequestDispatcher("/WEB-INF/jsp/dashboard.jsp")
                .forward(req, resp);
    }
}
