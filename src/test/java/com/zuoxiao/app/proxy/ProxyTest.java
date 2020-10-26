package com.zuoxiao.app.proxy;

import com.zuoxiao.app.interf.PrintInterface;
import com.zuoxiao.app.pojo.Person;
import com.zuoxiao.app.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Proxy;

/**
 * TODO
 *
 * @author zuoxiao
 * @date 2020/10/26 9:03
 */
@SpringBootTest
public class ProxyTest {

    @Test
    public void test() {
        User user1 = new User();
        user1.setAge(2);
        user1.setName("zzz");
        PrintInterface user = (PrintInterface) Proxy.newProxyInstance(PrintInterface.class.getClassLoader(), new Class[]{PrintInterface.class}, new UserProxyHandler(user1));
        user.print();
    }

    @Test
    public void test2() {
        //测试动态代理是否可以代理类对象而不是接口
        //结果：不可以代理类对象，只能代理接口
        User user1 = new User();
        user1.setId(22222L);
        user1.setAge(2);
        user1.setName("zzz");
        Person person = (Person) Proxy.newProxyInstance(Person.class.getClassLoader(),new Class[]{Person.class},new PersonProxyHandler(user1));
        System.out.println(person.getId());
    }
}
