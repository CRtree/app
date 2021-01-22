package com.zuoxiao.app;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class AppApplicationTests {

	@Autowired
	RedisTemplate<String,String> redisTemplate;

	@Test
	void contextLoads() {
		redisTemplate.opsForValue().set("zuoxiao","11");
		System.out.println(redisTemplate.opsForValue().get("zuoxiao"));
	}

	@Test
	public void listPartion(){
		JSONArray array = new JSONArray();
		JSONObject a = new JSONObject();
		a.put("1","1111");
		array.add(a);
		JSONObject b = new JSONObject();
		b.put("2",3456L);
		array.add(b);
		JSONObject c = new JSONObject();
		c.put("3",11111L);
		array.add(c);
		JSONObject d = new JSONObject();
		d.put("4","adhasodh");
		array.add(d);
		JSONObject e = new JSONObject();
		e.put("5","qaz");
		array.add(e);
		JSONArray tmpArray = new JSONArray();
		List<JSONArray> tmpList = new ArrayList<>();
		int batchCount = 2;
		for (int i = 0; i < array.size(); i++) {
			tmpArray.add(array.get(i));
			if ((i + 1) % batchCount == 0) {
				JSONArray t = new JSONArray();
				t.addAll(tmpArray);
				tmpList.add(t);
				tmpArray = new JSONArray();
				continue;
			}
			if (i == array.size() - 1) {
				tmpList.add(tmpArray);
			}
		}
		tmpList.size();

	}

}
