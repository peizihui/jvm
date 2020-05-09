package com.bjut.StaticTest;

/*
 *  static内部类是指被声名为static的内部类
 *  它可以不依赖于外部类实例对象而被实例化
 *  通常需要在外部类实例化之后才能实例化
 *
 * */
public class Outer {
    static int n = 5;

    static class Inner {
        void accessAttrFromOuter() {
            System.out.println("Inner:Outer.n = " + n);
        }
    }

    public static void main(String[] args) {
        Outer.Inner nest = new Outer.Inner();
        nest.accessAttrFromOuter();
    }
}
