package com.securewebapp.app.servlet;

import com.auth0.IdentityVerificationException;
import com.securewebapp.app.api.Endpoint;
import com.securewebapp.app.auth.AuthConfig;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LogoutServlet extends HttpServlet {
    private AuthConfig config;
    private final String rootPath = Endpoint.root;
    private static final Logger logger = Logger.getLogger(CallbackServlet.class.getName());

    @Override
    protected void doGet(
            final HttpServletRequest req,
            final HttpServletResponse res) throws ServletException, IOException {
        try{
            clearSession(req);
            res.sendRedirect(getLogoutUrl(req));
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "An error occurred: " + ex.getMessage(), ex);
            res.sendRedirect(rootPath);
        }
    }

    private void clearSession(HttpServletRequest request) {
        if (request.getSession() != null) {
            request.getSession().invalidate();
        }
    }

    private String getLogoutUrl(HttpServletRequest request) {
        String returnUrl = String.format("%s://%s", request.getScheme(), request.getServerName());
        int port = request.getServerPort();
        String scheme = request.getScheme();

        if (("http".equals(scheme) && port != 80) ||
                ("https".equals(scheme) && port != 443)) {
            returnUrl += ":" + port;
        }

        returnUrl += "/";

        config = new AuthConfig();
        String logoutUrl = String.format(
                "https://%s/v2/logout?client_id=%s&returnTo=%s",
                config.getDomain(),
                config.getClientId(),
                returnUrl
        );

        return logoutUrl;
    }
}
