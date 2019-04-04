package me.siyoon.noticeboard.service;

import lombok.RequiredArgsConstructor;
import me.siyoon.noticeboard.domain.NoticeContent;
import me.siyoon.noticeboard.repository.NoticeContentRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoticeContentServiceImpl implements NoticeContentService {
    private final NoticeContentRepository noticeContentRepository;

    @Override
    public NoticeContent addNoticeContent(String content) {
        NoticeContent noticeContent = NoticeContent.builder()
                .content(content)
                .build();

        return noticeContentRepository.save(noticeContent);
    }

    @Override
    public NoticeContent getNoticeContent(Long id) {
        return noticeContentRepository.findNoticeContentById(id);
    }

    @Override
    public NoticeContent modifyNoticeContent(Long id, String content) {
        NoticeContent noticeContent = noticeContentRepository.findNoticeContentById(id);
        noticeContent.setContent(content);
        return noticeContent;
    }
}
