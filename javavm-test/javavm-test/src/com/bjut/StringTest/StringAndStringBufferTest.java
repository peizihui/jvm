package com.bjut.StringTest;

/*
 *   String，StringBuffer，StringBuilder，StringTokenizer
 *   String用于字符串操作，属于不可变类
 *   StringBuffer也用于字符串操作，属于可变类
 *
 *   以下程序： 比较String和StringBuufer修改时的性能
 *   StringBuilder在单线程时效率更高，但是不是线程安全的。（StringBuffer是线程安全的）
 *   so: 多线程大量数据，优先考虑StringBuffer
 * */
public class StringAndStringBufferTest {
    public static void testString() {
        String s = "Hello";
        String s1 = "world";
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            s += s1;
        }
        long end = System.currentTimeMillis();
        long runTime = (end - start);
        System.out.println("testString:" + runTime);
    }

    public static void testStringBuffer() {
        StringBuffer s = new StringBuffer("Hello");
        String s1 = "world";
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            s.append(s1);
        }
        long end = System.currentTimeMillis();
        long runTime = (end - start);
        System.out.println("testStringBuufer:" + runTime);
    }

    public static void main(String[] args) {
        testString();
        testStringBuffer();
    }
}
