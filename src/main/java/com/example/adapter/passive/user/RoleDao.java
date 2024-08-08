package com.example.adapter.passive.user;

import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;

@Serdeable
@MappedEntity("role")
record RoleDao(
        @Id
        @Nullable
        @GeneratedValue
        Long id,
        @NotBlank
        String authority
) {
}
