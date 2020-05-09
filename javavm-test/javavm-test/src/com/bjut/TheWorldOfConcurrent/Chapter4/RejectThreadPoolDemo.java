package com.bjut.TheWorldOfConcurrent.Chapter4;

import java.util.concurrent.*;

/**
 * 自定义线程和拒绝策略
 *
 *  JDK 内置的拒绝策略如下：
 *    AbortPolicy 策略：该策略会直接抛出异常，阻止系统正常工作。
 *    CallerRunsPolicy策略：只要线程池未关闭，该策略直接在调试者进程中，运行当前被丢弃的任务。显然这样做不会真的丢弃任务。
 *                          但是，任务提交线程的性能极有可能会急剧下降。
 *    DiscardOledestPolicy策略：该策略将丢弃最老的一个请求，也就是即将被执行的一个任务，并尝试再次提交当前任务。
 *    DiscardPolicy策略：该策略默默地丢弃无法处理的任务，不与任何处理，如果允许任务丢失，比较最好的一个方案。
 *
 *    r : 为请求执行的任务
 *    executor : 为当前的线程池
 *
 */
public class RejectThreadPoolDemo {
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

    public static void main(String[] args) throws InterruptedException{
        MyTask task = new MyTask();
        ExecutorService es = new ThreadPoolExecutor(5,5,
                0L,TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(10),
                Executors.defaultThreadFactory(),
                new RejectedExecutionHandler(){
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        System.out.println(r.toString() + "is discard");
                    }
                });
        for(int i = 0; i < Integer.MAX_VALUE; i++){
            es.submit(task);
            Thread.sleep(10);
        }
    }
}
