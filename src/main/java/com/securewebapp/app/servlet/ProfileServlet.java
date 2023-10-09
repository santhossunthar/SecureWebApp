package com.securewebapp.app.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String user = (String) req.getSession(true)
                .getAttribute("userId");

        // add the user to the request context and render the JSP
        req.setAttribute("user", user);

        req.getRequestDispatcher("/WEB-INF/jsp/profile.jsp")
                .forward(req, resp);
    }
}
