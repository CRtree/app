package com.zuoxiao.app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class AppApplicationTests {

	@Autowired
	RedisTemplate<String,String> redisTemplate;

	@Test
	void contextLoads() {
		redisTemplate.opsForValue().set("zuoxiao","11");
		System.out.println(redisTemplate.opsForValue().get("zuoxiao"));
	}

}
