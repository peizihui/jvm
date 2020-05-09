package com.bjut.ExceptionTest;

public class ExceptionTest02 {
    public static int testFinally() {
        try {
            return 1;
        } catch (Exception e) {
            return 0;
        } finally {
            System.out.println("execute finally");
            return 3;
        }
    }

    public static void main(String[] args) {
        int result = testFinally();
        System.out.println(result);
    }
}
