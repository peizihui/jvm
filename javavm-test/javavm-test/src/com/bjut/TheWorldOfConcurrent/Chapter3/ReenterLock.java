package com.bjut.TheWorldOfConcurrent.Chapter3;

import java.util.concurrent.locks.ReentrantLock;

/**
 * synchronized的功能拓展：重写锁（Re-Entrant-Lock，因为这种锁设计可以反复进入的）
 * 历史：JDK 5.0 重写锁的性能远远好于synchronized，但是从JDK 6.0开始，synchronized做了大量的优化，使两者的性能差距并不大
 * 一个线程连续两次获得同一把锁，这是允许的！如果不允许，那么同一个线程在第2次获得锁时，将会和自己产生死锁。
 * 释放锁和申请锁的次数必须相同，
 * 如果释放锁的次数多，将会产生java.lang.IllegalMonitorStateException异常
 * 如果释放的锁次数少了，那么相当于线程还持有这个锁，其他线程无法进入临界区
 */
public class ReenterLock implements Runnable {
    public static ReentrantLock lock = new ReentrantLock();

    public static int i = 0;

    @Override
    public void run() {
        for (int j = 0; j < 10000000; j++) {
            lock.lock();    // 使用重入锁保护临界区资源i  确保多线程对i操作的安全性（需要指定何时加锁，何时释放锁，所以灵活性要远大于synchronized）
            try {
                i++;
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReenterLock tl = new ReenterLock();
        Thread t1 = new Thread(tl);
        Thread t2 = new Thread(tl);


        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(i);
    }
}
