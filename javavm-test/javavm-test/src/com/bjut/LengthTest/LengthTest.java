package com.bjut.LengthTest;

/*
 *   1  数组提供了length属性
 *   2  length()方法时针对字符串而言的
 * */
public class LengthTest {
    public static void testArray(int[] arr) {
        System.out.println(arr.length);
    }

    public static void testString(String s) {
        System.out.println(s.length());
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7};
        String s = "1357";
        testArray(arr);
        testString(s);
    }
}
