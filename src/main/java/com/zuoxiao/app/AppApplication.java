package com.zuoxiao.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class AppApplication {

	private static final Logger logger = LoggerFactory.getLogger(AppApplication.class);

	public static void main(String[] args) {
		logger.info("开始启动项目....");
		SpringApplication.run(AppApplication.class, args);
	}

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "jojo") String name) {
		return String.format("Hello %s! 你在干啥呀？", name);
	}

}
