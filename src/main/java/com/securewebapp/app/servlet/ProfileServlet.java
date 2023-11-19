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
    private final String rootPath = Endpoint.root;
    private final String profilePage = Pages.userProfile;
    private final String loginEndpoint = Endpoint.login;
    private static final Logger logger = Logger.getLogger(ProfileServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        try {
            String userSessionId = req.getRequestedSessionId();

            if(userSessionId != null){
                HttpSession session = req.getSession(false);

                if (session != null) {
                    String userId = (String) session.getAttribute("userId");
                    String accessToken = (String) session.getAttribute("accessToken");

                    AuthConfig authConfig = new AuthConfig();
                    AuthUser authUser = new AuthUser(authConfig.getDomain(), accessToken);
                    JsonNode userInfo = authUser.getInfo();

                    req.setAttribute("userInfo", userInfo);
                    req.getRequestDispatcher(profilePage)
                            .forward(req, res);
                } else {
                    res.sendRedirect(loginEndpoint);
                }
            } else {
                res.sendRedirect(loginEndpoint);
            }
        } catch (ServletException | IOException | UnirestException ex) {
            logger.log(Level.SEVERE, "An error occurred: " + ex.getMessage(), ex);
            res.sendRedirect(rootPath);
        }
    }
}
