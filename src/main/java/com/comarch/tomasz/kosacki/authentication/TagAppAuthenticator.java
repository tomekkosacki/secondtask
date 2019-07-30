package com.comarch.tomasz.kosacki.authentication;

import com.comarch.tomasz.kosacki.configuration.TagServiceConfiguration;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class TagAppAuthenticator implements Authenticator<BasicCredentials, AuthUser> {

    private static final Map<String, Set<String>> VALID_USERS = ImmutableMap.of(
            "guest", ImmutableSet.of(),
            "user", ImmutableSet.of("USER"),
            "admin", ImmutableSet.of("ADMIN", "USER")
    );

    private TagServiceConfiguration configuration;

    public TagAppAuthenticator(TagServiceConfiguration configuration) {

        this.configuration = configuration;
    }

    @Override
    public Optional<AuthUser> authenticate(BasicCredentials credentials) throws AuthenticationException {

        if(VALID_USERS.containsKey(credentials.getUsername()) && configuration.getPassword().equals(credentials.getPassword())) {

            return Optional.of(new AuthUser(credentials.getUsername(), VALID_USERS.get(credentials.getUsername())));
        }
        return Optional.empty();
    }
}
