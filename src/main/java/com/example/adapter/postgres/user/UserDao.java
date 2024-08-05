package com.example.adapter.postgres.user;

import com.example.domain.entities.user.UserState;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import jakarta.validation.constraints.NotBlank;

@MappedEntity
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
) implements UserState {

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public boolean isAccountExpired() {
        return accountExpired;
    }

    @Override
    public boolean isAccountLocked() {
        return accountLocked;
    }

    @Override
    public boolean isPasswordExpired() {
        return false;
    }
}
