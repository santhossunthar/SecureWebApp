package com.securewebapp.app.servlet;

import com.mashape.unirest.http.JsonNode;
import com.securewebapp.app.auth.AuthConfig;
import com.securewebapp.app.auth.AuthUser;
import com.securewebapp.app.api.Endpoint;
import com.securewebapp.app.api.Pages;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class ProfileServlet extends HttpServlet {
    private final String profilePage = Pages.userProfile;
    private final String loginEndpoint = Endpoint.login;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
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
                            .forward(req, resp);
                } else {
                    resp.sendRedirect(loginEndpoint);
                }
            } else {
                resp.sendRedirect(loginEndpoint);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }
}
