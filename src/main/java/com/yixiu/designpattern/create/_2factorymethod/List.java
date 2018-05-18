package com.yixiu.designpattern.create._2factorymethod;

/**
 * 抽象工厂：列表
 * 一个列表需要的功能主要是增删改查和获取遍历器，遍历器实际上就是一个个具体的产品，不同的列表需要的遍历器不同，
 * 通过模板方法让列表来控制生产遍历器，因此列表是工厂，遍历器是产品，不同的列表(具体工厂)可以生产不同的遍历器(具体产品)。
 */
public interface List<T> {
    boolean add(T data); // 添加一个元素
    Iterator<T> iterator(); // 返回一个遍历器
}
