package com.bjut.StreamTest;

import java.io.File;

/*
 *   File(String pathname) 根据指定的路径创建一个File对象
 *   |------------------------------------------------------------------|
 *   |createNewFile()  |若目录或文件存在，则返回false，否则创建文件或文件夹 |
 *   |delete()         |删除文件或文件夹                                   |
 *   |isFile()         |判断这个对象表示的是否是文件                         |
 *   |isDirectory()    |判断这个对象是否是文件夹                              |
 *   |listFile()       |若对象代表目录，则返回目录中所有文件的File对象          |
 *   |mkdir()          |根据当前对象指定的路径创建目录                          |
 *   |exists()         |判断对象对应的文件是否存在                               |
 *   |-------------------------------------------------------------------------  |
 * */
public class FileTest {
    public static void main(String[] args) {
        File file = new File("C:\\testDir");
        // 判断目录是否存在
        if (!file.exists()) {
            System.out.println("directory is empty");
            return;
        }
        // 寻找file目录下的子目录或文件
        File[] fileList = file.listFiles();
        for (int i = 0; i < fileList.length; i++) {
            if (fileList[i].isDirectory()) {
                System.out.println("directory is :" + fileList[i].getName());
            } else {
                System.out.println("file is :" + fileList[i].getName());
            }
        }
    }
}
