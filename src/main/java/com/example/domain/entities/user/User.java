package com.example.domain.entities.user;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class User implements UserState {
    @NotNull
    Username username;
    @NotNull
    Password password;
    @NotNull
    List<Authority> authorityList;
    private boolean enabled;
    private boolean accountExpired;
    private boolean accountLocked;
    private boolean passwordExpired;

    @Override
    public String getUsername() {
        return this.username.value();
    }

    @Override
    public String getPassword() {
        return this.password.value();
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    @Override
    public boolean isAccountExpired() {
        return this.accountExpired;
    }

    @Override
    public boolean isAccountLocked() {
        return this.accountLocked;
    }

    @Override
    public boolean isPasswordExpired() {
        return this.passwordExpired;
    }


    public record Username(@NotNull @NotEmpty String value) {}
    public record Password(@NotNull @NotEmpty String value) {}
    public record Authority(@NotNull @NotEmpty String value) {}



}
