package com.zuoxiao.app.beanClone;

import com.zuoxiao.app.pojo.Address;
import com.zuoxiao.app.pojo.User;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.InvocationTargetException;

/**
 * TODO
 *
 * @author zuoxiao
 * @date 2020/11/16 19:34
 */
@Log4j2
@SpringBootTest
public class CloneTest {
    @Test
    public void test1(){
        User user1 = new User();
        user1.setId(123L);
        user1.setName("zx");
        user1.setAge(26);
        Address address = new Address();
        address.setStreet("the 5th road");
        user1.setAddress(address);
        try {
            User user2 = (User) BeanUtils.cloneBean(user1);
            user2.setName("zy");
            user2.getAddress().setStreet("the 6th road");
            log.info("org.apache.commons.beanutils.BeanUtils的cloneBean方法，只能克隆基础类型（非自定义类）");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
