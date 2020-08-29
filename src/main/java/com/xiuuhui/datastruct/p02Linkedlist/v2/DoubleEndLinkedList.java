package com.xiuuhui.datastruct.p02Linkedlist.v2;

import com.xiuuhui.datastruct.AList;
import com.xiuuhui.datastruct.IList;

import java.util.Objects;

/**
 * 双向链表
 *
 * @author xim.hui
 * @param <E>
 */
public class DoubleEndLinkedList<E> extends AList<E> implements IList<E> {

    private Node<E> first;
    private Node<E> last;

    /**
     * 向指定的位置添加元素
     *
     * @param index   索引
     * @param element 元素
     */
    @Override
    public void add(int index, E element) {
        checkRangeForAdd(index);
        if (index == size) {
            Node<E> oldLast = last;
            last = new Node<>(oldLast, element, null);
            if ( Objects.isNull(oldLast) ) {
                first = last;
            } else {
                oldLast.next = last;
            }
        } else {
            Node<E> next = node(index);
            Node<E> prev = next.prev;
            Node<E> node = new Node<>(prev, element, next);
            next.prev = node;
            if (Objects.isNull(prev)) {
                first = node;
            } else {
                prev.next = node;
            }
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
        Node<E> removeNode = node(index);
        Node<E> next = removeNode.next;
        Node<E> prev = removeNode.prev;

        if (Objects.isNull(prev)) {
            first = next;
            first.prev = null;
        } else {
            prev.next = next;
        }
        if (Objects.isNull(next)) {
            last = prev;
            last.next = null;
        } else {
            next.prev = prev;
        }
        size--;
        return removeNode.element;
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
     * @return
     */
    @Override
    public E get(int index) {
        return node(index).element;
    }

    /**
     * 清空链表
     */
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    /**
     * 获取指定位置的节点
     * @param index 节点的索引
     * @return      节点元素
     */
    private Node<E> node(int index) {
        checkRange(index);
        if (index < (size >> 1)) {
            Node<E> temp = first;
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
            return temp;
        } else {
            Node<E> temp = last;
            int tempI = size - index;
            for (int i = 1; i < tempI ; i ++ ) {
                temp = temp.prev;
            }
            return temp;
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("size = "+size).append("\t【");

        Node<E> temp = first;
        while (!Objects.isNull(temp)) {
            stringBuilder.append(temp).append(",");
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
        public Node<E> prev;
        public E element;

        public Node<E> getNext() {
            return next;
        }

        public Node() {
        }

        public Node(Node<E> prev, E element,Node<E> next) {
            this.next = next;
            this.prev = prev;
            this.element = element;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }

        public void setE(E e) {
            this.element = e;
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Node{");
            sb.append("prev=").append(Objects.isNull(prev)?"空":prev.element);
            sb.append(", element=").append(element);
            sb.append(", next=").append(Objects.isNull(next)?"空":next.element);
            sb.append('}');
            return sb.toString();
        }
    }
}
