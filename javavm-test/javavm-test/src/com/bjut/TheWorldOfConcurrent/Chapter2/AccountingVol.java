package com.bjut.TheWorldOfConcurrent.Chapter2;

/**
 * 两个线程同时对i进行累加，各执行10000000次，预计累加到20000000
 * 但是实际并没有，因为两个线程同时对其写入时，其中一个线程的结果会覆盖另一个（虽然i被声明为volatile变量）
 * <p>
 * 解决方案：
 * AccountingSync AccountingSync2
 */
public class AccountingVol implements Runnable {
    static AccountingVol instance = new AccountingVol();
    static volatile int i = 0;

    public static void increate() {
        i++;
    }

    @Override
    public void run() {
        for (int j = 0; j < 10000000; j++) {
            increate();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(i);
    }
}
