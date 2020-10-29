package com.zuoxiao.app.controller;

import lombok.extern.log4j.Log4j2;
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
@Log4j2
@Controller
public class MainController {

    @Value("${motd}")
    private String motd;

    @GetMapping("/pageOne")
    public String getPageOne(){
        log.info("into page one");
        log.debug("into page one");
        log.warn("into page one");
        log.error("into page one");
        return "pageOne";
    }

    @GetMapping("/pageTwo")
    public String getPageTwo(){
        log.info("into page Two");
        log.debug("into page Two");
        log.warn("into page Two");
        log.error("into page Two");
        return "pageTwo";
    }

    @ModelAttribute("motd")
    public String message() {
        log.info("into message");
        log.debug("into message");
        log.warn("into message");
        log.error("into message");
        return motd;
    }
}
