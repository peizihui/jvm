package com.bjut.BingfaTest;

public class DrawThread2 extends Thread {

    @Override
    public void run() {
        Account2 account2 = new Account2("1234567", 1000);
        // 模拟两个线程对同一个账户取钱
        account2.draw(600);
    }

    public static void main(String[] args) {
        DrawThread2 drawThread2 = new DrawThread2();
        drawThread2.start();
        drawThread2.start();
    }
}
