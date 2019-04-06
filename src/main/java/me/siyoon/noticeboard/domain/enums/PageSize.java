package me.siyoon.noticeboard.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PageSize {
    NOTICE(2, 2);

    private Integer content; //한페이지에 출력할 글의 갯수
    private Integer pageNavCount; //페이징 네비게이션에서 출력할 페이지의 갯수
}
