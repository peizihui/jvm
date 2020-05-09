package com.bjut.TheWorldOfConcurrent.Chapter3;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock:
 * lock()： 获得锁，如果所已经被占用，则等待
 * lockInterruptibly()：获得锁，但优先相应中断
 * tryLock()：尝试获得锁，如果成功，返回true，失败返回false。该方法不等待，立即返回
 * tryLock(long time, TimeUnit unit): 在给定时间内尝试获得锁
 * unLock()：释放锁
 */
public class FairLock implements Runnable {
    public static ReentrantLock fairLock = new ReentrantLock(true);

    @Override
    public void run() {
        while (true) {
            try {
                fairLock.lock();
                System.out.println(Thread.currentThread().getName() + "获得锁");
            } finally {
                fairLock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        FairLock r1 = new FairLock();
        Thread t1 = new Thread(r1, "Thread_t1");
        Thread t2 = new Thread(r1, "Thread_t2");
        t1.start();
        t2.start();
    }
}
