package com.example.domain.entities.user;

public interface UserState {
    String getUsername();

    String getPassword();

    boolean isEnabled();

    boolean isAccountExpired();

    boolean isAccountLocked();

    boolean isPasswordExpired();
}
