package com.securewebapp.app.servlet;

import com.securewebapp.app.auth.AuthConfig;
import com.securewebapp.app.auth.AuthUser;
import com.securewebapp.app.api.Endpoint;
import com.securewebapp.app.api.Pages;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
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

                if (session != null && session.getId().equals(userSessionId)) {
                    String accessToken = (String) session.getAttribute("accessToken");

                    AuthConfig authConfig = new AuthConfig();
                    AuthUser authUser = new AuthUser(authConfig.getDomain(), accessToken);
                    JSONObject userInfoObject = authUser.getInfo();

                    HashMap<String, Object> userInfo = new HashMap<>();
                    userInfo.put("email", userInfoObject.get("email"));
                    userInfo.put("emailVerification", userInfoObject.get("email_verified"));
                    userInfo.put("picture", userInfoObject.get("picture"));
                    userInfo.put("fullName", userInfoObject.get("nickname"));
                    userInfo.put("name", userInfoObject.get("nickname"));

                    req.setAttribute("userInfo", userInfo);
                    req.getRequestDispatcher(Pages.userProfile)
                            .forward(req, res);
                } else {
                    res.sendRedirect(Endpoint.login);
                }
            } else {
                res.sendRedirect(Endpoint.login);
            }
        } catch (ServletException | IOException | JSONException | NullPointerException ex) {
            logger.log(Level.SEVERE, "An error occurred: " + ex.getMessage(), ex);
            res.sendRedirect(Endpoint.root);
        }
    }
}
