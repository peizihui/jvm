package com.bjut.ImmutableClass;

import java.util.Date;

/*
 *  不可变类：指当创建了这个类的实例后，就不允许修改它的值了。（一个对象一旦被创建出来，在其整个生命周期中，它的成员变量就不能修改了。）
 *    **** 1.类中所有的变量被private所修饰 ****
 *    **** 2.类中没有写或者修改成员变量的方法，（例如setXXX，只提供构造方法，一次生成，永不改变） ****
 *    **** 3.确保类中所有的方法不被子类覆盖（把类定义成final） ****
 *    **** 4.如果一个类不是不可变量，那么在成员变量初始化或者使用get方法获取该成员变量时，需要通过clone方法来确保类的不可变性 ****
 *   5.
 *
 *   优点：使用简单，线程安全，节省内存
 *   缺点：不可变类的对象会因为值的不同而产生新的对象
 *
 * */
class ImmutableClass {
    private Date d;

    public ImmutableClass(Date d) {
        this.d = (Date) d.clone(); // 解除了引用关系
    }

    public void printState() {
        System.out.println(d);
    }

    public Date getDate() {
        return (Date) d.clone();
    }
}

public class Test {
    public static void main(String[] args) {
        Date d = new Date();
        ImmutableClass immuC = new ImmutableClass(d);
        immuC.printState();
        d.setMonth(5);
        immuC.printState();
    }
}

