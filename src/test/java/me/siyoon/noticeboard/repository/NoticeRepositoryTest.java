package me.siyoon.noticeboard.repository;

import me.siyoon.noticeboard.domain.Notice;
import me.siyoon.noticeboard.domain.NoticeContent;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;


@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class NoticeRepositoryTest {
    @Autowired
    private NoticeRepository noticeRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void test1_공지사항_목록_한페이지_불러오기() {
        // TODO 아 좀 이상해 구려
        PageRequest of = PageRequest.of(0, 10);

        Page<Notice> notices = noticeRepository.findNotices(of);
        List<Notice> collect = notices.get().collect(Collectors.toList());

        Assert.assertEquals(collect.size(), 1);
    }

    @Test
    public void test2_공지사항_한건_조회하기() {
        Long id = 1L;
        Notice noticeById = noticeRepository.findNoticeById(id);

        Assert.assertEquals(id, noticeById.getId());
    }

    @Test
    public void test3_공지사항_한건_추가하기() {
        Notice notice = Notice.builder()
                .title("반갑습니다.")
                .user(userRepository.findUserById(1L))
                .noticeContent(new NoticeContent(1L, "반가워요"))
                .build();

        Notice save = noticeRepository.save(notice);

        Assert.assertEquals(notice.getTitle(), save.getTitle());
    }

    @Test
    public void test4_공지사항_한건_수정하기() {
        Long id = 1L;
        String newTitle = "잠시 수정 중 입니다.";

        Notice noticeById1 = noticeRepository.findNoticeById(id);
        noticeById1.setTitle(newTitle);
        noticeRepository.saveAndFlush(noticeById1);

        Notice noticeById2 = noticeRepository.findNoticeById(id);

        Assert.assertEquals(newTitle, noticeById2.getTitle());
    }

    @Test
    public void test5_공지사항_한건_삭제하기() {
        List<Notice> 삭제전 = noticeRepository.findAll();

        noticeRepository.deleteById(1L);

        List<Notice> 삭제후 = noticeRepository.findAll();

        Assert.assertEquals(삭제전.size(), 삭제후.size() + 1);
    }

}
