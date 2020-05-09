package com.bjut.TheWorldOfConcurrent.Chapter3;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CountDoenLatch是一个非常实用的多线程控制工具类，"Count Downn"在英文中意为倒计数。
 *   把门锁起来，不让里面的线程跑出来，因此，这个工具通常用来控制线程等待，它可以让某一个线程等待直到倒计时结束，再开始执行。
 *
 *   对于倒计时器，一宗典型的场景就是火箭发射，再火箭发射前，为了确保万无一失，往往还要进行各项设备、仪器的检查，
 *   只有等所有的检查完毕后，引擎才能点火，这种场景就非常适合使用CountDownLatch。它可以使得点火线程等待所有检查线程全部完工后，再执行。
 *
 *   注：CyclicBarrier要比CountDoenLatch更加复杂和强大
 *
 * @author zhengtianqi
 * @date 2019/2/5 22:33
 */
public class CountDownLatchDemo implements Runnable {
    static final CountDownLatch end = new CountDownLatch(10);
    static final CountDownLatchDemo demo = new CountDownLatchDemo();

    @Override
    public void run() {
        try {
            //模拟检查任务
            Thread.sleep(new Random().nextInt(10) * 1000);
            System.out.println("check complete");
            end.countDown();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newFixedThreadPool(10);
        for(int i=0;i<10;i++){
            exec.submit(demo);
        }
        //等待检查
        end.await();
        //发射火箭
        System.out.println("Fire!");
        exec.shutdown();
    }
}
