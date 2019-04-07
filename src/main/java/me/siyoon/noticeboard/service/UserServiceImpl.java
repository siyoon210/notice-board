package me.siyoon.noticeboard.service;

import lombok.RequiredArgsConstructor;
import me.siyoon.noticeboard.domain.Role;
import me.siyoon.noticeboard.domain.User;
import me.siyoon.noticeboard.domain.enums.Authority;
import me.siyoon.noticeboard.dto.UserForm;
import me.siyoon.noticeboard.exception.DuplicateUserInfo;
import me.siyoon.noticeboard.repository.RoleRepository;
import me.siyoon.noticeboard.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public User getUser(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUser(Long id) {
        return userRepository.findUserById(id);
    }

    @Override
    @Transactional
    public User signUp(UserForm userForm) {
        User user = convertFormToUser(userForm);
        checkDuplication(user);
        encodePassword(user);
        setRoles(user);

        return userRepository.save(user);
    }

    private User convertFormToUser(UserForm userForm) {
        return User.builder()
                .email(userForm.getEmail())
                .nickName(userForm.getNickName())
                .password(userForm.getPassword())
                .build();
    }

    private void checkDuplication(User user) {
        if (userRepository.findUserByEmail(user.getEmail()) != null) {
            throw new DuplicateUserInfo(user, user.getEmail());
        }

        if (userRepository.findUserByNickName(user.getNickName()) != null) {
            throw new DuplicateUserInfo(user, user.getNickName());
        }
    }

    private void encodePassword(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }

    private void setRoles(User user) {
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findRoleByAuthority(Authority.USER));
        user.setRoles(roles);
    }
}
