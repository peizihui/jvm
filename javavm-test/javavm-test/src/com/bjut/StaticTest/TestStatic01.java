package com.bjut.StaticTest;

public class TestStatic01 {
    public static int staticInt = 0;
    public int nonStaticInt = 0;

    public static void main(String[] args) {
        TestStatic01 t = new TestStatic01();
        System.out.println("t.staticInt = " + t.staticInt);
        System.out.println("TestStatic01.staticInt = " + TestStatic01.staticInt);
        System.out.println("t.nonStaticInt = " + t.nonStaticInt);

        System.out.println("对静态变量和实例变量分别 + 1");
        t.staticInt++;
        t.nonStaticInt++;

        TestStatic01 t1 = new TestStatic01();
        System.out.println("t1.staticInt = " + t1.staticInt);// 所以静态变量只有一个，被类拥有，不能调用非static方法
        System.out.println("TestStatic01.staticInt = " + TestStatic01.staticInt);
        System.out.println("t1.nonStaticInt = " + t1.nonStaticInt);// 实例变量是和具体对象相关的
    }
}
