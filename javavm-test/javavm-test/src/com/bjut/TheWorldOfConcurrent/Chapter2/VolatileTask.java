package com.bjut.TheWorldOfConcurrent.Chapter2;

/**
 * 输出结果小于100000（10个线程累加10000次）
 * valatile不能代替锁，他也无法保证一些复合操作的原子性
 */
public class VolatileTask {
    static volatile int i = 0;

    public static class PlusTask implements Runnable {
        @Override
        public void run() {
            for (int k = 0; k < 10000; k++)
                i++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(new PlusTask());
            threads[i].start();
        }
        for (int i = 0; i < 10; i++) {
            threads[i].join();
        }
        System.out.println(i);
    }
}
