package com.bjut.Thread2Test;

/*
 * Exception in ThreadTest "main" java.lang.IllegalThreadStateException
 *	at java.lang.Thread.start(Thread.java:708)
 *	at com.bjut.Thread2Test.StartDead.main(StartDead.java:29)
 * */
public class StartDead extends Thread {
    private int i;

    // 重写run方法，run方法的方法体就是线程执行体
    public void run() {
        for (; i < 100; i++) {
            System.out.println(getName() + " " + i);
        }
    }

    public static void main(String[] args) {
        // 创建线程对象
        StartDead sd = new StartDead();
        for (int i = 0; i < 300; i++) {
            // 调用Thread
            // 调用Thread的currentThread()方法获取当前线程
            // 调用Thread的currentThread()方法获取当前线程
            System.out.println(Thread.currentThread().getName() + " " + i);
            if (i == 20) {
                // 启动线程
                sd.start();
                System.out.println(sd.isAlive());
            }
            // 判断启动后线程的isAlive方法返回false
            // 当i>20时，该线程肯定已经启动过了，如果sd.isAlive为假时
            // 那就是死亡状态了
            if (i > 20 && !sd.isAlive()) {
                // 试图再次启动该线程
                sd.start();
            }
        }

    }
}
