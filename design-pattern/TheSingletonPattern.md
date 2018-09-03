### 定义
  单例模式（Singleton Pattern）是一个比较简单的模式，其定义如下：Ensure a class has only one instance, and provide a global point of access to it.（确保某一个类只有一个实例，而且自行实例化并向整个系统提供这个实例。）
  单例模式是确保一个类只有一个实例，而且自行实例化，同时它向整个系统提供这个实例。单例模式的通用代码如下所示：
  ``` Java
  public class Singleton {

      private static final Singleton INSTANCE = new Singleton();

      private Singleton() {
          // only allow one instance
      }

      public static Singleton getInstance() {
          return INSTANCE;
      }

      public static void doSomething() {
          // other methods should use the keyword "static" as far as possible.
      }
  }
  ```
  上述代码中，第5行的私有构造方法，保证了该类只会产生一个实例。在单例模式类中，如果还需要其它方法，请务必使用`static`关键字。

### 优缺点
  * 优点
  1. 减少系统开销。由于单例模式在内存中只有一个实例，减少了内存开支，特别是一个对象需要频繁地创建、销毁时，而且创建或销毁时性能又无法优化，单例模式的优势就非常明显。
  2. 避免对资源的多重占用。例如一个写文件动作，由于只有一个实例存在内存中，避免对同一个资源文件的同时写操作。
  3. 可以在系统设置全局的访问点，优化和共享资源访问。例如可以设计一个单例类，负责所有数据表的映射处理。
  * 缺点
  1. 单例模式一般没有接口，扩展很困难。
  2. 与单一职责原则有冲突。

