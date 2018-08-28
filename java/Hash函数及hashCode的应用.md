### 什么是Hash

* Hash function：A hash function is any function that can be used to map data of arbitrary(任意的) size to data of fixed(固定的) size. The values returned by a hash function are called hash values, hash codes, digests, or simply hashes. One use is a data structure called a hash table, widely used in computer software for rapid data lookup.<br/><br/>
哈希函数就是能将任意长度的数据映射为固定长度的数据的函数。哈希函数返回的值被叫做哈希值、哈希码、散列，或者直接叫做哈希。一个使用场景就是哈希表，哈希表被广泛用于快速搜索数据。

* Hash table: a hash table (hash map) is a data structure which implements an associative array abstract data type, a structure that can map keys to values. A hash table uses a hash function to compute an index into an array of buckets or slots, from which the desired value can be found. <br><br>
哈希表是一种能实现关联数组的抽象数据结构，能把很多「键」映射到很多「值」上。哈希表使用哈希函数来计算索引，一个索引对应一个值。

* hashCode方法的主要作用是为了配合基于散列的集合一起正常运行，这样的散列集合包括HashSet、==HashMap==以及HashTable。

### 构造Hash函数的几种方法
* **直接定址法** 取关键字或者关键字的某个线性函数为Hash地址，即address(key)=a*key+b;如知道学生的学号从2000开始，最大为4000，则可以将address(key)=key-2000作为Hash地址。

* **平方取中法** 对关键字进行平方运算，然后取结果的中间几位作为Hash地址。假如有以下关键字序列{421，423，436}，平方之后的结果为{177241，178929，190096}，那么可以取{72，89，00}作为Hash地址。

* **折叠法** 将关键字拆分成几部分，然后将这几部分组合在一起，以特定的方式进行转化形成Hash地址。假如知道图书的ISBN号为8903-241-23，可以将address(key)=89+03+24+12+3作为Hash地址。

* **除留取余法** 如果知道Hash表的最大长度为m，可以取不大于m的最大质数p，然后对关键字进行取余运算，address(key) = key % p。在这里p的选取非常关键，p选择的好的话，能够最大程度地减少冲突，p一般取不大于m的最大质数。

### 冲突解决

* **开放定址法** 即当一个关键字和另一个关键字发生冲突时，使用某种探测技术在Hash表中形成一个探测序列，然后沿着这个探测序列依次查找下去，当碰到一个空的单元时，则插入其中。
<br/>
比较常用的探测方法有线性探测法，比如有一组关键字{12，13，25，23，38，34，6，84，91}，Hash表长为14，Hash函数为address(key)=key%11，当插入12，13，25时可以直接插入，而当插入23时，地址1被占用了，因此沿着地址1依次往下探测(探测步长可以根据情况而定)，直到探测到地址4，发现为空，则将23插入其中。

* **链地址法** 采用数组和链表相结合的办法，将Hash地址相同的记录存储在一张线性表中，而每张表的表头的序号即为计算得到的Hash地址。

### hashCode()方法与equals()方法

* 在程序执行期间，只要equals方法的比较操作用到的信息没有被修改，那么对这同一个对象调用多次，hashCode方法必须始终如一地返回同一个整数。

* 如果两个对象根据equals方法比较是相等的，那么调用两个对象的hashCode方法必须返回相同的整数结果。

* 如果两个对象根据equals方法比较是不等的，则hashCode方法不一定得返回不同的整数。

* 设计hashCode()时最重要的因素就是：无论何时，对同一个对象调用hashCode()都应该产生同样的值。如果在讲一个对象用put()添加进HashMap时产生一个hashCdoe值，而用get()取出时却产生了另一个hashCode值，那么就无法获取该对象了。所以如果你的hashCode方法依赖于对象中易变的数据，用户就要当心了，因为此数据发生变化时，hashCode()方法就会生成一个不同的散列码”。

