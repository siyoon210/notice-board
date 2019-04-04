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

    @PostMapping("/notices")
    public String addNotice(@ModelAttribute NoticeForm noticeForm) {
        noticeService.addNotice(noticeForm);
        return "redirect:/notices";
    }

    @DeleteMapping("/notices/{id}")
    public String deleteNotice(@PathVariable(value = "id") Long id) {
        noticeService.deleteNotice(id);
        return "redirect:/notices";
    }

    @PutMapping("/notices")
    public String modifyNotice(@ModelAttribute NoticeForm noticeForm) {
        noticeService.modifyNotice(noticeForm);
        return "redirect:/notices";
    }

    @GetMapping("/edit")
    public String edit() {
        return "edit";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable(value = "id") Long id, Model model) {
        Notice notice = noticeService.getNotice(id);
        model.addAttribute("notice", notice);
        return "edit";
    }
}
