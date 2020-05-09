package com.bjut.JVMTest;

public class GCTest {
    public static void main(String[] args) {
        Object o = new Float(3.14F);
        Object[] oa = new Object[1];
        oa[0] = o;
        o = null; // 此时o会自动被GC回收
        oa[0] = null;
    }
}
