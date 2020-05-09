package com.bjut.TheWorldOfConcurrent.Chapter3;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *  ReadWriteLock是JDK5中提供的读写分离锁。读写分离锁可以有效地帮助减少锁竞争，以提升系统性能。
 *  用锁分离的机制来提升性能非常容易理解，比如线程A1,A2,A3进行写操作，B1,B2,B3进行读操作------>
 *                              如果使用重入锁或者内部锁 ， 则理论上说所有读之间、读与写之间，写与写之间都是串行操作。
 *                              当B1进行读取时，B2、B3则需要等待锁，由于读操作并不对数据的完整性造成破坏，这种等待显然是不合理。。
 *  因此  ：  读写锁就有了发挥功能的余地
 *
 * @author zhengtianqi
 * @date 2019/2/5 21:37
 */
public class ReadWriteLockDemo {
    private static Lock lock = new ReentrantLock();
    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static Lock readLock = readWriteLock.readLock();
    private static Lock writeLock = readWriteLock.writeLock();
    private int value;

    public Object handleRead(Lock lock) throws InterruptedException {
        try {
            lock.lock();    //模拟读操作
            Thread.sleep(1000); //读操作的耗时越多，读写锁的优势就越明显
            return value;
        } finally {
            lock.unlock();
        }
    }

    public void handleWrite(Lock lock, int index) throws InterruptedException {
        try {
            lock.lock();    //模拟写操作
            Thread.sleep(1000);
            value = index;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        final ReadWriteLockDemo demo = new ReadWriteLockDemo();

        Runnable readRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    demo.handleRead(readLock);
                    demo.handleRead(lock);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable writeRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    demo.handleWrite(writeLock, new Random().nextInt());
                    demo.handleWrite(lock, new Random().nextInt());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        for(int i = 0; i<18; i++){
            new Thread(readRunnable).start();
        }
        for(int i = 18; i<20; i++){
            new Thread(writeRunnable).start();
        }
    }
}
