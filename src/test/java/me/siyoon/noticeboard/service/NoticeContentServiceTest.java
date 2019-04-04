package me.siyoon.noticeboard.service;

import me.siyoon.noticeboard.domain.NoticeContent;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class NoticeContentServiceTest {
    @Autowired
    private NoticeContentService noticeContentService;
    @Autowired
    private EntityManager entityManager;

    @Test
    public void 공지_내용_새로_추가하기() {
        String content = "새로운 내용입니다.";
        NoticeContent noticeContent = noticeContentService.addNoticeContent(content);

        Assert.assertEquals(content, noticeContent.getContent());
    }

    @Test
    public void 공지_한건_조회하기() {
        Long id = 1L;
        NoticeContent noticeContent = noticeContentService.getNoticeContent(id);

        Assert.assertEquals(id, noticeContent.getId());
    }

    @Test
    public void 공지_한건_수정하기() {
        Long id = 1L;
        String content = "수정된 내용이지롱";

        NoticeContent noticeContent1 = noticeContentService.getNoticeContent(id);

        entityManager.flush();

        NoticeContent noticeContent2 = noticeContentService.modifyNoticeContent(id, content);

        Assert.assertEquals(content, noticeContent2.getContent());
    }
}
