package com.zuoxiao.app.sort;

/**
 * TODO
 *
 * @author zuoxiao
 * @date 2021/3/5 10:11
 */
public class SelectSort {

    public static int[] sort(int[] array) {
        //Arrays.sort(array, 0, array.length);
        if (array == null || array.length == 1) {
            return array;
        }
        for (int j = 0; j < array.length - 1; j++) {
            int index = 0;
            int max = array[0];
            for (int i = 1; i < array.length - j; i++) {
                if (array[i] > max) {
                    index = i;
                    max = array[i];
                }
            }
            int tmp = array[array.length - 1 - j];
            array[array.length - 1 - j] = array[index];
            array[index] = tmp;
        }
        return array;
    }
}
