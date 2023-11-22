package com.securewebapp.app.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

import javax.security.enterprise.credential.Credential;

public class JwtCredential implements Credential {
    private final JwtPrincipal jwtPrincipal;

    public JwtCredential(String token) {
        DecodedJWT decodedJWT = JWT.decode(token);
        this.jwtPrincipal = new JwtPrincipal(decodedJWT);
    }

    public JwtPrincipal getAuth0JwtPrincipal() {
        return jwtPrincipal;
    }
}