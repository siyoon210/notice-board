package me.siyoon.noticeboard.controller;

import lombok.RequiredArgsConstructor;
import me.siyoon.noticeboard.domain.Notice;
import me.siyoon.noticeboard.domain.enums.PageSizeLimit;
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

        Integer pageCount = PageSizeLimit.NOTICE.getPageCount();
        Integer pageNumber = noticePage.getNumber();

        Integer startPageNum = (pageNumber / pageCount) * pageCount;
        Integer pagingLimit = Math.min(startPageNum + pageCount - 1, noticePage.getTotalPages() - 1);

        //기본적인건 됐고 다음 페이지 이전페이지로 이동하는 거 구현해야함
        Integer previousPaging = null;
        if (startPageNum - 1 >= 0) {
            previousPaging = startPageNum - 1;
        }

        Integer nextPaging = null;
        if (pagingLimit + 1 <= noticePage.getTotalPages() - 1) {
            nextPaging = pagingLimit + 1;
        }

        model.addAttribute("pagingLimit", pagingLimit);
        model.addAttribute("startPageNum", startPageNum);
        model.addAttribute("previousPaging", previousPaging);
        model.addAttribute("nextPaging", nextPaging);
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
    public String getEditForm() {
        return "edit";
    }

    @GetMapping("/edit/{id}")
    public String getEditForm(@PathVariable(value = "id") Long id, Model model) {
        Notice notice = noticeService.getNotice(id);
        model.addAttribute("notice", notice);
        return "edit";
    }
}
