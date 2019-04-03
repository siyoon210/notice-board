package me.siyoon.noticeboard.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PageSize {
    Notice(10);

    private Integer limit;
}
