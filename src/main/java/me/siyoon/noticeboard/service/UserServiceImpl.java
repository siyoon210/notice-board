package me.siyoon.noticeboard.service;

import lombok.RequiredArgsConstructor;
import me.siyoon.noticeboard.domain.Role;
import me.siyoon.noticeboard.domain.User;
import me.siyoon.noticeboard.domain.enums.Status;
import me.siyoon.noticeboard.dto.UserForm;
import me.siyoon.noticeboard.repository.RoleRepository;
import me.siyoon.noticeboard.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User getUser(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findUserById(id);
    }

    @Override
    public User signUp(UserForm userForm) {
        User user = convertFormToUser(userForm);
        encodePassword(user);
        setRoles(user);

        return userRepository.save(user);
    }

    private User convertFormToUser(UserForm userForm) {
        User user = new User();
        BeanUtils.copyProperties(userForm, user);

        return user;
    }

    private void encodePassword(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }

    private void setRoles(User user) {
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findRoleByStatus(Status.USER));
        user.setRoles(roles);
    }
}
