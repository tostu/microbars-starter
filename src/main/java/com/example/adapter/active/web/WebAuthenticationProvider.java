package com.example.adapter.active.web;

import com.example.domain.entities.auth.PasswordEncoder;
import com.example.domain.entities.user.User;
import com.example.domain.entities.user.UserService;
import com.example.domain.entities.user.UserState;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.http.HttpRequest;
import io.micronaut.security.authentication.AuthenticationFailed;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import io.micronaut.security.authentication.provider.HttpRequestAuthenticationProvider;
import jakarta.inject.Singleton;

import java.util.List;

import static io.micronaut.security.authentication.AuthenticationFailureReason.*;

@Singleton
public class WebAuthenticationProvider<B> implements HttpRequestAuthenticationProvider<B> {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public WebAuthenticationProvider(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthenticationResponse authenticate(
            @Nullable HttpRequest<B> httpRequest,
            @NonNull AuthenticationRequest<String, String> authenticationRequest
    ) {

        UserState user = fetchUserState(authenticationRequest);
        AuthenticationFailed authenticationFailed = validate(user, authenticationRequest);
        if (authenticationFailed != null) {
            return AuthenticationResponse.failure(authenticationFailed.getReason());
        }
        else {
            return createSuccessfulAuthenticationResponse(user);
        }
    }

    private AuthenticationFailed validate(UserState user, AuthenticationRequest<?, ?> authenticationRequest) {
        AuthenticationFailed authenticationFailed = null;
        if (user == null) {
            authenticationFailed = new AuthenticationFailed(USER_NOT_FOUND);

        } else if (!user.isEnabled()) {
            authenticationFailed = new AuthenticationFailed(USER_DISABLED);

        } else if (user.isAccountExpired()) {
            authenticationFailed = new AuthenticationFailed(ACCOUNT_EXPIRED);

        } else if (user.isAccountLocked()) {
            authenticationFailed = new AuthenticationFailed(ACCOUNT_LOCKED);

        } else if (user.isPasswordExpired()) {
            authenticationFailed = new AuthenticationFailed(PASSWORD_EXPIRED);

        } else if (!passwordEncoder.matches(authenticationRequest.getSecret().toString(), user.getPasswordString())) {
            authenticationFailed = new AuthenticationFailed(CREDENTIALS_DO_NOT_MATCH);
        }

        return authenticationFailed;
    }

    private UserState fetchUserState(AuthenticationRequest<?, ?> authRequest) {
        final Object username = authRequest.getIdentity();
        return userService.findUserByUsername(new User.Username(username.toString())).orElse(null);
    }

    private AuthenticationResponse createSuccessfulAuthenticationResponse(UserState user) {
        List<String> authorities = user.getAuthorityStringList();
        return AuthenticationResponse.success(user.getUsernameString(), authorities);
    }

}
