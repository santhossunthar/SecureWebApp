package com.securewebapp.app.servlet;

import com.securewebapp.app.db.DBConnection;

import javax.servlet.ServletException;
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
        String user = (String) req.getSession(true)
                .getAttribute("userId");
        req.setAttribute("user", user);

        DBConnection dbConnection = new DBConnection();
        List<HashMap<String, Object>> reservationsDetails = dbConnection.getReservationsDetails();

        req.setAttribute("reservationsDetails", reservationsDetails);
        req.getRequestDispatcher("/WEB-INF/jsp/dashboard.jsp")
                .forward(req, resp);
    }
}
