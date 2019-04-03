package me.siyoon.noticeboard.service;

import me.siyoon.noticeboard.domain.Notice;
import me.siyoon.noticeboard.dto.NoticeForm;
import org.springframework.data.domain.Page;

public interface NoticeService {
    Page<Notice> getNoticePage(Integer startPage);

    Notice addNotice(NoticeForm noticeForm);

    Notice getNotice(Long id);

    Notice modifyNotice(NoticeForm noticeForm);

    void deleteNotice(Long id);
}
