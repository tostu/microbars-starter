package com.example.adapter.postgres.user;

import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;

@MappedEntity
record RoleDao(
        @Id
        @Nullable
        @GeneratedValue
        Long id,
        @NotBlank
        String authority
) {
}
