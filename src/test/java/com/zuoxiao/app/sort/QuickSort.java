package com.zuoxiao.app.sort;

/**
 * TODO
 *
 * @author zuoxiao
 * @date 2021/3/5 10:11
 */
public class QuickSort {

    public static int[] sort(int[] array) {
        //Arrays.sort(array, 0, array.length);
        if (array == null || array.length == 1) {
            return array;
        }
        partion(array,0,array.length-1);
        return array;
    }

    static void partion(int[] array, int left, int right) {
        if (left >= right) {
            return;
        }
        int base = array[left], baseleft = left, baseright = right;
        boolean flag = true;
        while (left < right) {
            if (flag) {
                if (array[right] < base) {
                    array[left++] = array[right];
                    flag = false;
                } else {
                    right--;
                }
            } else {
                if (array[left] > base) {
                    array[right--] = array[left];
                    flag = true;
                } else {
                    left++;
                }
            }
        }
        array[left] = base;
        partion(array, baseleft, left - 1);
        partion(array, left + 1, baseright);
    }

}
