package me.siyoon.noticeboard.repository;

import me.siyoon.noticeboard.domain.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Integer> {
}
