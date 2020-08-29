package com.xiuuhui.datastruct;

public interface IQueue<E> {

    /**
     * 返回队列中元素的数量
     *
     * @return 队列的大小
     */
    int size();

    /**
     * 队列是否为空
     *
     * @return 是否为空
     */
    boolean isEmpty();

    /**
     * 清空队列
     */
    void clear();

    /**
     * 入队
     *
     * @param e 元素
     */
    void enQueue(E e);

    /**
     * 出队
     *
     * @return 出队的元素
     */
    E deQueue();

    /**
     * 队头的元素
     *
     * @return 对头的元素
     */
    E front();
}
