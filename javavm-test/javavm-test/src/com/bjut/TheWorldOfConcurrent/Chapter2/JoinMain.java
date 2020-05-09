package com.bjut.TheWorldOfConcurrent.Chapter2;

/**
 * 如果不使用join()等待AddThread，那么得到i很可能是0或者一个非常小的数
 * 因为AddThread还没开始执行，i的值就已经被输出了
 * <p>
 * 但是使用join()方法后，表示主线程愿意等待AddThread执行完毕，跟着AddThread一起往前走
 * 故在join()返回时，AddThread已经执行完成，故i总是1000000
 */
public class JoinMain {
    public volatile static int i = 0; // 用volatile修饰的变量，就会具有可见性。  在 Java 中 volatile、synchronized 和 final 实现可见性

    public static class AddThread extends Thread {
        @Override
        public void run() {
            for (i = 0; i < 1000000; i++) ;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AddThread at = new AddThread();
        at.start();
        at.join();  // 使用join()方法后，表示主线程愿意等待AddThread执行完毕，跟着AddThread一起往前走
        System.out.println(i);
    }
}
