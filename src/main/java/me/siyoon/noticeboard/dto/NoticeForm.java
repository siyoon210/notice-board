package me.siyoon.noticeboard.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class NoticeForm {
    private Long id;
    @NotBlank
    @Length(min = 1, max = 100)
    private String title;
    @NotBlank
    @Length(min = 1, max = 2000)
    private String content;
}
