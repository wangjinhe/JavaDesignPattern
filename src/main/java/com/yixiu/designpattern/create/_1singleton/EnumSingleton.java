package com.yixiu.designpattern.create._1singleton;

/**
 * 优点:超集简单，线程安全
 * 创建枚举默认就是线程安全的，所以不需要担心double checked locking，而且还能防止反序列化导致重新创建新的对象。
 *
 * 为什么枚举类可以用来实现单例呢？
 * 一个类的对象时有限且固定的，如季节类，它只有春夏秋冬4个对象这种实例有限且固定的类，在 Java 中被称为枚举类；
 * 从枚举类的定义可以看出：单例只有一个实例，因此可以用枚举来实现。
 *
 * 枚举类是一种特殊的类，可以定义自己的变量，函数。
 * 参考：https://www.jianshu.com/p/46dbd930f6a2
 */
public enum EnumSingleton {
    Instance;

    public  void sayHello() {
        System.out.println("hello,I am EnumSingleton");
    }

}
