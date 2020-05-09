package com.bjut.TheWorldOfConcurrent.Chapter2;

/**
 * 因为两个线程指向了不同的Runnable实例，这两个线程的Runnable实例并不是同一个对象。
 * 因此，t1进入同步方法前加锁自己的Runnable实例，而t2也关注与自己的对象锁。
 * 换言之，这两个线程使用的是两把不同的锁，因此，线程安全是无法保证的。
 */
public class AccountingSyncBad implements Runnable {
    static int i = 0;

    public synchronized void increase() { // 应该改为 public static synchronized void increase()
        i++;
    }

    @Override
    public void run() {
        for (int j = 0; j < 10000000; j++) {
            increase();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new AccountingSyncBad());
        Thread t2 = new Thread(new AccountingSyncBad());

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(i);
    }
}
