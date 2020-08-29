package com.xiuuhui.datastruct.p04Queue.v1;

import com.xiuuhui.datastruct.IList;
import com.xiuuhui.datastruct.IQueue;
import com.xiuuhui.datastruct.p02Linkedlist.v1.XLinkedList;
import com.xiuuhui.datastruct.p02Linkedlist.v2.DoubleEndLinkedList;

/**
 * 简单队列实现
 * <p> 使用双向链表 </p>
 *
 * @author xim.hui
 * @version 1.0
 * @date 2020/4/20 18:07
 */
public class SimpleQueue<E> implements IQueue<E> {

    private DoubleEndLinkedList<E> list = new DoubleEndLinkedList<>();

    /**
     * 返回队列中元素的数量
     *
     * @return 队列的大小
     */
    @Override
    public int size() {
        return list.size();
    }

    /**
     * 队列是否为空
     *
     * @return 是否为空
     */
    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * 清空队列
     */
    @Override
    public void clear() {
        list.clear();
    }

    /**
     * 入队
     *
     * @param e 元素
     */
    @Override
    public void enQueue(E e) {
        list.add(e);
    }

    /**
     * 出队
     *
     * @return 出队的元素
     */
    @Override
    public E deQueue() {
        return list.remove(0);
    }

    /**
     * 队头的元素
     *
     * @return 对头的元素
     */
    @Override
    public E front() {
        return list.get(0);
    }
}
