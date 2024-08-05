package com.example.adapter.postgres.user;

import io.micronaut.data.annotation.EmbeddedId;
import io.micronaut.data.annotation.MappedEntity;

@MappedEntity
class UserRoleDao {

    @EmbeddedId
    private final UserRoleId id;

    public UserRoleDao(UserRoleId id) {
        this.id = id;
    }

    public UserRoleId getId() {
        return id;
    }
}
