package com.bjut.ExceptionTest;

/*
 *   程序在try块中强制退出，
 *   System.exit(0) finally块中的代码没有执行
 *
 *   Java语言把异常当做对象来处理，并定义了一个基类（java.lang.Throwable）作为所有异常的父类。
 *   在java API中，已经定义了许多异常类，这些异常类分为Error（错误）和Exception（异常）两大类
 *   Error，Exception，RuntimeException都是Throwable的子类，throw可以抛出这些异常
 *
 *   其中Error引起程序终止（例如： OutOfMemoryError，ThreadDeath）
 * */
public class ExceptionTest04 {
    public static void testFianlly() {
        try {
            System.out.println("try block");
            System.exit(0);
        } catch (Exception e) {
            System.out.println("catch block");
        } finally {
            System.out.println("finally block");
        }
    }

    public static void main(String[] args) {
        testFianlly();
    }
}
