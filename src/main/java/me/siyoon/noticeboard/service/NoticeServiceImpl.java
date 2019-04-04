package me.siyoon.noticeboard.service;

import lombok.RequiredArgsConstructor;
import me.siyoon.noticeboard.domain.Notice;
import me.siyoon.noticeboard.domain.NoticeContent;
import me.siyoon.noticeboard.domain.User;
import me.siyoon.noticeboard.domain.enums.Authority;
import me.siyoon.noticeboard.domain.enums.PageSize;
import me.siyoon.noticeboard.dto.NoticeForm;
import me.siyoon.noticeboard.repository.NoticeRepository;
import me.siyoon.noticeboard.security.CustomUserDetails;
import me.siyoon.noticeboard.util.UserDetailsUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {
    private final NoticeRepository noticeRepository;
    private final NoticeContentService noticeContentService;
    private final UserService userService;

    @Override
    public Page<Notice> getNoticePage(Integer startPage) {
        PageRequest pageRequest = PageRequest.of(startPage, PageSize.NOTICE.getLimit());

        return noticeRepository.findNotices(pageRequest);
    }

    @Override
    public Notice addNotice(NoticeForm noticeForm) {
        Notice notice = convertFormToNotice(noticeForm);

        return noticeRepository.save(notice);
    }

    private Notice convertFormToNotice(NoticeForm noticeForm) {
        NoticeContent noticeContent = noticeContentService.addNoticeContent(noticeForm.getContent());

        CustomUserDetails userDetails = UserDetailsUtil.get();
        User user = userService.getUser(userDetails.getEmail());

        return Notice.builder()
                .title(noticeForm.getTitle())
                .noticeContent(noticeContent)
                .user(user)
                .build();
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
        if (isValidAuthority(id)) {
            noticeRepository.deleteById(id);
            return;
        }

        // TODO 권한검사 throw InValid Autohority
    }

    private boolean isValidAuthority(Long id) {
        Long userId = this.getNotice(id).getUser().getId();
        CustomUserDetails userDetails = UserDetailsUtil.get();

        if (userId.equals(userDetails.getId())) {
            return true;
        }

        return userDetails.hasAuthority(Authority.ADMIN);
    }
}
