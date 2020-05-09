package com.bjut.TheWorldOfConcurrent.Chapter1;

/**
 * 原子性：
 * 原子性是指一个操作是不可以中断的。（即使是在多个线程一起执行的时候，一个操作一旦开始，就不会被其他线程干扰）
 * Thread.yield( )方法：
 * 使当前线程从执行状态（运行状态）变为可执行态（就绪状态）。
 * 此程序解释：
 * 对于32位的操作系统，long型的数据读写不是原子性的（因为long有64位）。
 * 所以当两个线程同时对long进行写入的时候，对线程之间的结果是有干扰的。
 * <p>
 * 所以程序会输出很多数据，ReadT读到了不存在的数字。
 * 所以证明了long型的数据的读和写都不是原子性的，多线程之间相互干扰了
 * <p>
 * 分析原因
 * 这几个数的二级制表示
 * +111 = 0000000000000000000000000000000000000000000000000000000001101111
 * -999 = 1111111111111111111111111111111111111111111111111111110000011001
 * +333 = 0000000000000000000000000000000000000000000000000000000010100110
 * -444 = 1111111111111111111111111111111111111111111111111111111001000100
 * -4294967185 = 1111111111111111111111111111111100000000000000000000000001101111
 * <p>
 * 前边是-999或者-444的前32位，后边是+111的后32位，导致结果出错
 * <p>
 * 解决方法 ： 可以使用volatile修饰，告诉编译器，long数据要格外小心
 * valatile保证数据的原子性，但是不能保证i++的原子性操作（第二章的VolatileTask）
 */
public class MultiThreadLong {
    public static long t = 0; // 解决方法 public volatile static long t = 0;

    public static class ChangeT implements Runnable {
        private long to;

        public ChangeT(long to) {
            this.to = to;
        }

        @Override
        public void run() {
            while (true) {
                MultiThreadLong.t = to;
                Thread.yield();
            }
        }
    }

    public static class ReadT implements Runnable {
        @Override
        public void run() {
            while (true) {
                long tmp = MultiThreadLong.t;
                if (tmp != 111L && tmp != -999L && tmp != 333L && tmp != -444L)
                    System.out.println(tmp);
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new ChangeT(111L)).start();
        new Thread(new ChangeT(-999L)).start();
        new Thread(new ChangeT(333L)).start();
        new Thread(new ChangeT(-444L)).start();
        new Thread(new ReadT()).start();
    }
}
