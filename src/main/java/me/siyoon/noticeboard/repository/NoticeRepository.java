package me.siyoon.noticeboard.repository;

import me.siyoon.noticeboard.domain.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
    @Query(value = "SELECT n FROM Notice n")
    Page<Notice> findNotices(Pageable pageable);

    Notice findNoticeById(Long id);
}
