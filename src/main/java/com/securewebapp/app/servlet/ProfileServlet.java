package com.securewebapp.app.servlet;

import com.securewebapp.app.auth.AuthUser;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String user = (String) req.getSession(true)
                .getAttribute("userId");
        req.setAttribute("user", user);

        String accessToken = null;

        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("accessToken".equals(cookie.getName())) {
                    accessToken = cookie.getValue();
                    break;
                }
            }
        }
        AuthUser authUser = new AuthUser();
        List<HashMap<String, String>> userInfo = authUser.getInfo(accessToken);
        System.out.println(userInfo);

        req.getRequestDispatcher("/WEB-INF/jsp/profile.jsp")
                .forward(req, resp);
    }
}
