package com.example.adapter.postgres.user;

import com.example.domain.entities.user.UserState;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Serdeable
@MappedEntity("user")
record UserDao(
    @Id
    @Nullable
    @GeneratedValue
    Long id,
    @NotBlank
    String username,
    @NotBlank
    String password,
    boolean enabled,
    boolean accountExpired,
    boolean accountLocked,
    boolean passwordExpired
) { }
