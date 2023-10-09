package com.securewebapp.app.servlet;

import com.securewebapp.app.auth.AuthUser;
import com.securewebapp.app.db.DBConnection;
import com.securewebapp.app.helper.InputValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

        if(reservationDate == null
                || reservationTime == null
                || reservationLocation == null
                || reservationVehicleNo == null
                || reservationMileage == null
                || reservationMessage == null
        ) {
            req.setAttribute("response", "All Fields are mandatory!");
            return;
        }

        InputValidator inputValidator = new InputValidator();
        List<String> postValidatedData = new ArrayList<>();

        if (inputValidator.isValidDate(reservationDate)
                && inputValidator.isNumeric(reservationTime)
                && inputValidator.isAlphanumeric(reservationLocation)
                && inputValidator.isAlphanumeric(reservationVehicleNo)
                && inputValidator.isNumeric(reservationMileage)
                && inputValidator.isAlphanumeric(reservationMessage)
        ) {
            postValidatedData.add(reservationDate);
            postValidatedData.add(reservationTime);
            postValidatedData.add(reservationLocation);
            postValidatedData.add(reservationVehicleNo);
            postValidatedData.add(reservationMileage);
            postValidatedData.add(reservationMessage);


        } else {
            req.setAttribute("response", "Error occurred!");
        }

        DBConnection dbConnection = new DBConnection();
        if(dbConnection.addReservationDetails(postValidatedData)){
            req.setAttribute("response", "Added Successfully!");
        } else {
            req.setAttribute("response", "Error occurred!");
        }

        resp.sendRedirect("/WEB-INF/jsp/reservation.jsp");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
