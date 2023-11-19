package com.securewebapp.app.servlet;

import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.securewebapp.app.auth.AuthConfig;
import com.securewebapp.app.auth.AuthUser;
import com.securewebapp.app.api.Endpoint;
import com.securewebapp.app.api.Pages;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProfileServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(ProfileServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        try {
            String userSessionId = req.getRequestedSessionId();

            if(userSessionId != null){
                HttpSession session = req.getSession(false);

                if (session != null) {
                    String accessToken = (String) session.getAttribute("accessToken");

                    AuthConfig authConfig = new AuthConfig();
                    AuthUser authUser = new AuthUser(authConfig.getDomain(), accessToken);
                    JsonNode userInfo = authUser.getInfo();

                    req.setAttribute("userInfo", userInfo);
                    req.getRequestDispatcher(Pages.userProfile)
                            .forward(req, res);
                } else {
                    res.sendRedirect(Endpoint.login);
                }
            } else {
                res.sendRedirect(Endpoint.login);
            }
        } catch (ServletException | IOException | UnirestException ex) {
            logger.log(Level.SEVERE, "An error occurred: " + ex.getMessage(), ex);
            res.sendRedirect(Endpoint.root);
        }
    }
}
