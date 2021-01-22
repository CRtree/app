package com.zuoxiao.app.cglib;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cglib.proxy.Enhancer;

import java.lang.reflect.Method;

/**
 * TODO
 *
 * @author zuoxiao
 * @date 2021/1/22 8:43
 */
@SpringBootTest
public class CglibTest {

    @Autowired
    private SomethingService somethingService;

    @Test
    public void test(){
        //观察spring IOC 的动态代理技术
        //结论：
        // 1.当类没有进行切面时，拿到的是类的实例（spring通过扫描注解或配置文件，通过class对象反射生成的实例）；
        // 2.当类的方法存在切面设置，比如事务、spring-Aspect，则会通过Cglib生成代理类；
        Class clazz = somethingService.getClass();
        System.out.println(clazz.getName());
        Method[] methods = clazz.getDeclaredMethods();
        for (Method m : methods){
            System.out.println(m.getName()+","+ m.getModifiers());
        }
        somethingService.talk();
        somethingService.say();
    }

    @Test
    public void userCglib(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SomethingService.class);
        enhancer.setCallback(new SomethingInterceptor());
        SomethingService service = (SomethingService) enhancer.create();
        service.say();
        service.talk();
        service.walk();
    }
}
