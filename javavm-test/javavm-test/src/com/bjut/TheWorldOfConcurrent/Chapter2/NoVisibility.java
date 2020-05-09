package com.bjut.TheWorldOfConcurrent.Chapter2;

/**
 * java虚拟机参数-server切换到Server模式
 * <p>
 * 在Server模式下：
 * ReaderThread线程无法“看到”主线程的修改，导致ReaderThread永远无法退出
 * 解决方法：
 * 使用volatile来申明ready变量，告诉虚拟机，这个变量可能会在不同的线程中修改。
 */
public class NoVisibility {
    private static boolean ready;
    private static int number;

    private static class ReaderThread extends Thread {
        @Override
        public void run() {
            while (!ready) ;
            System.out.println(number);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new ReaderThread().start();
        Thread.sleep(1000);
        number = 42;
        ready = true;
        Thread.sleep(10000);
    }
}
