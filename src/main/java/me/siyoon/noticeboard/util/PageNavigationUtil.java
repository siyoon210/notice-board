package me.siyoon.noticeboard.util;

import me.siyoon.noticeboard.domain.enums.PageSize;
import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.Map;

public final class PageNavigationUtil {
    public static Map<String, Integer> getNavMap(Page<?> page, PageSize pageSize) {
        Integer pageNavCount = pageSize.getPageNavCount();
        Integer pageNumber = page.getNumber();

        Integer startPageNum = getStartPageNum(pageNavCount, pageNumber);
        Integer lastPageNum = getLastPageNum(page, pageNavCount, startPageNum);

        Map<String, Integer> pageNav = new HashMap<>();
        pageNav.put("startPageNum", startPageNum);
        pageNav.put("lastPageNum", lastPageNum);
        pageNav.put("previousPaging", getPreviousPaging(startPageNum));
        pageNav.put("nextPaging", getNextPaging(page, lastPageNum));

        return pageNav;
    }

    private static Integer getNextPaging(Page<?> page, Integer lastPageNum) {
        return (lastPageNum + 1) <= (page.getTotalPages() - 1) ? (lastPageNum + 1) : null;
    }

    private static Integer getPreviousPaging(Integer startPageNum) {
        return (startPageNum - 1) >= 0 ? (startPageNum - 1) : null;
    }

    private static int getLastPageNum(Page<?> page, Integer pageNavCount, Integer startPageNum) {
        return Math.min(startPageNum + pageNavCount - 1, page.getTotalPages() - 1);
    }

    private static int getStartPageNum(Integer pageNavCount, Integer pageNumber) {
        return (pageNumber / pageNavCount) * pageNavCount;
    }
}
