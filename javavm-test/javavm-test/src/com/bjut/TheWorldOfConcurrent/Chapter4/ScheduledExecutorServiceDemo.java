package com.bjut.TheWorldOfConcurrent.Chapter4;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class  ScheduledExecutorServiceDemo {
    private static  ScheduledExecutorService schedule =
            new ScheduledThreadPoolExecutor(1, new ThreadFactoryBuilder().setNameFormat("scheduled-%d").build());
    public static void main(String[] args) {
        // 禁止使用Timer，一律使用ScheduledExecutorService
        /*
         *  创建并执行一个在给定初始延迟后首次启用的定期操作，后续操作具有给定的周期；
         *  也就是将在 initialDelay 后开始执行，然后在 initialDelay+period 后执行，接着在 initialDelay + 2 * period 后执行，依此类推。
         */
        schedule.scheduleAtFixedRate(new test(), 0L, 1L, TimeUnit.SECONDS);
    }
}
