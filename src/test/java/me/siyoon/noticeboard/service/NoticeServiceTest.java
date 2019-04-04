package me.siyoon.noticeboard.service;

import me.siyoon.noticeboard.domain.Notice;
import me.siyoon.noticeboard.dto.NoticeForm;
import me.siyoon.noticeboard.repository.NoticeRepository;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class NoticeServiceTest {
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private EntityManager entityManager;

    @Test
    public void 공지_한건_인덱스ID로_조회하기() {
        Long id = 1L;
        Notice notice = noticeService.getNotice(id);

        Assert.assertEquals(id, notice.getId());
    }

    @Test
    public void 공지_한페이지_불러오기() {
        int page = 0;
        Page<Notice> noticePage = noticeService.getNoticePage(0);

        Assert.assertEquals(page, noticePage.getPageable().getPageNumber());
    }

    @Test
    public void 공지_한건_추가하기() {
        NoticeForm noticeForm = new NoticeForm();
        noticeForm.setTitle("새로운 공지사항입니다!");
        noticeForm.setContent("내용 확인도 잘하세요");

        Page<Notice> 추가전 = noticeService.getNoticePage(0);
        noticeService.addNotice(noticeForm);
        Page<Notice> 추가후 = noticeService.getNoticePage(0);

        Assert.assertEquals(추가전.getTotalElements(), 추가후.getTotalElements() - 1);
    }

    @Test
    public void 지_한건_수정하기() {
        Long id = 1L;

        NoticeForm noticeForm = new NoticeForm();
        noticeForm.setId(id);
        noticeForm.setTitle("잠시 제목 수정 하겠습니다");
        noticeForm.setContent("내용도 수정 하겠습니다");

        Notice notice1 = noticeService.modifyNotice(noticeForm);

        entityManager.flush();

        Notice notice2 = noticeService.getNotice(id);

        Assert.assertEquals(notice1.getTitle(), notice2.getTitle());
    }

    @Test
    public void 공지_한건_삭제하기() {
        Long id = 1L;
        noticeService.deleteNotice(id);

        Assert.assertNull(noticeService.getNotice(id));
    }


}
