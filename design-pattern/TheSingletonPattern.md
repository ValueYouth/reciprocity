### ����
  ����ģʽ��Singleton Pattern����һ���Ƚϼ򵥵�ģʽ���䶨�����£�Ensure a class has only one instance, and provide a global point of access to it.��ȷ��ĳһ����ֻ��һ��ʵ������������ʵ������������ϵͳ�ṩ���ʵ������
  ����ģʽ��ȷ��һ����ֻ��һ��ʵ������������ʵ������ͬʱ��������ϵͳ�ṩ���ʵ��������ģʽ��ͨ�ô���������ʾ��
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
  ���������У���5�е�˽�й��췽������֤�˸���ֻ�����һ��ʵ�����ڵ���ģʽ���У��������Ҫ���������������ʹ��`static`�ؼ��֡�

### ��ȱ��
  * �ŵ�
  1. ����ϵͳ���������ڵ���ģʽ���ڴ���ֻ��һ��ʵ�����������ڴ濪֧���ر���һ��������ҪƵ���ش���������ʱ�����Ҵ���������ʱ�������޷��Ż�������ģʽ�����ƾͷǳ����ԡ�
  2. �������Դ�Ķ���ռ�á�����һ��д�ļ�����������ֻ��һ��ʵ�������ڴ��У������ͬһ����Դ�ļ���ͬʱд������
  3. ������ϵͳ����ȫ�ֵķ��ʵ㣬�Ż��͹�����Դ���ʡ�����������һ�������࣬�����������ݱ��ӳ�䴦��
  * ȱ��
  1. ����ģʽһ��û�нӿڣ���չ�����ѡ�
  2. �뵥һְ��ԭ���г�ͻ��

