package com.example.adapter.postgres.user;

import io.micronaut.data.annotation.EmbeddedId;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;

@Getter
@Serdeable
@MappedEntity("user_role")
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
