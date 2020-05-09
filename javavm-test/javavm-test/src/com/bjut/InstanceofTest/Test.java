package com.bjut.InstanceofTest;

public class Test {
    public static void main(String[] args) {
        String s = "Hello";
        int[] a = {1, 2};
        if (s instanceof String)
            System.out.println("true");
        if (s instanceof Object)
            System.out.println("true");
        if (a instanceof int[])
            System.out.println("true");
    }
}
