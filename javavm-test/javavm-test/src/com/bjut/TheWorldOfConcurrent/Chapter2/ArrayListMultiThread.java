package com.bjut.TheWorldOfConcurrent.Chapter2;

import java.util.ArrayList;

/**
 * Exception in thread "Thread-1" java.lang.ArrayIndexOutOfBoundsException: 10
 * 因为ArrayList在扩容过程中，内部一致性被破坏，但是由于没有锁的保护，另外一个线程访问到了不一致的内部状态-->导致出现越界问题
 * 打印的ArrayList的大小也出错 1000004
 * <p>
 * 两个线程同时对ArrayList在同一个地方赋值导致，可以改用线程安全的Vector，但是开销大
 */
public class ArrayListMultiThread {
    static ArrayList<Integer> al = new ArrayList<Integer>(10);

    public static class AddThread implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 1000000; i++) {
                al.add(i);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new AddThread());
        Thread t2 = new Thread(new AddThread());

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(al.size());
    }
}
