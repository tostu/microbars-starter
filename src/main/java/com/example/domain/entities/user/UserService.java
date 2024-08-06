package com.example.domain.entities.user;

import io.micronaut.core.annotation.NonNull;
import jakarta.validation.constraints.NotBlank;

import java.util.Optional;

public interface UserService {
    Optional<User> findUserByUsername(@NonNull @NotBlank User.Username username);
    void createUser(@NonNull @NotBlank User user);
}
