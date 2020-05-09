package com.bjut.ThreadTest;

/*
 * 对象锁的同步和异步
 * 如果是共享的资源，可以用同步
 * 异步是独立的，相互之间不受到任何制约
 *
 * */
public class MyObject {
    public synchronized void method1() {
        try {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /* 当method方法用synchonized修饰的时候，4s后才会调用这个方法
     *  因为当t1的锁释放之后才可以调用t2的锁
     * */
    public void method2() {
        System.out.println(Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        final MyObject mo = new MyObject();
        /*
         *  分析：
         *  t1线程先持有object对象的Lock锁，t2线程可以以异步的方式调用对象中的非synchronzied修饰的方法
         *  t1线程先持有object对象的Lock锁，t2线程如果在这个时候调用对象中的同步（synchronized）方法则需要等待，也就是同步
         * */
        Thread t1 = new Thread(() -> {
            mo.method1();
        }, "t1");

        Thread t2 = new Thread(() -> {
            mo.method2();
        }, "t2");
        t1.start();
        t2.start();
    }
}