### ����
  ��Ҫ������ʽ������ʽ
  ��������̬�ڲ���ʽ��˫�ؼ����ʽ��ö�ٵ���ʽ
  <!-- * <font color=#0066CC>����ʽ</font>   -->
  * ����ʽ
    ����ʽ������˼�壬��������ӡ��ܶ�����Ҫ�����ԡ�ʳ�������ġ����ӡ�ָ�ľ����࣬��ʳ�����ʵ�����Ķ�����ˣ�����ʽҪ������˼���£�����һ���ص�ʱ�򣬾�ͨ��new�ؼ���ֱ�ӳ�ʼ��һ��ʵ��������Ȼ�������еĴ����ʹ�õľ��Ƕ���ʽ��

  * ����ʽ  
    ��Ϊ�Ƚϡ���������������һ��ʼ��ʱ�򲢲���ʼ��ʵ����ʲôʱ���ã�ʲôʱ���ٽ��г�ʼ����ͨ�ô���������ʾ��
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
    ע�⣺
    �ڸ߲���������£�����ʽ�ͻ�������⣺���ܻ����ڴ��г��ֶ��ʵ�������磬�����߳�A��B���߳�Aִ�е���11�У���`singleton = new Singleton();`������û�л�ȡ���󣨶����ʼ��Ҳ����Ҫʱ��ģ������ͬʱ���߳�Bִ�е��˵�10�У���`if (singleton == null)`����ô�߳�B����ж�����ҲΪ�棬���Ǽ���������ȥ��������߳�A�����һ�������߳�BҲ�����һ���������ڴ��оͳ�������������
    �������Ҳ�ܼ򵥣�������������ɵ���Ч�ʲ��ߡ�������getInstance����ǰ�ӹؼ���`synchronized`��Ҳ������getInstance����������`synchronized`��



  * ��̬�ڲ���ʽ
    ��ʵ��ֻҪʵ�����ӳټ��ض�����ôҲ�ͽ�����̰߳�ȫ�����⡣��`��̬�ڲ���`�Ϳ���ʵ���ӳټ��ء�
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
    ���ھ�̬�ڲ��ಢû����һ��ʼ�ͳ�ʼ������ʵ����������ǶȽ���������������ʽ�ġ�

  * ˫�ؼ����ʽ
    ����ģʽ��������Ҫ��Ŀ�ľ�������һ����һ�޶��������޸ģ�����������⣩��ʵ�������Զ��ڶ��̰߳�ȫ�����⣬ֻҪ��new�ؼ��ֿ��ƺü��ɡ����������뵽�����ݿ��е����������˫�ؼ����������˼����ǽ��������жϣ��������£�
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
    ����˫�ؼ����ʽ��������һЩ�о��ıȽ�������£�����Ҳο����о����£�
    1. [��ȷʹ��˫�ؼ����(DCL)](https://my.oschina.net/u/866190/blog/205454)
    2. [�����ȷ��д������ģʽ](http://wuchong.me/blog/2014/08/28/how-to-correctly-write-singleton-pattern/#comments)
    3. [��happen-before������������DCL](http://www.iteye.com/topic/260515)
    4. [Java ������ʵ��: ��ȷʹ�� Volatile ����](https://www.ibm.com/developerworks/cn/java/j-jtp06197.html)

  * ö�ٵ���ʽ
    �й�ö����ϸ�Ľ��ܣ����ٿ��Ʋ�[Java�е�ö������](https://valueyouth.github.io/2017/04/04/think-in-enum/)����������ֻ���۵���ģʽ�е�ö�٣��Ͼ�������"��һְ��"ô��ֱ���ϴ��룺
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
    ����ʹ�÷���������ʾ��
    ``` Java
	      Singleton singleton = Singleton.INSTANCE;
    ```
    ����ʹ��ö�ٵ����ô�����Effective Java��������˵�ģ�
    
        ���ַ�����ö�ٷ������ڹ������빫���򷽷�������������Ӽ�࣬�޳����ṩ�����л����ƣ����Է�ֹ���ʵ��������ʹ������Ը��ӵ����л����߷��乥����ʱ����Ȼ���ַ�����û�й㷺���ã����ǵ�Ԫ�ص�ö�������Ѿ���Ϊʵ��Singleton����ѷ�����
    
    �Լ���������ǳ�����Զ������л����ơ����乥���ȸ߼�java���Ʋ�û�к��������⡣�������г���һЩ���ϵ����ϣ������λ���ٲ��ġ�
    1. [����ģʽ��Ϊʲô��ö�ٸ���](http://www.kejilie.com/importnew/article/E1B1AE22FE9D297F7EB4C388CCD84E40.html)
    2. [Java��Enum���͵����л�](http://mysun.iteye.com/blog/1581119)


### �ܽ�
   1. ��ö�ٵ���ʽ����ʵ�ֵ�������ѷ����������õ��˲��ࣻ
   2. �Ƽ�ʹ�á�����ʽ��������̬�ڲ���ʽ����
   3. ���Ƽ�ʹ�á�˫�ؼ����ʽ����
   4. �������Ƽ�ʹ�á�����ʽ����
   ���ڵ���ģʽ����⣬�һ���һ��СС��Сѧ��������˫������⼰���̵߳ȵȣ��Ҷ���ֻ�Ǵ���һ���Ƚϻ������������ϡ���Щ������Ҫ��ȥŬ��̽����Ѱ�����ࡣ���Ͼ����Ҷ��ڵ���ģʽ��һЩ�򵥵���⣬ϣ���ܰﵽ��λ��


### �ο��ĵ�

  1. [�ٶȰٿ�](http://baike.baidu.com/item/%E8%AE%BE%E8%AE%A1%E6%A8%A1%E5%BC%8F/1212549)
  2. [���ģʽ֮��  �ڶ��� ��С��](https://book.douban.com/subject/25843319/)
  3. [Head First ���ģʽ�����İ棩 ������](https://book.douban.com/subject/2243615/)
  4. [Effective Java ���İ�](https://book.douban.com/subject/1103015/)

