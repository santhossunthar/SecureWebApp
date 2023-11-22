package com.securewebapp.app.servlet;

import com.auth0.AuthenticationController;
import com.securewebapp.app.auth.AuthConfig;
import com.securewebapp.app.auth.AuthenticationProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    @Override
    public void doGet(
            HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // URL where the application will receive the authorization code
        String callbackUrl = String.format(
                "%s://%s:%s/callback",
                "http",
                "localhost",
                "8080"
        );

        AuthConfig config = new AuthConfig();
        AuthenticationProvider authenticationProvider = new AuthenticationProvider();
        AuthenticationController authenticationController = authenticationProvider.authenticationController(config);

        // Create the authorization URL to redirect the user to begin the authentication flow.
        String authURL = authenticationController.buildAuthorizeUrl(request, response, callbackUrl)
                .withScope(config.getScope())
                .build();

        response.sendRedirect(authURL);
    }
}
