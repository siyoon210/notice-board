package me.siyoon.noticeboard.service;

import lombok.RequiredArgsConstructor;
import me.siyoon.noticeboard.domain.Notice;
import me.siyoon.noticeboard.domain.NoticeContent;
import me.siyoon.noticeboard.domain.enums.PageSize;
import me.siyoon.noticeboard.dto.NoticeForm;
import me.siyoon.noticeboard.repository.NoticeContentRepository;
import me.siyoon.noticeboard.repository.NoticeRepository;
import me.siyoon.noticeboard.repository.UserRepository;
import me.siyoon.noticeboard.security.CustomUserDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {
    private final NoticeRepository noticeRepository;
    private final NoticeContentRepository noticeContentRepository;
    private final UserService userService;

    @Override
    public Page<Notice> getNoticePage(Integer startPage) {
        PageRequest pageRequest = PageRequest.of(startPage, PageSize.NOTICE.getLimit());

        return noticeRepository.findNotices(pageRequest);
    }

    @Override
    public Notice addNotice(NoticeForm noticeForm) {
        Notice notice = new Notice();
        notice.setTitle(noticeForm.getTitle());

        //TODO noticeContentServive로 빼기
        NoticeContent noticeContent = new NoticeContent();
        noticeContent.setContent(noticeForm.getContent());

        //TODO noticeContentServive로 빼기
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        notice.setUser(userService.getUser(userDetails.getEmail()));
        notice.setNoticeContent(noticeContentRepository.save(noticeContent));

        return noticeRepository.save(notice);
    }

    @Override
    public Notice getNotice(Long id) {
        return noticeRepository.findNoticeById(id);
    }

    @Override
    public Notice modifyNotice(NoticeForm noticeForm) {
        Notice notice = noticeRepository.findNoticeById(noticeForm.getId());
        notice.setTitle(noticeForm.getTitle());

        return notice;
    }

    @Override
    public void deleteNotice(Long id) {
        noticeRepository.deleteById(id);
    }
}
