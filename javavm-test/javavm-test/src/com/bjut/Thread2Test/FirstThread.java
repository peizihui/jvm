package com.bjut.Thread2Test;

// 通过继承Thread类来创建线程
public class FirstThread extends Thread {
    // 重写run()方法，
    private int i;

    public void run() {
        for (; i < 100; i++) {
            // 当线程类继承Thread类时，直接使用this即可获取当前线程
            // Thread对象的getName()返回当前线程的名字
            // 因此可以直接调用getName()方法返回当前线程的名字
            System.out.println(getName() + " " + i);
        }
    }

    public static void main(String[] args) { // 你 主线程的线程执行体
        for (int i = 0; i < 100; i++) {
            // 调用Thread()的currentThread()方法获取当前线程
            System.out.println(Thread.currentThread().getName() + " " + i); // 主线程正在运行
            if (i == 20) {
                // 创建并启动第一个线程
                new FirstThread().start(); // Thread-0 第一个线程正在运行
                // 创建并启动第二个线程
                new FirstThread().start(); // Thread-1 第二个线程正在运行
            }
        }
    }
}
