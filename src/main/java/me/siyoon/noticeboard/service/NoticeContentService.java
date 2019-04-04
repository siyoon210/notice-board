package me.siyoon.noticeboard.service;

import me.siyoon.noticeboard.domain.NoticeContent;

public interface NoticeContentService {
    NoticeContent addNoticeContent(String content);

    NoticeContent getNoticeContent(Long id);

    NoticeContent modifyNoticeContent(Long id, String content);
}
