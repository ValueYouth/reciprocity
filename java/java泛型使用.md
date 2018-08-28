### 泛型类

```java
public class Generic<T> {
    
    // key这个成员变量的类型为T,T的类型由外部指定
    prirate T key;
    
    // 泛型构造方法形参key的类型也为T，T的类型由外部指定
    public Generic(T key) { 
        this.key = key;
    }
    
    // 泛型方法getKey的返回值类型为T，T的类型由外部指定
    public T getKey(){ 
        return key;
    }
}
```

### 泛型接口

```java
public interface Generator<T> {
    public T next();
}
```

### 泛型方法
```java
/**
 * 泛型方法的基本介绍
 * 1. public与返回值中间<T>非常重要，可以理解为声明此方法为泛型方法。
 * 2. 只有声明了<T>的方法才是泛型方法，泛型类中的使用了泛型的成员方法并不是。
 * 3. <T>表明该方法将使用泛型类型T，此时才可以在方法中使用泛型类型T。
 * 4. 与泛型类的定义一样，此处T可以随便写为任意标识，常见的如T、E、K、V等形式的参数常用于表示泛型。
 *
 * @param tClass 传入的泛型实参
 * @return T 返回值为T类型
 */
public <T> T genericMethod(Class<T> tClass) {
    T instance = tClass.newInstance();
    return instance;
}
```