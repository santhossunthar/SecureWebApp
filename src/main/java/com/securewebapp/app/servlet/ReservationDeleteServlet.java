package com.securewebapp.app.servlet;

import com.securewebapp.app.repository.ReservRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ReservationDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String bookingId = req.getParameter("bid");

        ReservRepository reservRepository = new ReservRepository();
        boolean result = reservRepository.deleteReservationDetailsById(bookingId);

        if(!result){
            req.setAttribute("msg", "error");
            req.getRequestDispatcher("/WEB-INF/jsp/dashboard_action.jsp")
                    .forward(req, resp);
        }

        resp.sendRedirect("/dashboard");
    }
}