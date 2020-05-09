package com.bjut.Thread2Test;

public class SecondThread implements Runnable {

    private int i;

    @Override
    public void run() {
        for (; i < 100; i++) {
            // 当线程类实现Runnable接口时
            // 如果想获取当前线程，只能用Thread，currentThread()方法
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
            if (i == 20) {
                SecondThread st = new SecondThread(); //
                // 通过new Thread(target , name)方法创建新线程
                new Thread(st, "新线程1").start();
                new Thread(st, "新线程2").start();
            }
        }
    }
}
