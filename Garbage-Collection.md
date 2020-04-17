Garbage Collection



# 1.how to find a garbage

##### 1.1      reference count 

##### 

![find-garbage](http://q8xc9za4f.bkt.clouddn.com/find-garbage.png)



- reference count  为0； 引用基数；			   引用数量为0代表其为垃圾

  ### 1.2  RC can't resolve

  ![](http://q8xc9za4f.bkt.clouddn.com//corejavarc-cant-resolve.png)

  

  对于“环形垃圾”是不可以的

  

  ### 1.3 Root  Searching

  ![](http://q8xc9za4f.bkt.clouddn.com//corejavaroot-searching.png)

  



根可以有很多个，但是同个方法里面，数量有限



从根上开始找，凡是可以找得到的都是有用的

但是从根上找不到的话，那就是流离状态，就是垃圾

，

# 2. GC Algorithms

### 2.1 Mark-Sweep（标记清除）

![](http://q8xc9za4f.bkt.clouddn.com//corejavaMark-Sweep.png)



1. 找到垃圾；
2. 直接标记为可用；
3. 问题，碎片比较多；

# 2.2 Copying （拷贝、复制）



![](http://q8xc9za4f.bkt.clouddn.com//corejavacopying.png)

1. 内存一分为2；方法非常高效；
2. 回收垃圾，把上面有用的copy过来；(灰色代表有用的，把灰色备份copy到下面)
3. 缺点： 浪费内存；

### 2.3 Mark-Compact (标记压缩)

1. 首先标记出来内存，如黑色部分；



![](http://q8xc9za4f.bkt.clouddn.com//corejavamrak-compact-1.png)

2. 标记内存之后把有用的内存顶上去；

![](http://q8xc9za4f.bkt.clouddn.com//corejavamrak-compact-2.png)



3. 把下面部分回收掉；类似

![](http://q8xc9za4f.bkt.clouddn.com//corejavamrak-compact-3.png)





总结：

1. 不产生碎片；
2. 不浪费内存；
3. 缺点：效率偏低；

# 3. Garbage Collectors

## 3.1  回收器总概览

## 

![](http://q8xc9za4f.bkt.clouddn.com//corejavaGarbage-collectors-overvies.png)



1. 以上10种垃圾回收器；

2. 运用以上3中算法；

3. 前6种垃圾回收器配合使用；

   1.  ParNew /CMS ,

   2. Serial /Serial Old ,

   3. Parallel Scavenge /Parallel Old 配合使用，v1.8常用；

   4. 责任图

      | 类别                            |      |                |                                                              |
      | ------------------------------- | ---- | -------------- | ------------------------------------------------------------ |
      | ParNew/CMS                      |      | MUQT           |                                                              |
      | Serial /Serial Old              |      | JDK1.0 最简单  | 单线程，内存10-100M,太大STW时间就比较长，（正在清理，稍后再用） |
      | Parallel Scavenge /Parallel Old |      | 使用最多，推荐 | 多线程                                                       |

##  3.2 垃圾回收器介绍

   ### 3.2.1 Serial     

   safe point  停止所有线程（stop-the-world），开始清理工作;

   -  A stop-the-world,coppying collector which uses a single GC thread;

     ![](http://q8xc9za4f.bkt.clouddn.com//corejavaSerial.png)

     

   

   ###   3.2.2 Serial Old  

   1.  a stop-the-world mark-sweep-compact collector that uses a single GC thread;

      ![](http://q8xc9za4f.bkt.clouddn.com//corejavaSerial-Old.png)

### 3.2.3 Parallel Scavenge

### ![](http://q8xc9za4f.bkt.clouddn.com/corejava/Parallel-Scavenge.png)  

      

### 3.2.4 Parallel Old

![](http://q8xc9za4f.bkt.clouddn.com/corejava/Parallel-Old.png)

    

###3.2.5 CMS    

![](http://q8xc9za4f.bkt.clouddn.com/corejava/CMS.png)

    
    
     里程碑的垃圾回收区，工作在老年代；


| 类别                          |                        |      |
| ----------------------------- | ---------------------- | ---- |
| 初始标记（initial mark）      | 找到root,时间非常短    |      |
| 并发标记（concurrent mark）   | 线程继续，同时开始标记 |      |
| 重新标记 （remark）           | 找到漏标，错标         |      |
| 并发清理 （concurrent sweep） |                        |      |

####3.2.5.1  初始标记

   ![](http://q8xc9za4f.bkt.clouddn.com/corejava/initial-mark.png)

   

####3.2.5.2 重新标记

   - 由于前阶段存在漏标，错标问题修正  
   -  STW，需要停止然后标记，避免漏标，错标；

​    

   ![](http://q8xc9za4f.bkt.clouddn.com/corejava/remark.png)

   

   

####3.2.5.3  并发标记

   

   ![](http://q8xc9za4f.bkt.clouddn.com/corejava/concurrent-mark.png)

   

   - concurrent mark sweep  

   - a mostly concurrent ,low-pause collector

   - 4 phases

     1. initial mark

     2. concurrent mark

     3. remark

     4. concurrent sweep

        

        

        

   ### 3.2.6 G1

   ![image-20200417154715711](F:\daily shell\jvm\images\Garbage-collection\G1.png)

   

   ### 

   ​			

# 4.总结

   ![](http://q8xc9za4f.bkt.clouddn.com/corejava/stack-logics.png)

1. ​    新生代和老年代进行划分的好处？	

     效率更高；

2.   新生代老年代分别存放什么对象？及使用什么回收算法；

      ![](http://q8xc9za4f.bkt.clouddn.com/corejava/stack-logics-2.png)

   

   

   

    	 问：新生代里面10个对象，需要回收9个，保留1个，如何做到高效回收？
    
    	 答:  把那个有用的挑出来（copying）；eden -----------> survivor ，调到（survivor）幸存区；然后清理整个eden区域；  

   ​			对应survivor 区域也使用相同的机制，循环往复；多次未被回收的对象，最终会进入tenured(终身代/老年代)；

   

   

| 类别   | 备注                       | 存放何种对象                                         | 回收算法                 |
| ------ | -------------------------- | ---------------------------------------------------- | ------------------------ |
| 新生代 | 新创建的对象比较容易回收， | 1.刚new对象；                                        | coping                   |
| 老年代 |                            | 1.回收一次，年龄被标注一次，当达到一定年龄进入老年代 | MarkCompact  ,Mark Sweep |
|        |                            |                                                      |                          |

   

   ![](http://q8xc9za4f.bkt.clouddn.com/corejava/object-from-birth-to-death.png)

   

3. ​    为什么划分之后效率更高？

   ​	 不同的分代存储不同的对象，不同对象使用不同的算法，这样效率会更高；

   

4. 

   ![](http://q8xc9za4f.bkt.clouddn.com/corejava/object-from-birth-to-death-2.png)



5. 

![](http://q8xc9za4f.bkt.clouddn.com/corejava/object-from-birth-to-death-3.png)



s1 满了，年龄不够怎么办？





# 5.调优实战；



























[^www.mashibing.com]: www.mashibing.com

