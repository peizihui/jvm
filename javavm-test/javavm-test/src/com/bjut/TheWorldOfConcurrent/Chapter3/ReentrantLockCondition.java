package com.bjut.TheWorldOfConcurrent.Chapter3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 和Object.wait()和notify()方法一样，当线程使用Condition.await()时，要求线程持有有相关的重入锁，
 * 在Condition.await()调用后，这个线程会释放这把锁。
 * 同理：
 * 在Condition.signal()方法调用时，也要求线程先获得相关的锁。
 * <p>
 * <p>
 * await()方法会使当前线程等待，同时释放当前锁，当其他线程中使用signal()或者signalAll方法时，
 * 线程会重新获得锁并继续执行，或者当线程被中断，也能跳出等待
 * <p>
 * awaitUninterruptibly()方法与await()方法基本相同，但是它并不会在等待过程中响应中断。
 * single()方法用于唤醒一个在等待的进程。相对的singalAll()方法会唤醒所有在等待中的线程，这和Object.notify()方法很相似。
 */
public class ReentrantLockCondition implements Runnable {
    public static ReentrantLock lock = new ReentrantLock();
    public static Condition condition = lock.newCondition(); // 通过lock生成一个与之绑定的Condition对象

    @Override
    public void run() {
        try {
            lock.lock();
            condition.await(); // 必须先获得锁才可以用await()方法
            System.out.println("Thread is going on");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockCondition tl = new ReentrantLockCondition();
        Thread t1 = new Thread(tl);
        t1.start(); // 通知线程t1继续执行
        Thread.sleep(2000);
        lock.lock(); // 表示Lock对象执行了锁定操作，其他的线程都必须等这个线程完成，并释放锁之后，才能执行被锁住的代码块
        condition.signal(); // 由主线程main发出通知，告知等待在Condition上的线程可以继续执行了,所以打印了24行的Thread is going on
        lock.unlock();
    }
}
