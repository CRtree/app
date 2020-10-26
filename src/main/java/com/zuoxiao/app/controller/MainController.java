package com.zuoxiao.app.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * TODO
 *
 * @author zuoxiao
 * @date 2020/10/26 14:33
 */
@Controller
public class MainController {

    @Value("${motd}")
    private String motd;

    @GetMapping("/pageOne")
    public String getPageOne(){
        return "pageOne";
    }

    @ModelAttribute("motd")
    public String message() {
        return motd;
    }
}
