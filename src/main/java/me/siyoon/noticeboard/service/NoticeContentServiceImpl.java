package me.siyoon.noticeboard.service;

import me.siyoon.noticeboard.domain.Notice;
import me.siyoon.noticeboard.dto.NoticeForm;
import org.springframework.data.domain.Page;

public interface NoticeContentService {
    Notice addNoticeContent(String content);

    Notice getNoticeContent(Long id);

    Notice modifyNoticeContent(String content);
}
