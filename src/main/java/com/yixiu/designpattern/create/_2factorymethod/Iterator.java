package com.yixiu.designpattern.create._2factorymethod;

/**
 * 抽象产品：遍历器
 * 具体产品的实现在具体工厂中。
 * @param <T>
 */
public interface Iterator<T> {
    boolean hasNext(); // 是否有下一个元素
    T next(); // 返回下一个元素
}
