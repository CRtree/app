package com.zuoxiao.app.classloader;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;

/**
 * TODO
 *
 * @author zuoxiao
 * @date 2021/4/12 19:41
 */
public class ClassloadTest {
    public static void main(String[] args){
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        System.out.println(classLoader);
        System.out.println(classLoader.getParent());
        System.out.println(classLoader.getParent().getParent());
        System.out.println(classLoader.getClass().getClassLoader());

        AtomicInteger atomicInteger = new AtomicInteger(1);
        atomicInteger.addAndGet(5);
        Lock lock;
        String a = "a";


    }
}
