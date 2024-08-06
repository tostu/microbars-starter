package com.example.domain.entities.user;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class User {
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

    public record Username(@NotNull @NotEmpty String value) {}
    public record Password(@NotNull @NotEmpty String value) {}
    public record Authority(@NotNull @NotEmpty String value) {}



}
