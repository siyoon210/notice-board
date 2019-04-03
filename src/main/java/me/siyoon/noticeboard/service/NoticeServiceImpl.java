package me.siyoon.noticeboard.service;

import lombok.AllArgsConstructor;
import me.siyoon.noticeboard.domain.Notice;
import me.siyoon.noticeboard.domain.enums.PageSize;
import me.siyoon.noticeboard.dto.NoticeForm;
import me.siyoon.noticeboard.repository.NoticeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NoticeServiceImpl implements NoticeService {
    private final NoticeRepository noticeRepository;

    @Override
    public Page<Notice> getNoticePage(Integer startPage) {
        PageRequest pageRequest = PageRequest.of(startPage, PageSize.Notice.getLimit());

        return noticeRepository.findNotices(pageRequest);
    }

    @Override
    public Notice addNotice(NoticeForm noticeForm) {
        return null;
    }

    @Override
    public Notice getNotice(Long id) {
        return noticeRepository.findNoticeById(id);
    }

    @Override
    public Notice modifyNotice(NoticeForm noticeForm) {
        return null;
    }

    @Override
    public void deleteNotice(Long id) {
        noticeRepository.deleteById(id);
    }
}
