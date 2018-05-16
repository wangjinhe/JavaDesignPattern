package com.yixiu.designpattern.create._2factorymethod;

/**
 * 抽象工厂：列表
 */
public interface List<T> {
    boolean add(T data); // 添加一个元素
    Iterator<T> iterator(); // 返回一个遍历器
}
