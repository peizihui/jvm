​		#1. volatile 五层实现



![image-20200424205826205](http://q8xc9za4f.bkt.clouddn.com/cloudflare/image-20200424205826205.png)







![image-20200424203622606](http://q8xc9za4f.bkt.clouddn.com/cloudflare/image-20200424203622606.png)















- MESI 
- 



# 3 .  缓存一致性协议



![image-20200424203812206](F:\daily shell\jvm\valatile.assets\image-20200424203812206.png)





#  4. CPU乱序执行

![image-20200424204515495](http://q8xc9za4f.bkt.clouddn.com/cloudflare/image-20200424204515495.png)

# 5. 禁止乱序执行



- fence

- barrier

  ## 5.1 JSR 内存屏障、

  1. LoadLoad

  2. StoreStore

  3. LoadStore

  4. StoreLoad

     Lord  read

     Store write

     

## 5.2 volatile 的实现细节、

![image-20200424205241891](http://q8xc9za4f.bkt.clouddn.com/cloudflare/image-20200424205241891.png)





## 5.3  有序行保障

![image-20200424205554542](http://q8xc9za4f.bkt.clouddn.com/cloudflare/image-20200424205554542.png)

## 5.4

![image-20200424205649374](http://q8xc9za4f.bkt.clouddn.com/cloudflare/image-20200424205649374.png)