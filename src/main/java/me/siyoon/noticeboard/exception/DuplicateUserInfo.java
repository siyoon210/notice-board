package me.siyoon.noticeboard.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.siyoon.noticeboard.domain.User;

@Getter
@AllArgsConstructor
public class DuplicateUserInfo extends RuntimeException {
    private User user;
    private String duplicationStr;
}