package com.bjut.BingfaTest;

public class DrawThread extends Thread {
    // 模拟用户账户
    private Account account;
    // 当前取钱线程所希望取的钱数
    private double drawAmount;

    public DrawThread(String name, Account account, double drawAmount) {
        super(name);
        this.account = account;
        this.drawAmount = drawAmount;
    }
    // 当前线程修改同一个共享数据时，将涉及数据安全问题

    @Override
    public void run() {
        // 账户余额大于取钱数目
        if (account.getBalance() >= drawAmount) {
            // 吐出钞票
            System.out.println(getName() + "取钱成功！吐出钞票：" + drawAmount);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            /*
             * 修改金额
             * */
            account.setBalance(account.getBalance() - drawAmount);
            System.out.println("\t 余额为：" + account.getBalance());
        } else {
            System.out.println(getName() + "取钱失败！ 余额不足");
        }
    }
}
