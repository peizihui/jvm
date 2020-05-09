package com.bjut.StringTest;

import java.util.StringTokenizer;

/*
 * StringTokenizer是用来分割字符串的工具类
 * */
public class StringTokenizerTest {
    public static void main(String[] args) {
        StringTokenizer st = new StringTokenizer("Welcome to our country");
        while (st.hasMoreTokens()) {
            System.out.println(st.nextToken());
        }
    }
}
