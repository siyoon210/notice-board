package me.siyoon.noticeboard.controller;

import lombok.RequiredArgsConstructor;
import me.siyoon.noticeboard.domain.Notice;
import me.siyoon.noticeboard.service.NoticeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NoticeContentApiController {
    private final NoticeService noticeService;

    @GetMapping("/api/content/{noticeId}")
    public ResponseEntity getNoticeContent(@PathVariable(value = "noticeId") Long noticeId) {
        Notice notice = noticeService.getNotice(noticeId);
        return ResponseEntity.ok(notice.getNoticeContent().getContent());
    }
}
