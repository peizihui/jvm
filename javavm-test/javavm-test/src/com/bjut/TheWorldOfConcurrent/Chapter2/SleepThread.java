package com.bjut.TheWorldOfConcurrent.Chapter2;

/**
 * Thread.sleep()函数的 签名如下：
 * public static native void sleep(long millis) throws InterruptedException
 * 使当前正在执行的线程休眠（暂时停止） 指定毫秒数
 */
public class SleepThread {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Interrupted!");
                    break;
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    System.out.println("Interrupted When Sleep");
                    // 设置中断状态
                    Thread.currentThread().interrupt();
                }
                Thread.yield();
            }
        });
        t1.start();
        Thread.sleep(2000);
        t1.interrupt();
    }
}
