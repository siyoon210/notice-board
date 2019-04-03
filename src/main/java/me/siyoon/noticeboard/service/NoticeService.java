package me.siyoon.noticeboard.service;

import me.siyoon.noticeboard.domain.Notice;
import org.springframework.data.domain.Page;

public interface NoticeService {
    Page<Notice> getNoticePage(int startPage);

    Notice addNotice(NoticeForm noticeForm);

    Notice getNotice();

    Notice modifyNotice();

    void deleteNotice();
}
