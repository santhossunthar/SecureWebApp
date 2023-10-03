package com.securewebapp.app.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // get the currently logged in user
        String user = (String) request.getSession(true)
                .getAttribute("userId");

        // add the user to the request context and render the JSP
        request.setAttribute("user", user);

        request.getRequestDispatcher("/WEB-INF/jsp/dashboard.jsp")
                .forward(request, response);
    }
}
