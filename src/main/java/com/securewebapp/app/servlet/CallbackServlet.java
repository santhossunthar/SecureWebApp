package com.securewebapp.app.servlet;

import com.auth0.AuthenticationController;
import com.auth0.IdentityVerificationException;
import com.auth0.SessionUtils;
import com.auth0.Tokens;
import com.securewebapp.app.auth.*;
import com.securewebapp.app.api.Endpoint;
import com.securewebapp.app.helper.CSRFTokenGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CallbackServlet extends HttpServlet {
    private AuthenticationController authenticationController;
    private final String loginEndpoint = Endpoint.login;
    private final String reservationEndpoint = Endpoint.reservation;
    private static final Logger logger = Logger.getLogger(CallbackServlet.class.getName());

    @Override
    public void init() throws ServletException {
        AuthConfig configs = new AuthConfig();
        AuthenticationProvider authenticationProvider = new AuthenticationProvider();
        authenticationController = authenticationProvider.authenticationController(configs);
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        handle(req, res);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        handle(req, res);
    }

    private void handle(HttpServletRequest req, HttpServletResponse res) throws IOException {
        try {
            Tokens tokens = authenticationController.handle(req, res);
            SessionUtils.set(req, "accessToken", tokens.getAccessToken());
            SessionUtils.set(req, "idToken", tokens.getIdToken());

            JwtCredential jwtCredential = new JwtCredential(tokens.getIdToken());
            JwtPrincipal jwtPrincipal = jwtCredential.getAuth0JwtPrincipal();
            SessionUtils.set(req, "userId", jwtPrincipal.getName());

            CSRFTokenGenerator csrfTokenGenerator = new CSRFTokenGenerator();
            SessionUtils.set(req, "csrfToken", csrfTokenGenerator.generate());

            res.sendRedirect(reservationEndpoint);
        } catch (IdentityVerificationException | IOException ex) {
            logger.log(Level.SEVERE, "An error occurred: " + ex.getMessage(), ex);
            res.sendRedirect(loginEndpoint);
        }
    }
}