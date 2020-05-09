package com.bjut.SingletonTest;

public class Singleton {
    private Singleton() {
    }

    private static Singleton instance;

    public Singleton getInstance() {
        return instance;
    }
}
