package com.bjut.StreamTest;

import java.io.*;

/*
 *   在java中，输入和输出都被称为抽象的流：根据处理数据类型的不同，常见的流可以分为两大类，字节流和字符流（java.io包中还有很妒忌）
 *   字节流：以字节（8bit）为单位，包含两个抽象类：InputStream（输入流）和OutputStream（输出流）
 *   字符流：以字符（16bit）为单位，根据码表映射字符，一次可以读多个字节，包含两个抽象类：Reader（输入流）和Writer（输出流）
 *
 *   以下是一个输入流类，该类的作用为在符文剑时把文件中的大写字母转换成小写字母，小写字母转大写字母
 *   在设计中，可以通过抽象修饰者类（FilterInputStream），通过调用InputStream类或者子类提供的一些方法再加上逻辑判断
 *
 * */
class MyOwnInputStream extends FilterInputStream {
    public MyOwnInputStream(InputStream in) {
        super(in);
    }

    public int read() throws IOException {
        int c = 0;
        if ((c = super.read()) != -1) {
            // 把小写转换成大写
            if (Character.isLowerCase((char) c))
                return Character.toUpperCase((char) c);
            else if (Character.isUpperCase((char) c))
                return Character.toLowerCase((char) c);
                // 如果不是字母，保持不变
            else
                return c;
        } else {
            return -1;
        }
    }
}

public class StreamTest {
    public static void main(String[] args) {

        int c;
        InputStream is;

        {
            try {
                is = new MyOwnInputStream(new BufferedInputStream(new FileInputStream("src/com/bjut/StreamTest/test.txt")));
                while ((c = is.read()) >= 0) {
                    System.out.print((char) c);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
