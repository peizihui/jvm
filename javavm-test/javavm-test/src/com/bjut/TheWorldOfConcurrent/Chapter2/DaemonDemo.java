package com.bjut.TheWorldOfConcurrent.Chapter2;

public class DaemonDemo {
    public static class DaemonT extends Thread {
        @Override
        public void run() {
            System.out.println("I am alive");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = new DaemonT();
        t.setDaemon(true); // 将线程t设置为守护线程。设置守护线程必须在start()之前设置。  否则会得到异常，告诉你守护线程设置失败。（IllegalThreadStateException）
        t.start();

        Thread.sleep(2000); //         // --------程序执行到此处，前台线程（main线程）结束-----------
        // 后台线程也应该随之结束
    }
}
