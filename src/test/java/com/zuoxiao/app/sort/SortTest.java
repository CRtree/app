package com.zuoxiao.app.sort;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Random;

/**
 * TODO
 *
 * @author zuoxiao
 * @date 2021/3/5 10:38
 */
@SpringBootTest
public class SortTest {

    int[] array = {1,3,5,9,2,4,5};

    void print(){
        System.out.println(Arrays.toString(array));
    }

    @Test
    public void bubbleTest(){
        print();
        BubbleSort.sort(array);
        print();
    }

    @Test
    public void selectTest(){
        print();
        SelectSort.sort(array);
        print();
    }

    @Test
    public void InsertTest(){
        print();
        InsertSort.sort(array);
        print();
    }

    @Test
    public void MergeTest(){
        print();
        int[] result = MergeSort.sort(array);
        System.out.println(Arrays.toString(result));
    }

    @Test
    public void QuicksortTest(){
        print();
        QuickSort.sort(array);
        print();
    }

    private void createArray(){
        int size = 20000;
        array = new int[size];
        for (int i = 0; i < size; i++) {
            Random random = new Random();
            int t = random.nextInt(size*10);
            array[i] = t;
        }
    }

    @Test
    public void bigTest(){
        createArray();
        long current = System.currentTimeMillis();
        bubbleTest();
        System.out.println("BubbleSort耗时："+  (System.currentTimeMillis()-current) + "ms");

//        createArray();
//        current = System.currentTimeMillis();
//        selectTest();
//        System.out.println("SelectSort耗时："+  (System.currentTimeMillis()-current) + "ms");
//
//        createArray();
//        current = System.currentTimeMillis();
//        InsertTest();
//        System.out.println("InsertSort耗时："+  (System.currentTimeMillis()-current) + "ms");
//
//        createArray();
//        current = System.currentTimeMillis();
//        MergeTest();
//        System.out.println("MergeSort耗时："+  (System.currentTimeMillis()-current) + "ms");
//
//        createArray();
//        current = System.currentTimeMillis();
//        QuicksortTest();
//        System.out.println("Quicksort耗时："+  (System.currentTimeMillis()-current) + "ms");
    }
}
