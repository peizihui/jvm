package com.bjut.JVMTest;

public class Test01 {
    // 类加载器
    public static void main(String[] args) throws ClassNotFoundException {

        // 启动类加载器 (Boostrap) C++  是null
        Object obj = new Object();
        System.out.println("*****:" + obj.getClass().getClassLoader());
        System.out.println();

        // 应用程序类加载器(App) Java 也叫系统类加载器,加载当前应用的classpath的所有类
        Class clazz = Class.forName("com.bjut.Thread2Test.MyHello");
        System.out.println("*****:" + clazz.getClassLoader());
        System.out.println();

        // 拓展类加载器(Extesion) Java 是sun.misc.Launcher$AppClassLoader@4e25154f
        Test01 test = new Test01();
        System.out.println("*****:" + test.getClass().getClassLoader());

        // native关键字 可以调用c语言和底层语言,但是用的越来越少了
        // native放在native方法栈中
        new Thread().start();
    }
}
