package com.zuoxiao.app.sort;

import java.util.Arrays;

/**
 * TODO
 *
 * @author zuoxiao
 * @date 2021/3/5 10:11
 */
public class MergeSort {

    public static int[] sort(int[] array) {
        //Arrays.sort(array, 0, array.length);
        if (array == null || array.length == 1) {
            return array;
        }
        return partion(array);
    }

    static int[] partion(int[] array) {
        if (array.length < 2) {
            return array;
        }
        int mid = array.length >> 1;

        int[] left = partion(Arrays.copyOfRange(array, 0, mid));
        int[] right = partion(Arrays.copyOfRange(array, mid, array.length));
        return merge(left, right);
    }

    static int[] merge(int[] leftArray, int[] rightArray) {
        int left = 0, right = 0;
        int[] result = new int[leftArray.length + rightArray.length];
        int index = 0;
        while (left < leftArray.length || right < rightArray.length) {
            if (left == leftArray.length && right < rightArray.length) {
                for (int i = right; i < rightArray.length; i++) {
                    result[index++] = rightArray[i];
                    right++;
                }
            }else if (left < leftArray.length && right == rightArray.length) {
                for (int i = left; i < leftArray.length; i++) {
                    result[index++] = leftArray[i];
                    left++;
                }
            }else if (leftArray[left] < rightArray[right]) {
                result[index++] = leftArray[left++];
            }else if (leftArray[left] >= rightArray[right]) {
                result[index++] = rightArray[right++];
            }

        }
        return result;
    }
}
