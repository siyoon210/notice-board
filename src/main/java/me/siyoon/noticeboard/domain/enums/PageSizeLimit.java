package me.siyoon.noticeboard.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PageSizeLimit {
    NOTICE(1, 5);

    private Integer content;
    private Integer pageCount;
}
