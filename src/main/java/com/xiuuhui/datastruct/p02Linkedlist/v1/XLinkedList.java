package com.xiuuhui.datastruct.p02Linkedlist.v1;

import com.xiuuhui.datastruct.AList;
import com.xiuuhui.datastruct.IList;

import java.util.Objects;

/**
 * 单链表
 *
 * @author xim.hui
 * @date 2020/4/17 14:07
 */
public class XLinkedList<E> extends AList<E> implements IList<E> {

    private Node<E> first;

    /**
     * 向指定的位置添加元素
     *
     * @param index   索引
     * @param element 元素
     */
    @Override
    public void add(int index, E element) {
        checkRangeForAdd(index);
        if (index == ZERO) {
            first = new Node<>(first,element);
        } else {
            Node<E> prev = node(index - 1);
            prev.next = new Node<>(prev.next,element);
        }
        size++;
    }

    /**
     * 移除对应索引处的元素
     *
     * @param index 移除元素的位置
     * @return 移除的元素
     */
    @Override
    public E remove(int index) {
        checkRange(index);
        Node<E> node = first;
        if (index == ZERO) {
            first = first.next;
        } else {
            Node<E> prev = node(index - 1);
            node = prev.next;
            prev.next = node.next;
        }
        size--;
        return node.element;
    }

    /**
     * 更新指定位置的元素，返回旧的数据
     *
     * @param index   更新元素的索引
     * @param element 新元素
     * @return 旧元素
     */
    @Override
    public E update(int index, E element) {
        checkRange(index);
        Node<E> node = node(index);
        E old = node.element;
        node.element = element;
        return old;
    }

    /**
     * 查询元素所在的第一个索引
     *
     * @param element 元素
     * @return 索引，找不到返回 -1
     */
    @Override
    public int indexOf(E element) {
        if (Objects.isNull(element)) {
            Node<E> temp = first;
            for (int i = 0; i < size; i++) {
                if (Objects.isNull(temp.element)) return i;
                temp = temp.next;
            }
        } else {
            Node<E> temp = first;
            for (int i = 0; i < size; i++) {
                if ( element.equals(temp.element) ) return i;
                temp = temp.next;
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    /**
     * 获取指定索引处的元素
     *
     * @param  index 索引
     * @return 返回指定位置的值
     */
    @Override
    public E get(int index) {
        return node(index).element;
    }

    /**
     * 获取指定位置的节点
     * @param index 节点的索引
     * @return      节点元素
     */
    private Node<E> node(int index) {
        checkRange(index);
        Node<E> temp = first;
        for (int i = 0; i < index; i++) {
            temp = first.next;
        }
        return temp;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("size = ").append(size).append("\t【");

        Node<E> temp = first;
        while (!Objects.isNull(temp)) {
            stringBuilder.append(temp.element).append(",");
            temp = temp.next;
        }
        stringBuilder.append("】");
        return stringBuilder.toString();
    }

    /**
     * 链表节点
     * @param <E>
     */
    private static class Node<E> {

        public Node<E> next;
        public E element;

        public Node<E> getNext() {
            return next;
        }

        public Node() {
        }

        public Node(Node<E> next, E e) {
            this.next = next;
            this.element = e;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }

        public void setE(E e) {
            this.element = e;
        }
    }
}
