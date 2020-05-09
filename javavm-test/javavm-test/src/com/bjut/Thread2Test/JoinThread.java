package com.bjut.Thread2Test;

/*
 *  有三个线程 必须等到join线程运行完后主线程才会由阻塞变成就绪
 * */
public class JoinThread extends Thread {
    // 提供一个有参数的构造器，用于设置该线程的名字
    public JoinThread(String name) {
        super(name);
    }

    // 重写run方法。定义线程执行体
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(getName() + " " + i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 启动子线程
        new JoinThread("新线程").start();
        for (int i = 0; i < 100; i++) {
            if (i == 20) {
                JoinThread jt = new JoinThread("被Join的线程");
                jt.start();
                // main线程调用了jt线程的join方法，main线程
                // 必须等jt执行结束才会向下执行
                jt.join();
            }
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }
}
