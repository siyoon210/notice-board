package me.siyoon.noticeboard.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class UserForm {
    private String email;
    private String password;
    private String nickName;
}
