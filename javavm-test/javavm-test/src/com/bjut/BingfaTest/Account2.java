package com.bjut.BingfaTest;

/*
 *   直接调用account对象的draw方法来执行取钱操作
 *   同步方法的同步监视器是this，this代表调用draw方法的对象
 *   也就是说，线程进入draw方法之前，必须先对account对象枷锁
 *    枷锁  -》 修改 -》 释放锁
 * */
public class Account2 {
    // 封装账户编号、账户余额 的两个成员变量
    private String accountNo;
    private double balance;

    public Account2() {
    }

    // 构造器
    public Account2(String accountNo, double balance) {
        this.accountNo = accountNo;
        this.balance = balance;
    }

    // 提供一个线程安全的draw方法来完成取钱操作
    public synchronized void draw(double drawAmount) {
        // 账户余额大于取钱数目
        if (balance >= drawAmount) {
            // 吐出钞票
            System.out.println(Thread.currentThread().getName() + "取钱成功！吐出钞票：" + drawAmount);

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //修改金额
            balance -= drawAmount;
            System.out.println("\t 余额为：" + balance);
        } else {
            System.out.println(Thread.currentThread().getName() + "取钱失败！余额不足！");
        }
    }


    // 不提供setter方法，不可以随便修改余额，只需要getBalance方法
    public double getBalance() {
        return this.balance;
    }


    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }
}
