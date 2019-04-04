package me.siyoon.noticeboard.controller;

import lombok.RequiredArgsConstructor;
import me.siyoon.noticeboard.domain.Notice;
import me.siyoon.noticeboard.service.NoticeService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/notices")
@RequiredArgsConstructor
public class NoticeController {
    private final NoticeService noticeService;

    @GetMapping
    public String getNoticePage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            Model model) {

        Page<Notice> noticePage = noticeService.getNoticePage(page);
        model.addAttribute("noticePage", noticePage);
        return "notices";
    }
}
