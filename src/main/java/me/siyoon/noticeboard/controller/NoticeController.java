package me.siyoon.noticeboard.controller;

import lombok.RequiredArgsConstructor;
import me.siyoon.noticeboard.domain.Notice;
import me.siyoon.noticeboard.dto.NoticeForm;
import me.siyoon.noticeboard.service.NoticeService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class NoticeController {
    private final NoticeService noticeService;

    @GetMapping("/notices")
    public String getNoticePage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            Model model) {

        Page<Notice> noticePage = noticeService.getNoticePage(page);
        model.addAttribute("noticePage", noticePage);
        return "notices";
    }

    @GetMapping("/edit")
    public String edit() {
        return "edit";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute NoticeForm noticeForm) {
        noticeService.addNotice(noticeForm);
        return "redirect:notices";
    }
}
