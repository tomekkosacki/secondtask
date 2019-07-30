package com.comarch.tomasz.kosacki.authentication;

import java.security.Principal;
import java.util.Set;

public class AuthUser implements Principal {

    private final String name;

    private final Set<String> roles;

    public AuthUser(String name) {
        this.name = name;
        this.roles = null;
    }

    public AuthUser(String name, Set<String> roles) {
        this.name = name;
        this.roles = roles;
    }

    @Override
    public String getName() {
        return name;
    }

    public Set<String> getRoles() {
        return roles;
    }
}
