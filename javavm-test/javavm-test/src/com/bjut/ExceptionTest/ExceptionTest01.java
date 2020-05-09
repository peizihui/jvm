package com.bjut.ExceptionTest;

/*
 *   finally先执行，
 * */
public class ExceptionTest01 {
    public static int testFinally() {
        try {
            return 1;
        } catch (Exception e) {
            return 0;
        } finally {
            System.out.println("execute finally");
        }
    }

    public static void main(String[] args) {
        int result = testFinally();
        System.out.println(result);
    }
}
