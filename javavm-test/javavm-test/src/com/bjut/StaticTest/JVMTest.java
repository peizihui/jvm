package com.bjut.StaticTest;

/*
 *  JVM在加载类的时候会执行static代码块，
 *
 *  如果有多个static代码块，JVM会按顺序执行
 *
 *  static代码块经常被用来初始化静态变量，
 *
 *  需要注意的是，这些static代码块只会被执行一次
 * */
public class JVMTest {
    private static int a;

    static {
        JVMTest.a = 4;
        System.out.println(a);
        System.out.println("static block is calles");
    }

    public static void main(String[] args) {

    }
}
