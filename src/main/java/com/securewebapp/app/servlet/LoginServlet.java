package com.securewebapp.app.servlet;

import com.auth0.AuthenticationController;
import com.securewebapp.app.api.Endpoint;
import com.securewebapp.app.auth.AuthConfig;
import com.securewebapp.app.auth.AuthenticationProvider;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(LoginServlet.class.getName());

    @Override
    public void doGet(
            HttpServletRequest request, HttpServletResponse res) throws IOException {
        try {
            String callbackUrl = String.format(
                    "%s://%s:%s/callback",
                    "http",
                    "localhost",
                    "8080"
            );

            AuthConfig config = new AuthConfig();
            AuthenticationProvider authenticationProvider = new AuthenticationProvider();
            AuthenticationController authenticationController = authenticationProvider.authenticationController(config);
            String authURL = authenticationController.buildAuthorizeUrl(request, res, callbackUrl)
                    .withScope(config.getScope())
                    .build();

            res.sendRedirect(authURL);
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "An error occurred: " + ex.getMessage(), ex);
            res.sendRedirect(Endpoint.root);
        }
    }
}
