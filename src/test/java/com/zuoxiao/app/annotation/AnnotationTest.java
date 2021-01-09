package com.zuoxiao.app.annotation;

import com.zuoxiao.app.pojo.User;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * TODO
 *
 * @author zuoxiao
 * @date 2020/11/16 19:34
 */
@Log4j2
@SpringBootTest
public class AnnotationTest {
    @Test
    public void test1(){
        User user1 = new User();
        user1.setId(123L);
        user1.setName("zx");
        user1.setAge(26);
        HahaHandler.execute(user1);
    }
}
