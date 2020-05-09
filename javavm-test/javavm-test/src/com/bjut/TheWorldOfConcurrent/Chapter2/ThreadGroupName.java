package com.bjut.TheWorldOfConcurrent.Chapter2;

/**
 *
 *
 *
 * */
public class ThreadGroupName implements Runnable {
    public static void main(String[] args) {
        ThreadGroup tg = new ThreadGroup("PrintGroup"); // 建立一个名为PrintGroup的线程组
        Thread t1 = new Thread(tg, new ThreadGroupName(), "T1");
        Thread t2 = new Thread(tg, new ThreadGroupName(), "T2"); // 将T1 T2两个线程加入这个数组中
        t1.start();
        t2.start();
        System.out.println(tg.activeCount()); // 线程组activeCount()可以获得活动线程的总数
        tg.list(); //list可以打印这个线程组中所有的线程信息,对调试有一定帮助。
    }

    @Override
    public void run() {
        String groupAndName = Thread.currentThread().getThreadGroup().getName()
                + "-" + Thread.currentThread().getName();
        while (true) {
            System.out.println("I am " + groupAndName);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
