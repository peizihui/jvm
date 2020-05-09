package com.bjut.TheWorldOfConcurrent.Chapter4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * newFixedThreadPool()为例，简单的展示线程池的使用。
 *
 */
public class ThreadPoolDemo {
    /**
     * 展示当前线程的id
     */
    public static class MyTask implements Runnable{

        @Override
        public void run() {
            System.out.println(System.currentTimeMillis() + ":Thread ID:" + Thread.currentThread().getId());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 建立一个固定大小的线程池，可以看到五个线程几乎是同步进行的。。
     *
     * 前5个任务和后5个任务的执行时间正好相差一秒钟（注意时间戳的单位是毫秒）并且前5个任务的线程ID和5个任务也是完全一致的
     * 这说明在这10个任务中，是分成2批次执行的。这也完全符合一个只有5个线程的线程池的行为
     * @param args
     */
    public static void main(String[] args) {
        MyTask task = new MyTask();
        ExecutorService es = Executors.newFixedThreadPool(5);
        for(int i = 0; i < 10; i++){
            es.submit(task);
        }
    }
}
