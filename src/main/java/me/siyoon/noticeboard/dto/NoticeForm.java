package me.siyoon.noticeboard.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoticeForm {
    private Long id;
    private String title;
    private String content;
}
