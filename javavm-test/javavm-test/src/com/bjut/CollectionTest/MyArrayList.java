package com.bjut.CollectionTest;

/*
 * 手写ArrayList
 * Collection<--List<--ArrayList
 * */
public class MyArrayList {
    private Object[] value = null;
    private int size = 0;

    MyArrayList() {
        value = new Object[10];
    }

    public boolean add(Object obj) {
        if (size == value.length) {
            expansion();
        }
        value[size++] = obj;
        return true;
    }

    public Object get(int index) {
        return value[index];
    }

    public void remove(Object obj) {
        Object[] obj2 = new Object[size];
        int index = 0;
        int id = 0;
        for (int i = 0; i <= size; i++) {
            if (!(value[i].toString().equals(obj.toString()))) {
                obj2[index] = value[i];
                index++;
            } else {
                id++;
                if (id == 1)
                    size--;
                else {
                    obj2[index] = value[i];
                    index++;
                }
            }
        }
        value = obj2;
    }

    public void set(int index, Object obj) {
        Object[] newObj = new Object[size];
        ;
        for (int i = 0; i < size; i++) {
            if (i == index)
                newObj[i] = obj;
            else
                newObj[i] = value[i];
        }
        value = newObj;
    }

    public int size() {
        return size;
    }

    /*
     * 注意：clone只对一维数组起作用，而不能用于二维数组， 因为java没有二维数组的概念，而只有数组的数组，
     * 二维数组存储的是几个一维数组的引用，而使用clone也只是 拷贝了这几个引用，说白了还是原来那几个一维数组对象。
     * 如果想用于二维数组，那么就遍历其中的一维数组，挨个拷贝一维数组到目标二维数组中的一维数组下。
     * */
    private boolean expansion() {
        Object[] temp = new Object[value.length + 5];
        temp = value.clone();
        value = temp;
        return true;
    }

    public void clear() {
        size = 0;
        value = null;
    }

    public static void main(String[] args) {
        MyArrayList ma = new MyArrayList();
        ma.add("hello");
        ma.add("world");
        ma.add("java");
        System.out.println(ma.get(1));
        System.out.println(ma.size());
        ma.set(1, "new");
        System.out.println(ma.get(1));
        System.out.println(ma.size());
    }
}
