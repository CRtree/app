package com.zuoxiao.app.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author zuoxiao
 * @date 2020/10/24 13:29
 */
@SpringBootTest
public class RedisTest {

    @Autowired
    RedisTemplate<String,String> redisTemplate;

    @Test
    void setAndGet() {
        //测试set和get操作的实际性能
        for (int j = 0; j < 5; j++) {
            long current = System.currentTimeMillis();
            for (int i = 0; i < 10000; i++) {
                redisTemplate.opsForValue().set("zuoxiao:"+i,i+"",10, TimeUnit.MINUTES);
            }
            System.out.println(String.format("redis的10000次set操作耗时%s毫秒", System.currentTimeMillis()-current));
            current = System.currentTimeMillis();
            for (int i = 0; i < 10000; i++) {
                redisTemplate.opsForValue().get("zuoxiao:"+i);
            }
            System.out.println(String.format("redis的10000次get操作耗时%s毫秒", System.currentTimeMillis()-current));
            System.out.println();
        }
    }

    @Test
    void setAndGetWithPipeline() {
        //测试Pipeline下的set和get操作的实际性能
        for (int j = 0; j < 5; j++) {
            long current = System.currentTimeMillis();
            List<Object> result = redisTemplate.executePipelined(new RedisCallback<String>() {
                @Override
                public String doInRedis(RedisConnection redisConnection) throws DataAccessException {
                    RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                    for (int i = 0; i < 10000; i++) {
                        redisConnection.set(serializer.serialize("zuoxiao:"+i),serializer.serialize(i+""), Expiration.seconds(60), RedisStringCommands.SetOption.UPSERT );
                    }
                    return null;
                }
            });
            System.out.println(String.format("redis的 pipeline 10000次set操作耗时%s毫秒", System.currentTimeMillis()-current));
            System.out.println(result);
            current = System.currentTimeMillis();
            List<Object> result2 = redisTemplate.executePipelined(new RedisCallback<String>() {
                @Override
                public String doInRedis(RedisConnection redisConnection) throws DataAccessException {
                    RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                    for (int i = 0; i < 10000; i++) {
                        redisConnection.get(serializer.serialize("zuoxiao:"+i));
                    }
                    return null;
                }
            });
            System.out.println(String.format("redis的 pipeline 10000次get操作耗时%s毫秒", System.currentTimeMillis()-current));
            System.out.println(result2);
        }
    }

    @Test
    void listTest(){

    }

    @Test
    void hashTest(){
        redisTemplate.opsForHash().put("hs-key","name","zuoxiao");
        String value = (String) redisTemplate.opsForHash().get("hs-key","name");
        System.out.println("hash ："+value);
    }

}
