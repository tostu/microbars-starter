package com.example.adapter.postgres.user;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import jakarta.validation.constraints.NotBlank;

import java.util.Optional;

@Repository("postgres")
@JdbcRepository(dialect = Dialect.POSTGRES)
interface UserRepository extends CrudRepository<UserDao, Long> {
    Optional<UserDao> findByUsername(@NonNull @NotBlank String username);
}
