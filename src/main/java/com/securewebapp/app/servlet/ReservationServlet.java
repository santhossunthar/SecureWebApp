package com.securewebapp.app.servlet;

import com.securewebapp.app.db.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ReservationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String user = (String) req.getSession(true)
                .getAttribute("userId");
        req.setAttribute("user", user);
        req.setAttribute("response", null);
        req.getRequestDispatcher("/WEB-INF/jsp/reservation.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String reservationDate = req.getParameter("date");
        String reservationTime = req.getParameter("time");
        String reservationLocation = req.getParameter("location");
        String reservationVehicleNo = req.getParameter("vehicleno");
        String reservationMileage = req.getParameter("mileage");
        String reservationMessage = req.getParameter("message");

        DBConnection dbConnection = new DBConnection();
        if(dbConnection.addReservationDetails(reservationData)){
            req.setAttribute("response", "Added Successfully!");
        } else {
            req.setAttribute("response", "Error occurred!");
        }

        req.getRequestDispatcher("/WEB-INF/jsp/reservation.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
