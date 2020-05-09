package com.bjut.SwitchTest;

/*
 *  switch(expr) expr只能是一个枚举常量（内部也是由整形或字符类型实现）或一个整数表达式
 *   long，float，double，String类型不能隐式的转换成int类型
 *
 *   如果一定用long，float，double作为switch的参数，必须强制转化为int型才可以
 *   JAVA 7 switch支持String
 *    原理：case后面的String对象调用hashCode()方法，得到一个int类型的hash值，然后用这个hash值来唯一标识这个case
 *
 *    当匹配时，使用的是String.equals()
 * */
public class TestSwitch {

}
