package com.yixiu.designpattern.create._2factorymethod;

/**
 * 具体工厂类：单链表列表
 * @param <T>
 */
public class LinkList<T> implements List<T> {
    private int size; // 存放元素的个数，默认为0
    private Node<T> first; // 第一个节点，默认为null

    public boolean add(T data) {
        if (size == 0) {
            first = new Node<T>(data, null);
            size++;
            return true;
        }
        Node<T> node = first; // 从第1个节点开始找最后一个节点
        while (node.next != null) {
            node = node.next;
        }
        node.next = new Node<T>(data, null);
        size++;
        return true;
    }

    public Iterator<T> iterator() {
        return new LinkListIterator();
    }


    // 节点类
    private static class Node<T> {
        private T data; // 当前节点数据
        private Node<T> next; // 下一个节点

        public Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }
    }


    /**
     * 具体产品：单链表遍历器
     *注意：LinkListIterator实现泛型接口Iterator，只需要在后面加一个T就行了，下面的写法是错误的。
     * private class LinkListIterator<T> implements Iterator<T>
     *
     */
    private class LinkListIterator implements Iterator<T> {
        private Node<T> next; // 保存下一个节点，初始化为first

        LinkListIterator() {
            next = first;
        }

        // 是否有下一个节点，next本身就指向下一个节点
        public boolean hasNext() {
            return next != null;
        }

        // 返回下一个节点的值
        public T next() {
            if (next == null) {
                return null;
            }
            T data = next.data; // 先保存下一个节点的值
            next = next.next; // 移动到下一个节点
            return data;
        }

    }
}
