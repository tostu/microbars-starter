package com.example.domain.entities.user;

import java.util.List;

public interface UserState {
    String getUsernameString();

    String getPasswordString();

    List<String> getAuthorityStringList();

    boolean isEnabled();

    boolean isAccountExpired();

    boolean isAccountLocked();

    boolean isPasswordExpired();
}
