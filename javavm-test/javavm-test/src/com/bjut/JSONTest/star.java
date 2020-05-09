package com.bjut.JSONTest;

public class star {
    public static void main(String args[]) {
        int num = 10;
        int n = num * 2 - 1;
        System.out.println(sizeOfInt(n));
        for (int i = 1; i < num; i++) {
            for (int j = 1; j < num - i; j++) {
                for (int p = 0; p < sizeOfInt(n); p++) {
                    System.out.print(" ");
                }
                System.out.print(" ");
            }
            for (int k = 1; k <= 2 * i - 1; k++) {
                System.out.print(i * 2 - 1);
                for (int p = 0; p < sizeOfInt(n) - sizeOfInt(i * 2 - 1) + 1; p++) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    final static int[] sizeTable = {9, 99, 999, 9999, 99999, 999999, 9999999, 99999999, 999999999, Integer.MAX_VALUE};

    static int sizeOfInt(int x) {
        for (int i = 0; ; i++)
            if (x <= sizeTable[i])
                return i + 1;
    }
}
