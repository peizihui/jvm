package com.bjut.JVMTest;

/*
 *  堆内存调优简介:
 *
 *  -Xms 设置初始分配大小,默认为物理内存的"1/64"
 *  -Xmx 最大分配内存,默认为物理内存的"1/4"
 *  -XX:+PrintGCDetails 输出详细的处理日志
 *
 *  堆内存(JVM Heap)永远只到养老区
 * */
public class Test02 {
    public static void main(String[] args) {
        long maxMemory = Runtime.getRuntime().maxMemory();// 返回Java 虚拟机试图使用的最大内存量
        long totalMemory = Runtime.getRuntime().totalMemory(); //返回Java 虚拟机中的内存总量

        System.out.println("MAX_MEMORY = " + maxMemory + "(字节)" + (maxMemory / (double) 1024 / 1024) + "MB");
        System.out.println("TOTAL_MEMORY = " + totalMemory + "(字节)" + (totalMemory / (double) 1024 / 1024) + "MB");
    }
}
