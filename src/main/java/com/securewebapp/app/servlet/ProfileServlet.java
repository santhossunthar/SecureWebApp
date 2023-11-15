package com.securewebapp.app.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;

public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String userSessionId = req.getRequestedSessionId();

        if(userSessionId != null){
            HttpSession session = req.getSession(false);

            if (session != null) {
                String userId = (String) session.getAttribute("userId");

                req.getRequestDispatcher("/WEB-INF/jsp/profile.jsp")
                        .forward(req, resp);
            } else {
                resp.sendRedirect("/login");
            }
        } else {
            resp.sendRedirect("/login");
        }
    }
}