### 分类
  主要：饿汉式、懒汉式
  其它：静态内部类式、双重检测锁式、枚举单例式
  <!-- * <font color=#0066CC>饿汉式</font>   -->
  * 饿汉式
    饿汉式，顾名思义，这个“汉子”很饿，需要立即吃“食物”。这里的“汉子”指的就是类，“食物”就是实例化的对象。因此，饿汉式要表达的意思如下：当类一加载的时候，就通过new关键字直接初始化一个实例。很显然，定义中的代码块使用的就是饿汉式。

  * 懒汉式  
    因为比较“懒”，所以它在一开始的时候并不初始化实例。什么时候用，什么时候再进行初始化。通用代码如下所示：
    ``` Java
    public class Singleton {

        private static Singleton INSTANCE = null; // the instance is null.

        private Singleton() {
            // only allow one instance
        }

        public static synchronized  Singleton getInstance() {
            if (INSTANCE == null) { // the first time
                INSTANCE = new Singleton();
            }

            return INSTANCE; // others
        }

        public static void doSomething() {
            // other methods should use the keyword "static" as far as possible.
        }
    }
    ```
    注意：
    在高并发的情况下，懒汉式就会出现问题：可能会在内存中出现多个实例。例如，现有线程A、B，线程A执行到第11行，即`singleton = new Singleton();`，但还没有获取对象（对象初始化也是需要时间的）；与此同时，线程B执行到了第10行，即`if (singleton == null)`，那么线程B获得判断条件也为真，于是继续运行下去。结果，线程A获得了一个对象，线程B也获得了一个对象，在内存中就出现了两个对象。
    解决方法也很简单，不过这样会造成调用效率不高。可以在getInstance方法前加关键字`synchronized`，也可以在getInstance方法内增加`synchronized`。



  * 静态内部类式
    其实，只要实现了延迟加载对象，那么也就解决了线程安全的问题。而`静态内部类`就可以实现延迟加载。
    ``` Java
    public class Singleton {

        private static Singleton INSTANCE = null; // the instance is null.

        private Singleton() {
            // only allow one instance
        }

        private static class SingletonHolder {
            // init the instance, read only.
            private static final Singleton INSTANCE = new Singleton();
        }

        public static Singleton getInstance() {
            return SingletonHolder.INSTANCE;
        }

        public static void doSomething() {
            // other methods should use the keyword "static" as far as possible.
        }
    }
    ```
    由于静态内部类并没有在一开始就初始化对象实例，从这个角度讲，它是属于懒汉式的。

  * 双重检测锁式
    单例模式，其最主要的目的就是生成一个独一无二，不可修改（特殊情况除外）的实例。所以对于多线程安全的问题，只要将new关键字控制好即可。这让我联想到了数据库中的事务操作。双重检测锁，顾名思义就是进行两次判断，代码如下：
    ``` Java
    public class Singleton {

        private static Singleton INSTANCE = null; // the instance is null.

        private Singleton() {
            // only allow one instance
        }

        public static Singleton getInstance() {
            if (INSTANCE == null) { // the first check: before 'synchronized'
                synchronized (Singleton.class) {
                    if (INSTANCE == null) { // the second check: after 'synchronized'
                        INSTANCE = new Singleton();
                    }
                }
            }
        }

        public static void doSomething() {
            // other methods should use the keyword "static" as far as possible.
        }
    }
    ```
    对于双重检测锁式，网上有一些研究的比较深的文章，供大家参考。列举如下：
    1. [正确使用双重检查锁(DCL)](https://my.oschina.net/u/866190/blog/205454)
    2. [如何正确地写出单例模式](http://wuchong.me/blog/2014/08/28/how-to-correctly-write-singleton-pattern/#comments)
    3. [用happen-before规则重新审视DCL](http://www.iteye.com/topic/260515)
    4. [Java 理论与实践: 正确使用 Volatile 变量](https://www.ibm.com/developerworks/cn/java/j-jtp06197.html)

  * 枚举单例式
    有关枚举详细的介绍，看官可移步[Java中的枚举类型](https://valueyouth.github.io/2017/04/04/think-in-enum/)，这里我们只讨论单例模式中的枚举，毕竟这属于"单一职责"么。直接上代码：
    ``` Java
    /**
     * the define of the enum.
     */
    public enum Singleton {
        INSTANCE; // an element of enum

        public void doSomething() {
            // other methods should use the keyword "static" as far as possible.
        }
    }
    ```
    具体使用方法如下所示：
    ``` Java
	      Singleton singleton = Singleton.INSTANCE;
    ```
    关于使用枚举的诸多好处，《Effective Java》是这样说的：
    
        这种方法（枚举方法）在功能上与公有域方法相近，但它更加简洁，无偿地提供了序列化机制，绝对防止多次实例化，即使是在面对复杂地序列化或者反射攻击地时候。虽然这种方法还没有广泛采用，但是单元素的枚举类型已经成为实现Singleton的最佳方法。
    
    自己阅历还很浅，所以对于序列化机制、反射攻击等高级java机制并没有很深入的理解。所以我列出了一些网上的资料，方便各位看官查阅。
    1. [单例模式中为什么用枚举更好](http://www.kejilie.com/importnew/article/E1B1AE22FE9D297F7EB4C388CCD84E40.html)
    2. [Java中Enum类型的序列化](http://mysun.iteye.com/blog/1581119)


### 总结
   1. “枚举单例式”是实现单例的最佳方法，不过用的人不多；
   2. 推荐使用“饿汉式”及“静态内部类式”；
   3. 不推荐使用“双重检测锁式”；
   4. 极力不推荐使用“懒汉式”。
   对于单利模式的理解，我还是一个小小的小学生。包括双重锁检测及多线程等等，我都还只是处于一个比较基础的理解层面上。这些还都需要我去努力探索，寻求真相。以上就是我对于单例模式的一些简单的理解，希望能帮到各位。


### 参考文档

  1. [百度百科](http://baike.baidu.com/item/%E8%AE%BE%E8%AE%A1%E6%A8%A1%E5%BC%8F/1212549)
  2. [设计模式之禅  第二版 秦小波](https://book.douban.com/subject/25843319/)
  3. [Head First 设计模式（中文版） 弗里曼](https://book.douban.com/subject/2243615/)
  4. [Effective Java 中文版](https://book.douban.com/subject/1103015/)

