package com.bjut.JVMTest;

/*
 *    1）装载：根据查找路径找到相对应的class文件，然后导入
 *    2）链接：链接又可以分为3个小的步骤，具体如下。
 *       1）检查：检查待加载的class文件的正确性。
 *       2）准备：给类中的静态变量分配存储空间。
 *       3）解析：将符号引用转换成直接引用。
 *    3）初始化：对静态变量和静态代码块执行初始化工作。
 * */
public class LoaderTest {
    public static void main(String[] args) {

        // 调用class加载器
        ClassLoader clApp = LoaderTest.class.getClassLoader();
        System.out.println(clApp);

        // 调用上一层Class加载器
        ClassLoader clExt = clApp.getParent();
        System.out.println(clExt);

        // 调用根部Class加载器
        ClassLoader clBoot = clExt.getParent();
        System.out.println(clBoot);

    }
}
