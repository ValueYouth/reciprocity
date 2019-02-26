package base;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

@SuppressWarnings("unused")
public class FourReference {

    public static void main(String[] args) {
        WeakReference();
    }

    /**
     * 强引用
     *
     * 最常见的就是通过new关键字来创建。只要强引用在，垃圾搜集器永远不会搜集被引用的对象。
     * 也就是说，宁愿出现内存溢出，也不会回收这些对象
     * 如果想中断强引用和某个对象之间的关联，可以显示地将引用赋值为null，这样一来的话，
     * JVM在合适的时间就会回收该对象。Vector类的clear方法 "Let gc do its work"
     */
    private static void strongReference() {
        FourReference fourReference = new FourReference();
        System.out.println(fourReference);
    }

    /**
     * 软引用
     *
     * 软引用是用来描述一些有用但并不是必需的对象。如果一个对象只具有软引用，则内存空间足够，
     * 垃圾回收器就不会回收它；如果内存空间不足了，就会回收这些对象的内存
     *
     * 应用：实现缓存（网页缓存、图片缓存等）、浏览器的后退按钮
     */
    private static void SoftReference() {
        SoftReference<String> sr = new SoftReference<>("SoftReference...");
        System.gc();
        System.out.println(sr.get());
    }

    /**
     * 弱引用
     *
     * 弱引用也是用来描述非必需对象的，当JVM进行垃圾回收时，无论内存是否充足，都会回收被弱引用
     * 关联的对象。弱引用与软引用的区别在于：只具有弱引用的对象拥有更短暂的生命周期
     *
     * 应用：当你想引用一个对象，但是这个对象有自己的生命周期，你不想介入这个对象的生命周期，这
     * 时候你就是用弱引用
     */
    private static void WeakReference() {
        WeakReference<String> wr = new WeakReference<>("WeakReference...");
        System.out.println(wr.get());
        System.gc();
        System.out.println(wr.get());
    }

    /**
     * 虚引用
     *
     *  “虚引用”顾名思义，就是形同虚设，与其他几种引用都不同，虚引用并不会决定对象的生命周期。
     *  如果一个对象仅持有虚引用，在任何时候都可能被垃圾回收器回收
     *  虚引用与软引用和弱引用的一个区别在于：虚引用必须和引用队列 （ReferenceQueue）联合使用
     *
     *  应用：虚引用主要用来跟踪对象被垃圾回收器回收的活动。程序可以通过判断引用队列中是否已经加
     *  入了虚引用，来了解被引用的对象是否将要被垃圾回收。
     */
    @SuppressWarnings("unchecked")
    private static void PhantomReference() {
        ReferenceQueue<String> queue = new ReferenceQueue<>();
        String str = "PhantomReference";
        PhantomReference pr = new PhantomReference(str, queue);
        System.gc();
        System.out.println(pr.get());
    }
}
