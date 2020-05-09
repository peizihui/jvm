package com.bjut.JVMTest;

public class StackDemo {
    public static void sayHello() {
        sayHello();
    }

    // 1.main方法会先放进栈里
    // 2.然后调用sayHello 进栈
    // 3.递归调用 栈报错Exception in ThreadTest "main" java.lang.StackOverflowError
    /*
     *   .class -> ClassLoader ->
     *   java8不在有永久代(PSPermGen) 变成了元空间(Metaspace)
     *
     *
     * */
    public static void main(String[] args) {
        sayHello();
    }
}
