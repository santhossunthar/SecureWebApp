package com.securewebapp.app.auth;

import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;

@ApplicationScoped
public class JwtIdentityStore implements IdentityStore {
    @Override
    public CredentialValidationResult validate(final Credential credential) {
        CredentialValidationResult result = CredentialValidationResult.NOT_VALIDATED_RESULT;
        if (credential instanceof JwtCredential) {
            JwtCredential jwtCredential = (JwtCredential) credential;
            result = new CredentialValidationResult(jwtCredential.getAuth0JwtPrincipal());
        }
        return result;
    }
}
