package com.comarch.tomasz.kosacki.authentication;

import io.dropwizard.auth.Authorizer;

public class TagAppAuthorizer implements Authorizer<AuthUser> {


    @Override
    public boolean authorize(AuthUser principal, String role) {
        return principal.getRoles() != null && principal.getRoles().contains(role);
    }
}
