package base;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.lang.reflect.Constructor;

/**
 * java中创建对象的几种方式
 *
 * 方法一、二、三都调用了构造方法
 *
 * @author wangchi
 * @since 2019年2月21日
 */
public class ClassCreate {

    public ClassCreate() {
        System.out.println("调用方法 ThreadJoin()...");
    }

    public ClassCreate(String s) {
        System.out.println("调用方法 ClassCreate(String s)... 参数内容为：" + s);
    }

    public static void main(String[] args) throws Exception {
        // 1.new关键字
        System.out.println("1.通过new关键字创建对象");
        ClassCreate c1 = new ClassCreate();
        System.out.println("----------------------------------");

        // 2.Class类的newInstance方法
        System.out.println("2.通过Class类的newInstance方法创建对象");
        ClassCreate c2 = (ClassCreate) Class.forName("base.ClassCreate").newInstance();
        System.out.println("----------------------------------");

        // 3.Constructor类的newInstance方法
        System.out.println("3.通过Constructor类的newInstance方法创建对象");
        Constructor<ClassCreate> constructor1 = ClassCreate.class.getConstructor();
        ClassCreate c3 = constructor1.newInstance(); // 不带参数
        Constructor<ClassCreate> constructor2 = ClassCreate.class.getConstructor(String.class);
        ClassCreate c4 = constructor2.newInstance("HelloWorld..."); // 带参数
        System.out.println("----------------------------------");

        // 4.clone方法
        ClassCreate c5 = (ClassCreate) c3.clone();
        System.out.println("----------------------------------");

        // 5.使用反序列化
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("xxx.obj"));
        ClassCreate c6 = (ClassCreate) in.readObject();
    }



}
