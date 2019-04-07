package me.siyoon.noticeboard.util;

import me.siyoon.noticeboard.domain.enums.PageSize;
import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.Map;

public final class PageNavigationUtil {
    public static Map<String, Integer> getNavMap(Page<?> page, PageSize pageSize) {
        Integer pageNavCount = pageSize.getPageNavCount();
        Integer pageNumber = page.getNumber();

        Integer startPageNum = calcStartPageNum(pageNavCount, pageNumber);
        Integer lastPageNum = calcLastPageNum(page, pageNavCount, startPageNum);

        Map<String, Integer> pageNav = new HashMap<>();
        pageNav.put("startPageNum", startPageNum);
        pageNav.put("lastPageNum", lastPageNum);
        pageNav.put("previousPaging", calcPreviousPaging(startPageNum));
        pageNav.put("nextPaging", calcNextPaging(page, lastPageNum));

        return pageNav;
    }

    private static Integer calcNextPaging(Page<?> page, Integer lastPageNum) {
        return (lastPageNum + 1) <= (page.getTotalPages() - 1) ? (lastPageNum + 1) : null;
    }

    private static Integer calcPreviousPaging(Integer startPageNum) {
        return (startPageNum - 1) >= 0 ? (startPageNum - 1) : null;
    }

    private static int calcLastPageNum(Page<?> page, Integer pageNavCount, Integer startPageNum) {
        return Math.min(startPageNum + pageNavCount - 1, page.getTotalPages() - 1);
    }

    private static int calcStartPageNum(Integer pageNavCount, Integer pageNumber) {
        return (pageNumber / pageNavCount) * pageNavCount;
    }
}
