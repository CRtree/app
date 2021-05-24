package com.zuoxiao.app.reference;

import sun.nio.ch.DirectBuffer;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

/**
 * TODO
 *
 * @author zuoxiao
 * @date 2021/5/21 8:47
 */
public class ReferenceTest {
    public static void main(String[] args) {
       phantomTest();
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
        System.out.println("分配内存前");
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024*1024*500);//100m
        ByteBuffer.allocate(1024);
        byte a = 12;
        byteBuffer.put(a);
        System.out.println("分配内存后");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("清除内存前");
        //clean(byteBuffer);
        byteBuffer = null;
        System.gc();
        System.out.println("清除内存后");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void bitmapTest(){
        int [] array = new int [] {1,2,3,22,0,3,129};
        BitSet bitSet = new BitSet(6);
        //将数组内容组bitmap
        for(int i=0;i<array.length;i++)
        {
            bitSet.set(array[i], true);
        }
        System.out.println(bitSet.size());
        System.out.println(bitSet.get(3));
        Integer i = Integer.valueOf(23);

    }

    public static void clean(final ByteBuffer byteBuffer) {
        if (byteBuffer.isDirect()) {
            ((DirectBuffer) byteBuffer).cleaner().clean();
        }
    }
}
