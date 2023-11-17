package com.securewebapp.app.servlet;

import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.securewebapp.app.auth.AuthConfig;
import com.securewebapp.app.auth.AuthUser;
import org.json.JSONArray;

import javax.json.Json;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Iterator;

public class ProfileServlet extends HttpServlet {
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
                    System.out.println(userInfo);

                    req.setAttribute("userInfo", userInfo);
                    req.getRequestDispatcher("/WEB-INF/jsp/profile.jsp")
                            .forward(req, resp);
                } else {
                    resp.sendRedirect("/login");
                }
            } else {
                resp.sendRedirect("/login");
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }
}
