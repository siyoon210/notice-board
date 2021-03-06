package me.siyoon.noticeboard.repository;

import me.siyoon.noticeboard.domain.Notice;
import me.siyoon.noticeboard.domain.NoticeContent;
import me.siyoon.noticeboard.domain.enums.PageSize;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class NoticeRepositoryTest {
    @Autowired
    private NoticeRepository noticeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EntityManager entityManager;

    @Test
    public void 공지사항_목록_한페이지_불러오기() {
        PageRequest of = PageRequest.of(0, PageSize.NOTICE.getContent());

        Page<Notice> notices = noticeRepository.findNotices(of);

        Assert.assertEquals(PageSize.NOTICE.getContent(), Integer.valueOf(notices.getSize()));
    }

    @Test
    public void 공지사항_한건_조회하기() {
        Long id = 1L;
        Notice noticeById = noticeRepository.findNoticeById(id);

        Assert.assertEquals(id, noticeById.getId());
    }

    @Test
    public void 공지사항_한건_추가하기() {
        Notice notice = Notice.builder()
                .title("반갑습니다.")
                .user(userRepository.findUserById(1L))
                .noticeContent(new NoticeContent(1L, "반가워요"))
                .build();

        Notice save = noticeRepository.save(notice);

        Assert.assertEquals(notice.getTitle(), save.getTitle());
    }

    @Test
    public void 공지사항_한건_수정하기() {
        Long id = 1L;
        String newTitle = "잠시 수정 중 입니다.";

        Notice noticeById1 = noticeRepository.findNoticeById(id);
        noticeById1.setTitle(newTitle);

        entityManager.flush();

        Notice noticeById2 = noticeRepository.findNoticeById(id);

        Assert.assertEquals(newTitle, noticeById2.getTitle());
    }

    @Test
    public void 공지사항_한건_삭제하기() {
        List<Notice> 삭제전 = noticeRepository.findAll();

        noticeRepository.deleteById(1L);

        List<Notice> 삭제후 = noticeRepository.findAll();

        Assert.assertEquals(삭제전.size(), 삭제후.size() + 1);
    }

}
