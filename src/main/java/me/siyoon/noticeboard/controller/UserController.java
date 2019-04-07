package me.siyoon.noticeboard.controller;

import lombok.RequiredArgsConstructor;
import me.siyoon.noticeboard.domain.Notice;
import me.siyoon.noticeboard.domain.User;
import me.siyoon.noticeboard.dto.UserForm;
import me.siyoon.noticeboard.service.NoticeService;
import me.siyoon.noticeboard.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public String getSignUpForm() {
        return "signUp";
    }

    @PostMapping
    public String signUp(@Valid @ModelAttribute UserForm userForm) {
        userService.signUp(userForm);
        return "redirect:/session";
    }
}
