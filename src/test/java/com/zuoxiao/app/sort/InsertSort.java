package com.zuoxiao.app.sort;

/**
 * TODO
 *
 * @author zuoxiao
 * @date 2021/3/5 10:11
 */
public class InsertSort {

    public static int[] sort(int[] array) {
        //Arrays.sort(array, 0, array.length);
        if (array == null || array.length == 1) {
            return array;
        }

        for (int j = 1; j < array.length; j++) {
            int i = j;
            int tmp = array[i];
            while (i >= 1) {
                if (array[i - 1] > tmp) {
                    array[i] = array[i - 1];
                    i--;
                }else{
                    array[i] = tmp;
                    break;
                }
            }
            if(i == 0){
                array[i] = tmp;
            }
        }
        return array;
    }
}
