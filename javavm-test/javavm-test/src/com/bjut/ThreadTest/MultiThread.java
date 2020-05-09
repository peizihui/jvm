package com.bjut.ThreadTest;

/*
 *  在静态方法上加上synchronzied关键字，表示锁定.class类，类一级别的锁（独占.class类）
 *  static synchronzied 两个线程有先后的顺序
 *  */
public class MultiThread {
    private static int num = 0;

    /* 当没有static修饰时，不是类级别的锁，两个类运行时会乱序
     * 总结： 关键字synchronized取得的锁都是对象锁，而不是把一段代码（方法）当作锁
     *       所以示例代码中那个线程先执行synchronized关键字的方法，那个线程就持有该
     *       方法所属对象的锁（Lock），两个对象，线程获得的就是不同的锁，他们互不影响。
     *           有一种情况则是相同的锁，即在静态方法上加synchronized关键字，表示锁
     *           定.class类，类一级别的锁（独占.class类）
     * */
    public static synchronized void printNum(String tag) {
        try {
            if (tag.equals("a")) {
                num = 100;
                System.out.println("tag a , set num over !");
                Thread.sleep(1000);
            } else {
                num = 200;
                System.out.println("tag b , set num over !");
            }
            System.out.println("tag = " + tag + ", num = " + num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 注意观察run方法输出顺序
    public static void main(String[] args) {
        // 两个不同的对象
        final MultiThread m1 = new MultiThread();
        final MultiThread m2 = new MultiThread();

        Thread t1 = new Thread(() -> m1.printNum("a"));
        Thread t2 = new Thread(() -> m2.printNum("b"));
        t1.start();
        t2.start();
    }
}
