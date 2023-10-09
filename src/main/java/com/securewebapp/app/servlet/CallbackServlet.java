package com.securewebapp.app.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.securewebapp.app.auth.TokenHandler;

public class CallbackServlet extends HttpServlet {
    private Algorithm algorithm = Algorithm.HMAC256("BcmIMR3Un8s1Q_dwwIU3S-n4RZx2X4aNkAJxCP_8eDp0wgYKjlhr3m-dynHbT2-C");
    private JWTVerifier verifier = JWT.require(algorithm).build();
    private String idToken = null;

    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        String code = request.getParameter("code");

        // Getting ID Token
        TokenHandler tokenHandler = new TokenHandler();
        idToken = tokenHandler.exchangeCodeForIdToken(code);

        // Verify ID Token
        try {
            DecodedJWT jwt = verifier.verify(idToken);

            // Check if the JWT contains required claims and is not expired
            if (jwt.getClaims().containsKey("sub")) {
                String userId = jwt.getClaim("sid").asString();

                // Create a session and store user information
                HttpSession session = request.getSession(true);
                session.setAttribute("userId", userId);
                response.sendRedirect("/dashboard");
            } else {
                response.sendRedirect("index.jsp?error=AuthenticationFailed");
            }
        } catch (JWTVerificationException e) {
            response.sendRedirect("index.jsp?error=AuthenticationError");
        }
    }
}