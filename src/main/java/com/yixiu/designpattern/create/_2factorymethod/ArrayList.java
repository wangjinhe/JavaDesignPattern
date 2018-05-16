package com.yixiu.designpattern.create._2factorymethod;

/**
 * 具体工厂类：数组列表
 * @param <T>
 */
public class ArrayList <T> implements List<T> {

    private int size; // 数组元素个数，初始化为0
    private  Object [] defaultArray ; // 使用对象数组，存放元素
    private static final   int defaultLength  = 100; // 默认数组大小
    // 默认构造函数，初始化对象数组
    public  ArrayList()
    {
        defaultArray = new  Object[defaultLength];
    }

    // 实现add方法
    public boolean add(T data) {
        if(size < defaultLength) {
            defaultArray[size++] = data;
            return  true;
        }
        return false;
    }

    // 实现iterator方法
    public Iterator<T> iterator() {
        return new ArrayListIterator();
    }


    /**
     * 具体产品：数组列表遍历器
     * @param <T>
     */
    private  class ArrayListIterator<T> implements Iterator<T> {
        private int next ; // 保存下一个元素的位置，初始化为0
        public boolean hasNext() {
            if(next < size) {
                return  true;
            }
            return false;
        }

        public T next() {
            if(next < size) {
                return  (T)defaultArray[next++];
            }
            return null;
        }
    }


}
