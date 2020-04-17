Garbage Collection



# 1.how to find a garbage

##### 1.1

![image-20200417085655857](C:\Users\will\AppData\Roaming\Typora\typora-user-images\image-20200417085655857.png)



- reference count  为0； 应用基数；

  ### 1.2  RC can't resolve

  ![image-20200417091149389](C:\Users\will\AppData\Roaming\Typora\typora-user-images\image-20200417091149389.png)

  

  对于“环形垃圾”是不可以的

  

  ### 1.3 Root  Searching

  

![image-20200417092823199](C:\Users\will\AppData\Roaming\Typora\typora-user-images\image-20200417092823199.png)

根可以有很多个，但是同个方法里面，数量有限



从根上开始找，凡是可以找得到的都是有用的

但是从根上找不到的话，那就是流离状态，就是垃圾

，

# 2. GC Algorithms

### 2.1 Mark-Sweep（标记清除）

![image-20200417101816522](C:\Users\will\AppData\Roaming\Typora\typora-user-images\image-20200417101816522.png)



1. 找到垃圾；
2. 直接标记为可用；
3. 问题，碎片比较多；

# 2.2 Copying （拷贝、复制）



![image-20200417102145630](C:\Users\will\AppData\Roaming\Typora\typora-user-images\image-20200417102145630.png)

1. 内存一分为2；方法非常高效；
2. 回收垃圾，把上面有用的copy过来；(灰色代表有用的，把灰色备份copy到下面)
3. 缺点： 浪费内存；

### 2.3 Mark-Compact (标记压缩)

1. 首先标记出来内存，如黑色部分；

![image-20200417103628834](C:\Users\will\AppData\Roaming\Typora\typora-user-images\image-20200417103628834.png)

2. 标记内存之后把有用的内存顶上去；

![image-20200417103746525](C:\Users\will\AppData\Roaming\Typora\typora-user-images\image-20200417103746525.png)



3. 把下面部分回收掉；类似

![image-20200417103908244](C:\Users\will\AppData\Roaming\Typora\typora-user-images\image-20200417103908244.png![image-20200417104013506](C:\Users\will\AppData\Roaming\Typora\typora-user-images\image-20200417104013506.png)





总结：

1. 不产生碎片；
2. 不浪费内存；
3. 缺点：效率偏低；

# 3. Garbage Collectors

## 3.1  回收器总概览

## 

![image-20200417104405664](C:\Users\will\AppData\Roaming\Typora\typora-user-images\image-20200417104405664.png)



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

   ## 3.2 垃圾回收器结算

   ### 3.2.1 Serial     

   safe point  停止所有线程（stop-the-world），开始清理工作;

   -  A stop-the-world,coppying collector which uses a single GC thread;

     ![image-20200417115812136](C:\Users\will\AppData\Roaming\Typora\typora-user-images\image-20200417115812136.png)

   

   ###   3.2.2 Serial Old  

   1.  a stop-the-world mark-sweep-compact collector that uses a single GC thread;

      ![image-20200417120653173](C:\Users\will\AppData\Roaming\Typora\typora-user-images\image-20200417120653173.png)

      ## 3.2.3 Parallel Scavenge   

        ![image-20200417120929806](C:\Users\will\AppData\Roaming\Typora\typora-user-images\image-20200417120929806.png)

      ### 3.2.4 Parallel Old

      ![image-20200417135610365](C:\Users\will\AppData\Roaming\Typora\typora-user-images\image-20200417135610365.png)

      ## 3.2.5 CMS    

   ![image-20200417143210007](C:\Users\will\AppData\Roaming\Typora\typora-user-images\image-20200417143210007.png) 

     里程碑的垃圾回收区，工作在老年代；

   

    

   | 类别                        |                        |      |
   | --------------------------- | ---------------------- | ---- |
   | 初始标记（initial mark）    | 找到root,时间非常短    |      |
   | 并发标记（concurrent mark） | 线程继续，同时开始标记 |      |
   | 重新标记 （remark）         | 找到漏标，错标         |      |
   | 并发清理                    |                        |      |

   ##  **初始标记**

   ![初始标记](F:\daily shell\jvm\images\initial-mark.png)

   

   **重新标记**

   - 由于前阶段存在漏标，错标问题修正  
   -  STW，需要停止然后标记，避免漏标，错标；

    

   ![image-20200417152404230](C:\Users\will\AppData\Roaming\Typora\typora-user-images\image-20200417152404230.png)

   

   

   **并发标记**

   

   ![image-20200417150012000](C:\Users\will\AppData\Roaming\Typora\typora-user-images\image-20200417150012000.png)

   

   - concurrent mark sweep  

   - a mostly concurrent ,low-pause collector

   - 4 phases

     1. initial mark

     2. concurrent mark

     3. remark

     4. concurrent sweep

        

        

        

   1. 

   

   ### 3.1 分代

   ![image-20200417104656045](C:\Users\will\AppData\Roaming\Typora\typora-user-images\image-20200417104656045.png)

1. ​    新生代和老年代进行划分的好处？	

     效率更高；

2.   新生代老年代分别存放什么对象？及使用什么回收算法；

      

   

   ![image-20200417110959258](C:\Users\will\AppData\Roaming\Typora\typora-user-images\image-20200417110959258.png)

   

    	 问：新生代里面10个对象，需要回收9个，保留1个，如何做到高效回收？

    	 答:  把那个有用的挑出来（copying）；eden -----------> survivor ，调到（survivor）幸存区；然后清理整个eden区域；  

   ​			对应survivor 区域也使用相同的机制，循环往复；多次未被回收的对象，最终会进入tenured(终身代/老年代)；

   

   

   | 类别   | 备注                       | 存放何种对象                                         | 回收算法                 |
   | ------ | -------------------------- | ---------------------------------------------------- | ------------------------ |
   | 新生代 | 新创建的对象比较容易回收， | 1.刚new对象；                                        | coping                   |
   | 老年代 |                            | 1.回收一次，年龄被标注一次，当达到一定年龄进入老年代 | MarkCompact  ,Mark Sweep |
   |        |                            |                                                      |                          |

   

   ![image-20200417113347150](C:\Users\will\AppData\Roaming\Typora\typora-user-images\image-20200417113347150.png)

   

3. ​    为什么划分之后效率更高？

   ​	 不同的分代存储不同的对象，不同对象使用不同的算法，这样效率会更高；

   

4. ![image-20200417114129841](C:\Users\will\AppData\Roaming\Typora\typora-user-images\image-20200417114129841.png)

   



5. 

![image-20200417114453143](C:\Users\will\AppData\Roaming\Typora\typora-user-images\image-20200417114453143.png)



s1 满了，年龄不够怎么办？

