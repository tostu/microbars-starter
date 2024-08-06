package com.example.application;

import com.example.domain.entities.auth.PasswordEncoder;
import com.example.domain.entities.user.User;
import com.example.domain.entities.user.UserService;
import jakarta.annotation.Nullable;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Singleton
public class RegisterService {
    private static final boolean DEFAULT_ENABLED = true;
    private static final boolean DEFAULT_ACCOUNT_EXPIRED = false;
    private static final boolean DEFAULT_ACCOUNT_LOCKED = false;
    private static final boolean DEFAULT_PASSWORD_EXPIRED = false;

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public RegisterService(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(
            @NotBlank String username,
            @NotBlank String rawPassword
    ) {
        register(username, rawPassword, Collections.emptyList());
    }

    @Transactional
    public void register(
            @NotBlank String username,
            @NotBlank String rawPassword,
            @Nullable List<String> authorities
    ) {

        final String encodedPassword = passwordEncoder.encode(rawPassword);

        var user = new User(
                new User.Username(username),
                new User.Password(encodedPassword),
                Optional.ofNullable(authorities).orElse(Collections.emptyList()).stream().map(User.Authority::new).toList(),
                DEFAULT_ENABLED,
                DEFAULT_ACCOUNT_EXPIRED,
                DEFAULT_ACCOUNT_LOCKED,
                DEFAULT_PASSWORD_EXPIRED
        );

       userService.createUser(user);
    }
}
