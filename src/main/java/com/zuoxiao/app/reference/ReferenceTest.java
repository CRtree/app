package com.zuoxiao.app.reference;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author zuoxiao
 * @date 2021/5/21 8:47
 */
public class ReferenceTest {
    public static void main(String[] args) {
        softTest();
    }

    private static void weakTest(){
        Apple apple = new Apple("apple1");
        Fruit fruit = new Fruit(apple);
        System.out.println("apple:" + apple.getName());
        System.out.println("fruit.apple:" + fruit.getApple().getName());
        apple = null;
        System.gc();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("fruit.apple:" + fruit.getApple());
    }

    private static void softTest(){
        Apple apple = new Apple("apple1");
        Fruit2 fruit2 = new Fruit2(apple);
        System.out.println("apple:" + apple.getName());
        System.out.println("fruit2.apple:" + fruit2.getApple().getName());
        apple = null;
        System.gc();
        try {
            Thread t = new Thread(()->{ List<byte[]> list = new ArrayList<>();
                while (true){
                    list.add(new byte[1024]);
                }});
            t.start();
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("fruit2.apple:" + fruit2.getApple());
    }

    private static void phantomTest(){
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
    }
}
