package com.example.adapter.passive.user;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;

import java.util.Optional;

@Repository("postgres")
@JdbcRepository(dialect = Dialect.POSTGRES)
interface RoleRepository extends CrudRepository<RoleDao, Long> {
    RoleDao save(String authority);
    Optional<RoleDao> findByAuthority(String authority);
}
