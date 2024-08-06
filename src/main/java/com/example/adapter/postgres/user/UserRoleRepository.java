package com.example.adapter.postgres.user;

import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import jakarta.validation.constraints.NotBlank;

import java.util.stream.Stream;

@Repository("postgres")
@JdbcRepository(dialect = Dialect.POSTGRES)
interface UserRoleRepository extends CrudRepository<UserRoleDao, UserRoleId> {
    @Query("""
    SELECT authority FROM role 
    INNER JOIN user_role ON user_role.id_role_id = role.id 
    INNER JOIN "user" user_ ON user_role.id_user_id = user_.id 
    WHERE user_.username = :username""")
    Stream<String> findAllAuthoritiesByUsername(@NotBlank String username);
}
