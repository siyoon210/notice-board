package me.siyoon.noticeboard.repository;

import me.siyoon.noticeboard.domain.NoticeContent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeContentRepository extends JpaRepository<NoticeContent, Long> {
}
