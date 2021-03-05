package com.zuoxiao.app.sort;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

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
}
