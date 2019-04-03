package me.siyoon.noticeboard.repository;

import me.siyoon.noticeboard.domain.NoticeContent;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class NoticeContentRepositoryTest {
    @Autowired
    NoticeContentRepository noticeContentRepository;

    @Test
    public void test1_공지_내용_한건_조회하기() {
        Long id = 1L;
        NoticeContent noticeContentById = noticeContentRepository.findNoticeContentById(id);

        Assert.assertEquals(id, noticeContentById.getId());
    }

    @Test
    public void test2_공지_내용_한건_추가하기() {
        NoticeContent noticeContent = NoticeContent.builder()
                .content("공지사항의 내용입니다.")
                .build();

        NoticeContent save = noticeContentRepository.save(noticeContent);

        Assert.assertEquals(noticeContent.getContent(), save.getContent());
    }

    @Test
    public void test3_공지_내용_한건_수정하기() {
        Long id = 1L;
        String newContent = "잠시 수정 중 입니다.";

        NoticeContent noticeContentById = noticeContentRepository.findNoticeContentById(id);
        noticeContentById.setContent(newContent);
        noticeContentRepository.saveAndFlush(noticeContentById);

        NoticeContent noticeContentById2 = noticeContentRepository.findNoticeContentById(id);

        Assert.assertEquals(newContent, noticeContentById2.getContent());
    }

}
