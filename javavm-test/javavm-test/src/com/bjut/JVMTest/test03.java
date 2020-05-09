package com.bjut.JVMTest;

import java.util.Random;

/*
 *   OOM异常
 *   Exception in ThreadTest "main" java.lang.OutOfMemoryError: Java heap(堆) space
 *   Java中对象都是分配在heap(堆)中
 *   从heap中分配内存所消耗的时间   >>   从stack产生存储空间所需的时间。
 *
 * */
public class test03 {
    public static void main(String[] args) {
        String str = "www.baidu.com";
        while (true) {
            str += str + new Random().nextInt(88888888) + new Random().nextInt(999999999);
        }
    }
}
