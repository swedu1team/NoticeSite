package com.NotificationSite.NotificationSite.controller;

import com.NotificationSite.NotificationSite.entity.Notice;
import com.NotificationSite.NotificationSite.entity.SiteUser;
import com.NotificationSite.NotificationSite.service.NoticeService;
import com.NotificationSite.NotificationSite.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RequestMapping("/notice")
@RequiredArgsConstructor
@Controller
public class NoticeController {

    private final NoticeService noticeService;
    private final UserService userService;

    @GetMapping("/list") //
    public String noticeList(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
        Page<Notice> paging = this.noticeService.getList(page);
        model.addAttribute("paging", paging);
        return "notice_list";
    }

    @PreAuthorize("isAuthenticated()")
    //공지사항 작성 html과 연결
    @GetMapping("/noticewrite")
    public String noticeWriteForm() {
        return "noticewrite";
    }

    //공지사항 작성 기능
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/noticewritepro")
    public String noticeWritePro(Notice notice, Principal principal) {
        SiteUser siteUser = this.userService.getUser(principal.getName());
        this.noticeService.write(notice, siteUser);
        return "redirect:/notice/list";
    }

    //공지사항 상세
    @GetMapping("/noticeview")
    public String noticeView(Model model, Integer id){
        model.addAttribute("Notice",noticeService.noticeView(id));
        return "noticeview";
    }

    //공지사항 수정
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/noticemodify/{id}")
    public String noticeModify(Model model, @PathVariable("id") Integer id, Principal principal){
        Notice notice = noticeService.noticeView(id);
        if(!notice.getSiteUser().getUsername().equals(principal.getName())) {
            return "redirect:/notice/list";  //수정권한이 없으면 list로 이동
        }

        model.addAttribute("Notice",noticeService.noticeView(id));
        return "noticemodify";
    }

    //공지사항 수정내용 업데이트
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/noticeupdate/{id}")
    public String noticeUpdate(Notice notice, @PathVariable("id") Integer id, Principal principal){

        //수정된 내용 업데이트
        Notice temp = noticeService.noticeView(id);
        temp.setContent(notice.getContent());
        temp.setMeetDay(notice.getMeetDay());
        temp.setMeetPlace(notice.getMeetPlace());
        temp.setMeetSubject(notice.getMeetSubject());

        noticeService.modiwrite(temp);

        return "redirect:/notice/list";
    }

    //공지사항 삭제
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/noticedelete/{id}")
    public String noticeDelete(@PathVariable("id") Integer id, Principal principal){
        Notice notice = this.noticeService.noticeView(id);

        if(!notice.getSiteUser().getUsername().equals(principal.getName())) {
            return "redirect:/notice/list";  //수정권한이 없으면 list로 이동
        }

        this.noticeService.delete(notice);
        return "redirect:/notice/list";
    }
}
