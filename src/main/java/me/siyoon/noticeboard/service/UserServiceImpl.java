package me.siyoon.noticeboard.service;

import lombok.RequiredArgsConstructor;
import me.siyoon.noticeboard.domain.User;
import me.siyoon.noticeboard.dto.UserForm;
import me.siyoon.noticeboard.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

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
        User user = new User();
        BeanUtils.copyProperties(userForm, user);

        return userRepository.save(user);
    }
}
