package com.zuoxiao.app.sort;

/**
 * TODO
 *
 * @author zuoxiao
 * @date 2021/3/5 10:11
 */
public class BubbleSort {

    public static int[] sort(int[] array){
        //Arrays.sort(array, 0, array.length);
        if(array == null || array.length==1){
            return array;
        }
        for (int j = 0; j < array.length; j++) {
            for (int i = 0; i < array.length-1-j; i++) {
                if(array[i] > array[i+1]){
                    int tmp = array[i+1];
                    array[i+1] = array[i];
                    array[i] = tmp;
                }
            }
        }
        return array;
    }
}
