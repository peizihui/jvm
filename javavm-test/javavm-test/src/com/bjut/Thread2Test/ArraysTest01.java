package com.bjut.Thread2Test;

import java.util.Arrays;

public class ArraysTest01 {
    public static void main(String args[]) {
        int[] arr1 = new int[]{3, -4, 25, 16, 30, 18};
        Arrays.parallelSort(arr1);
        System.out.println(Arrays.toString(arr1));
    }
}
