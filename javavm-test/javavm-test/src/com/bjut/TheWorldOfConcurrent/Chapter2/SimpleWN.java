package com.bjut.TheWorldOfConcurrent.Chapter2;

/**
 * 两个线程T1 T2
 * T1执行object.wait()时，它持有object的锁
 * wait()方法执行后，T1会等待，并释放object锁。
 * <p>
 * T2在执行notify()之前也会先获得object的对象锁，
 * 当T2执行了notify()方法，会通知T1，会首先尝试重新获得object的对象锁
 */
public class SimpleWN {
    final static Object object = new Object();

    public static class T1 extends Thread {
        @Override
        public void run() {
            synchronized (object) {
                System.out.println(System.currentTimeMillis() + ": T1 start !");
                try {
                    System.out.println(System.currentTimeMillis() + "T1 wait for object ");
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis() + ": T1 end!");
            }
        }
    }

    public static class T2 extends Thread {
        @Override
        public void run() {
            synchronized (object) {
                System.out.println(System.currentTimeMillis() + ": T2 start! notify one Thread");
                object.notify();
                System.out.println(System.currentTimeMillis() + ": T2 end!");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new T1();
        Thread t2 = new T2();
        t1.start();
        t2.start();
    }
}
