package com.securewebapp.app.auth;

import com.auth0.jwt.interfaces.DecodedJWT;

import javax.security.enterprise.CallerPrincipal;

public class JwtPrincipal extends CallerPrincipal {
    private final DecodedJWT idToken;

    public JwtPrincipal(DecodedJWT idToken) {
        super(idToken.getClaim("name").asString());
        this.idToken = idToken;
    }

    public DecodedJWT getIdToken() {
        return this.idToken;
    }
}
