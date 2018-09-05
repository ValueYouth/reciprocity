# 1. 设计模式是什么？

  设计模式，英文全称：Design Pattern。它是一套被反复使用、多数人知晓的、经过分类编目的、代码设计经验的总结。使用设计模式是为了可重用代码、让代码更容易被他人理解、保证代码可靠性。设计模式是代码设计经验，它可以被反复使用，并且众所周知。如果把软件工程比作一座高楼厦，那么设计模式就是这座大厦的基石。

# 2. 六大原则

* **单一职责原则**(Single Responsibility Principle, SRP)
  一个类或者一个借口，最好只做一件事情。这样当发生变化时，它只会受到单一的影响。假设职责太多，那么引起变化的原因将会很多，这样导致职责和功能上的依赖，将严重影响其`内聚性`和`耦合度`。
* **里氏代换原则**(Liskov Substitution Principle)  
  `任何基类可以出现的地方，子类一定可以出现`。该原则是继承复用的基石，主要针对的时集成基础上的抽象与多态。具体就是子类必须实现父类的方法，即重写(Override)。
* **依赖倒置原则**(Dependence Inversion Principle, DIP)
  原始定义如下：High level modules should not depend upon low level modules. Both should depend upon abstractions. Abstractions should not depend upon details. Details should depend upon abstractions.<br>
  翻译过来有三层含义：高层模块不应该依赖低层模块，二者都应该依赖其抽象；抽象不应该依赖细节；细节应该依赖抽象。
  在java语言中的表现：模块间的依赖通过抽象发生，实现类之间不发生直接的依赖关系，其依赖关系是通过借口或者抽象类产生的；接口或抽象类不依赖于实现类；实现类依赖接口或抽象类。
  更加精简的定义：`面向接口编程`
* **接口隔离原则**(Interface Segregation Principle, ISP)
  接口有两种：类接口(Class Interface)和实例接口(Object Interface)。前者无需解释，而实例接口其实就是在Java中声明一个类，然后用new关键字产生一个实例，它是对一个类型的事物的描述，这是一种接口。
  基于两种接口，分别有不同的隔离原则与之对应：<br>
  Client should not be forced to depend upon interfaces that they don't use. 客户端不应该依赖它不需要的接口。<br>
  The dependency of one class to another one should depend on the smallest possible interface. 类间的依赖关系应该建立在最小的接口上。<br>
* **迪米特法则**(Law of Demeter, LoD) / 最少知道原则(Least Knowledge Principle, LKP)
  英文解释：Only talk to your immediate friends，只和直接的朋友通信。每个对象都必然会与其它对象有耦合关系，两个对象之间的耦合就称之为朋友关系。例如：组合、聚合和依赖。通俗的讲，一个类应该对自己需要耦合或者调用的类知道的最少，你(被耦合或调用的类)的内部是如何复杂都和我没有关系，那是你的事情，我就知道你提供的这么多的public方法，我就调用这么多，其它的我一概不关心。
* **开闭原则**(Open Close Principle)  
  `对扩展开放，对修改关闭`。即代码模块应当尽量不修改原来代码的情况下进行扩展，而为了增强代码的可扩展性，我们就需要接口和抽象类。

# 3. 二十三种设计模式

总的来说，设计模式分为以下三大类：

* 创建型模式(共5种)：[单例模式](https://valueyouth.github.io/2017/03/11/The-singleton-pattern/)、工厂方法模式、抽象工厂模式、建造者模式、原型模式。
* 结构型模式(共7种)：代理模式、适配器模式、装饰器模式、外观模式、桥接模式、组合模式、享元模式。
* 行为型模式(共11种)：策略模式、模板方法模式、观察者模式、迭代器模式、责任链模式、命令模式、备忘录模式、状态模式、访问者模式、中介者模式、解释器模式
