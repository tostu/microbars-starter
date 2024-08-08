package com.example.adapter.passive.user;

import com.example.domain.entities.user.User;
import com.example.domain.entities.user.UserAlreadyExistsException;
import com.example.domain.entities.user.UserService;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Optional;

@Singleton
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;

    UserServiceImpl(
            UserRepository userRepository,
            RoleRepository roleRepository,
            UserRoleRepository userRoleRepository
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userRoleRepository = userRoleRepository;
    }


    @Override
    public Optional<User> findUserByUsername(User.Username username) {
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

    @Override
    public void createUser(User user) {

        List<String> authorityList = user.getAuthorityList().stream().map(User.Authority::value).toList();

        if (userRepository.findByUsername(user.getUsername().value()).isPresent()) {
            throw new UserAlreadyExistsException();
        }

        var userDao = userRepository.save(createUserDao(user));
        if (userDao != null) {
            authorityList.forEach(authority -> {
                var roleDao = roleRepository.findByAuthority(authority).orElseGet(() -> roleRepository.save(authority));
                var userRoleId = new UserRoleId(userDao, roleDao);
                if (userRoleRepository.findById(userRoleId).isEmpty()){
                    userRoleRepository.save(new UserRoleDao(userRoleId));
                }
            });
        }
    }


    private UserDao createUserDao(User user) {
        return new UserDao(
                null,
                user.getUsername().value(),
                user.getPassword().value(),
                user.isEnabled(),
                user.isAccountExpired(),
                user.isAccountLocked(),
                user.isPasswordExpired()
        );
    }

}
