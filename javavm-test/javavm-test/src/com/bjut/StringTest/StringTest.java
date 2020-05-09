package com.bjut.StringTest;

public class StringTest {
    public static void main(String[] args) {
        String s = "Welcome to our country";
        String[] arr = s.split(" ");
        for (String s1 : arr) {
            System.out.println(s1);
        }
    }
}
