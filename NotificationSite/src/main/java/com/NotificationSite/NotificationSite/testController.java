package com.NotificationSite.NotificationSite;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class testController {
    @GetMapping("/test")
    @ResponseBody
    public String testCode() {
        return "카카오 Oauth test";
    }
}
