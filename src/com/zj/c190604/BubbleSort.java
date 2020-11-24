package com.zj.c190604;

import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        //定义数组
        int[] arr = {9, 8, 10, 23, 89, 76, 45, 7, 23, 41};
       //冒泡排序
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            System.out.println("第"+(i+1)+"轮排序后为: "+Arrays.toString(arr));
        }
    }
}
