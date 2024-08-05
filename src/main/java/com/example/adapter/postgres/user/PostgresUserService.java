package com.example.adapter.postgres.user;

import com.example.domain.entities.user.User;
import com.example.domain.entities.user.UserService;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Optional;

@Singleton
public class PostgresUserService implements UserService {

    @Inject
    UserRepository userRepository;

    @Inject
    UserRoleRepository userRoleRepository;

    @Override
    public Optional<User> findByUsername(User.Username username) {

        List<User.Authority> authorityList = userRoleRepository.findAllAuthoritiesByUsername(username.value()).map(User.Authority::new).toList();

        return userRepository.findByUsername(username.value()).map(userDao -> new User(
                new User.Username(userDao.username()),
                new User.Password(userDao.password()),
                authorityList,
                userDao.enabled(),
                userDao.accountExpired(),
                userDao.accountLocked(),
                userDao.accountExpired()
        ));
    }
}
