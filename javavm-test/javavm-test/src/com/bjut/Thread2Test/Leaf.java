package com.bjut.Thread2Test;

public class Leaf {
    private int i = 0;

    Leaf increment() {
        i++;
        return this;
    }

    void print() {
        System.out.println("i = " + i);
    }

    public static void main(String[] args) {
        Leaf x = new Leaf();
        x.increment().print();
        x.increment().increment().print();
        x.increment().increment().increment().print();
        x.increment().increment().increment().increment().print();
    }
}
