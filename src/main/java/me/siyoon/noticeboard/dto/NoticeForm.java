package me.siyoon.noticeboard.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class NoticeForm {
    @
    private Long id;
    @NotBlank(message = "제목의 길이는 1글자 이상, 100글자 이하로 입력해주세요.")
    @Length(min = 1, max = 100, message = "제목의 길이는 1글자 이상, 100글자 이하로 입력해주세요.")
    private String title;
    @NotBlank(message = "내용의 길이는 1글자 이상, 2000글자 이하로 입력해주세요.")
    @Length(min = 1, max = 2000, message = "내용의 길이는 1글자 이상, 2000글자 이하로 입력해주세요.")
    private String content;
}
