package me.siyoon.noticeboard.service;

import me.siyoon.noticeboard.domain.User;
import me.siyoon.noticeboard.dto.UserForm;

public interface UserService {
    User getUser(String email);

    User getUser(Long id);

    User signUp(UserForm userForm);
}
