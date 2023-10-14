package com.securewebapp.app.auth;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Produces;

import com.auth0.jwk.JwkProvider;
import com.auth0.jwk.JwkProviderBuilder;
import com.auth0.AuthenticationController;

@ApplicationScoped
public class AuthenticationProvider {
    @Produces
    public AuthenticationController authenticationController(AuthConfig config) {
        JwkProvider jwkProvider = new JwkProviderBuilder(config.getDomain()).build();
        return AuthenticationController.newBuilder(
                        config.getDomain(),
                        config.getClientId(),
                        config.getClientSecret())
                .withJwkProvider(jwkProvider)
                .build();
    }
}
