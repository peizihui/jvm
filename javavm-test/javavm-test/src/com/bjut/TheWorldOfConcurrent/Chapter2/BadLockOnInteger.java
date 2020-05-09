package com.bjut.TheWorldOfConcurrent.Chapter2;

/**
 * 为了确保数据正确性，我们自然会需要对计数器加锁。
 * 解析：
 * 在JAVA中，Integer属于不可变对象，一旦被创建，就不可能被修改
 * Integer.valueOf()实际上是一个工厂方法，它将倾向于返回一个代表指定数值的Integer实例。
 * 因此，i++的本质是，创建一个新的Integer对象，并将它的引用赋值给i
 */
public class BadLockOnInteger implements Runnable {
    public static Integer i = 0;
    static BadLockOnInteger instance = new BadLockOnInteger();

    @Override
    public void run() {
        for (int j = 1; j < 10000000; j++) {
            synchronized (i) {
                i++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(i);
    }
}
