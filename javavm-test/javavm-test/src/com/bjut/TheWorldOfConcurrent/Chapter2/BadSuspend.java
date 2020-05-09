package com.bjut.TheWorldOfConcurrent.Chapter2;

/**
 * 不推荐使用suspend()去挂起线程
 * 因为suspend()在导致线程暂停的同时，并不会去释放任何锁资源
 * 其他任何线程想要访问被它暂用的锁时，都会被牵连，导致无法正常继续运行
 * <p>
 * 直到对应的线程上进行了resume()操作，被挂起的线程才能继续，从而其他所有阻塞在相关锁上的线程也可以继续执行
 */
public class BadSuspend {
    public static Object u = new Object();
    static ChangeObjectThread t1 = new ChangeObjectThread("t1");
    static ChangeObjectThread t2 = new ChangeObjectThread("t2");

    public static class ChangeObjectThread extends Thread {
        public ChangeObjectThread(String name) {
            super.setName(name);
        }

        @Override
        public void run() {
            synchronized (u) {
                System.out.println("in " + getName());
                Thread.currentThread().suspend();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        t1.start();
        Thread.sleep(100);
        t2.start();
        t1.resume();
        t2.resume();
        t1.join();
        t2.join();
    }
}
