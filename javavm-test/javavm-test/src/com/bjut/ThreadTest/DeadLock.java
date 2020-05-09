package com.bjut.ThreadTest;

/*
 * 线程死锁是指由于两个或者多个线程互相持有对方所需要的资源，导致这些线程处于等待状态，无法前往执行。当线程进入对象的synchronized代码块时，便占有了资源，直到它退出该代码块或者调用wait方法，才释放资源，在此期间，其他线程将不能进入该代码块。当线程互相持有对方所需要的资源时，会互相等待对方释放资源，如果线程都不主动释放所占有的资源，将产生死锁。
 * 当然死锁的产生是必须要满足一些特定条件的：
 *
 * 1.互斥条件：进程对于所分配到的资源具有排它性，即一个资源只能被一个进程占用，直到被该进程释放
 * 2.请求和保持条件：一个进程因请求被占用资源而发生阻塞时，对已获得的资源保持不放。
 * 3.不剥夺条件：任何一个资源在没被该进程释放之前，任何其他进程都无法对他剥夺占用
 * 4.循环等待条件：当发生死锁时，所等待的进程必定会形成一个环路（类似于死循环），造成永久阻塞。
 *
 * */
public class DeadLock {
    public static void main(String[] args) {
        dead_lock();
    }

    private static void dead_lock() {
        // 两个资源
        final Object resource1 = "resource1";
        final Object resource2 = "resource12";
        // 第一个线程，想先占有resource1，再尝试占有resource2
        Thread t1 = new Thread(() -> {
            // 尝试占有resource1
            synchronized (resource1) {
                // 成功占有resource1
                System.out.println("Thread1 1:locked resource1");
                // 休眠一段时间
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 尝试占有resource2，如果不能占有，该线程会一直等到
                synchronized (resource2) {
                    System.out.println("Thread1 1:locked resource2");
                }
            }
        });
        // 第二个线程，想先占有resource2，再占有resource1
        Thread t2 = new Thread(() -> {
            // 尝试占有resource2
            synchronized (resource2) {
                // 成功占有resource2
                System.out.println("Thread2Test :locked resource2");
                // 休眠一段时间
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 尝试占有resource1，如果不能占有，该线程会一直等到
                synchronized (resource1) {
                    System.out.println("Thread1 2:locked resource1");
                }
            }
        });
        t1.start();
        t2.start();
    }
}
