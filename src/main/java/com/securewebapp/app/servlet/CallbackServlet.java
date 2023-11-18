package com.securewebapp.app.servlet;

import com.auth0.AuthenticationController;
import com.auth0.IdentityVerificationException;
import com.auth0.SessionUtils;
import com.auth0.Tokens;
import com.securewebapp.app.auth.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class CallbackServlet extends HttpServlet {
    private String redirectOnSuccess;
    private String redirectOnFail;
    private AuthenticationController authenticationController;

    @Override
    public void init() throws ServletException {
        redirectOnSuccess = "/reservation";
        redirectOnFail = "/login";

        AuthConfig configs = new AuthConfig();
        AuthenticationProvider authenticationProvider = new AuthenticationProvider();
        authenticationController = authenticationProvider.authenticationController(configs);
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {
        handle(req, res);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {
        handle(req, res);
    }

    private void handle(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        try {
            Tokens tokens = authenticationController.handle(req, res);
            SessionUtils.set(req, "accessToken", tokens.getAccessToken());
            SessionUtils.set(req, "idToken", tokens.getIdToken());

            JwtCredential jwtCredential = new JwtCredential(tokens.getIdToken());
            JwtPrincipal jwtPrincipal = jwtCredential.getAuth0JwtPrincipal();
            SessionUtils.set(req, "userId", jwtPrincipal.getName());

            res.sendRedirect(redirectOnSuccess);
        } catch (IdentityVerificationException e) {
            res.sendRedirect(redirectOnFail);
        }
    }

}