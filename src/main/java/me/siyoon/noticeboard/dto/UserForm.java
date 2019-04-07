package me.siyoon.noticeboard.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserForm {
    @Email
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    @Length(min = 1, max = 12)
    private String nickName;
}
