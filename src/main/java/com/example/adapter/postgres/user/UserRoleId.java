package com.example.adapter.postgres.user;

import io.micronaut.data.annotation.Embeddable;
import io.micronaut.data.annotation.Relation;
import lombok.Getter;

import java.util.Objects;

@Getter
@Embeddable
class UserRoleId {

    @Relation(value = Relation.Kind.MANY_TO_ONE)
    private final UserDao user;

    @Relation(value = Relation.Kind.MANY_TO_ONE)
    private final RoleDao role;

    public UserRoleId(UserDao user, RoleDao role) {
        this.user = user;
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserRoleId userRoleId = (UserRoleId) o;
        return role.id().equals(userRoleId.getRole().id()) &&
                user.id().equals(userRoleId.getUser().id());
    }

    @Override
    public int hashCode() {
        return Objects.hash(role.id(), user.id());
    }

    public UserDao getUser() {
        return user;
    }

    public RoleDao getRole() {
        return role;
    }
}
