package me.siyoon.noticeboard.service;

import lombok.RequiredArgsConstructor;
import me.siyoon.noticeboard.domain.Notice;
import me.siyoon.noticeboard.domain.NoticeContent;
import me.siyoon.noticeboard.domain.User;
import me.siyoon.noticeboard.domain.enums.Authority;
import me.siyoon.noticeboard.domain.enums.PageSizeLimit;
import me.siyoon.noticeboard.dto.NoticeForm;
import me.siyoon.noticeboard.repository.NoticeRepository;
import me.siyoon.noticeboard.security.CustomUserDetails;
import me.siyoon.noticeboard.util.UserDetailsUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {
    private final NoticeRepository noticeRepository;
    private final NoticeContentService noticeContentService;
    private final UserService userService;

    @Override
    @Transactional(readOnly = true)
    public Page<Notice> getNoticePage(Integer startPage) {
        PageRequest pageRequest =
                PageRequest.of(startPage, PageSizeLimit.NOTICE.getContent(),
                        new Sort(Sort.Direction.DESC, "id"));

        return noticeRepository.findNotices(pageRequest);
    }

    @Override
    @Transactional
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
    @Transactional(readOnly = true)
    public Notice getNotice(Long id) {
        return noticeRepository.findNoticeById(id);
    }

    @Override
    @Transactional
    public Notice modifyNotice(NoticeForm noticeForm) {
        Notice notice = this.getNotice(noticeForm.getId());

        if (isValidAuthority(notice.getId())) {
            notice.setTitle(noticeForm.getTitle());
            notice.setNoticeContent(modifyNoticeContent(noticeForm, notice));
        }

        //TODO thorws 권한 없음 익셉션

        return notice;
    }

    private NoticeContent modifyNoticeContent(NoticeForm noticeForm, Notice notice) {
        return noticeContentService.modifyNoticeContent(
                notice.getNoticeContent().getId(), noticeForm.getContent());
    }

    @Override
    @Transactional
    public void deleteNotice(Long id) {
        if (isValidAuthority(id)) {
            noticeRepository.deleteById(id);
            return;
        }

        // TODO 권한검사 throw InValid Autohority
    }

    private boolean isValidAuthority(Long noticeId) {
        Long userId = this.getNotice(noticeId).getUser().getId();
        CustomUserDetails userDetails = UserDetailsUtil.get();

        if (userId.equals(userDetails.getId())) {
            return true;
        }

        return userDetails.hasAuthority(Authority.ADMIN);
    }
}
