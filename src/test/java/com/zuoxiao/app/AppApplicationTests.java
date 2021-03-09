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

	@Test
	public void bitTest(){
		int a = 255;
		output(a);
	}

	private void output(int a){
		if(a == 0){
			return;
		}
		output(a>>1);
		int t = a & 1;
		System.out.print(t);
	}

	@Test
	public void maze() {
		int[][] matrix = {
				{1, 2, 3, 4, 13},
				{5, 6, 7, 8, 14},
				{9, 10, 11, 12, 15},
				{16, 17, 18, 19, 20}};
		int start = 0;
		while (matrix.length > start * 2 && matrix[0].length > start * 2) {
			printMaze(start, matrix);
			start++;
		}
	}

	private void printMaze(int start, int[][] matrix) {
		int endX = matrix[0].length - 1 - start;//列
		int endY = matrix.length - 1 - start;//行
		//从左到右
		for (int i = start; i <= endX; i++) {
			System.out.print(matrix[start][i] + ",");
		}
		if (endY > start) {
			for (int i = start + 1; i <= endY; i++) {
				System.out.print(matrix[i][endX] + ",");
			}
		}
		if (endX > start && endY > start) {
			for (int i = endX - 1; i >= start; i--) {
				System.out.print(matrix[endY][i] + ",");
			}
		}
		if (endY - start > 2) {
			for (int i = endY - 1; i > start; i--) {
				System.out.print(matrix[i][start] + ",");
			}
		}
	}
}
