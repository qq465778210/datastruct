package com.xiuuhui.datastruct.p04Queue.v3;

import com.xiuuhui.datastruct.IQueue;

import java.util.Arrays;
import java.util.StringJoiner;

/**
 * 双端循环队列
 *
 * @author xim.hui
 * @date 2020/4/21 13:04
 */
public class DeCircleQueue<E> implements IQueue<E> {

    private int front;

    private int size;

    private E[] elements;

    public static final double CAPACITY_CRITICAL = 0.75;

    public static final int DEFAULT_SIZE = 10;

    public DeCircleQueue() {
        elements = (E[]) new Object[DEFAULT_SIZE];
    }

    /**
     * 返回队列中元素的数量
     *
     * @return 队列的大小
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * 队列是否为空
     *
     * @return 是否为空
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 清空队列
     */
    @Override
    public void clear() {
        this.front = 0;
        this.size = 0;
    }

    /**
     * 入队
     *
     * @param e 元素
     */
    @Override
    public void enQueue(E e) {
        capacity(size+1);
        this.elements[index(front)] = e;
        this.size ++;
    }

    /**
     * 头部入队
     *
     * @param e 元素
     */
    public void enQueueFront(E e) {
        capacity(size+1);
        this.elements[index(-1)] = e;
        this.size ++;
    }

    /**
     * 确保容量
     *
     * @param size 长度
     */
    private void capacity(int size) {
        int oldLength = this.elements.length;
        if (size > oldLength * CAPACITY_CRITICAL ) {
            int newLength = oldLength + (oldLength >> 1);
            E[] newElement = (E[]) new Object[newLength];
            //为加深理解，不使用 System.arraycopy
            for (int i = 0; i < size - 1; i++) {
                newElement[i] = this.elements[index(i)];
            }
            this.elements = newElement;
            this.front = 0;
            System.out.println("oldLength:"+oldLength+"  newLength:"+newLength);
        }
    }

    /**
     * 返回元素在数组的索引
     *
     * @param index 第几个元素
     * @return      数组的索引
     */
    private int index(int index) {
        index += front;
        if ( index<0 ) {
            return index + elements.length;
        }
        return index % this.elements.length;
    }

    /**
     * 出队
     *
     * @return 出队的元素
     */
    @Override
    public E deQueue() {
        E frontElement = this.elements[this.front];
        this.front = index(1);
        this.size --;
        return frontElement;
    }

    /**
     * 尾部出队
     *
     * @return 出队的元素
     */
    public E deQueueRear() {
        int rear = index(size - 1);
        E rearElement = this.elements[rear];
        this.elements[rear] = null;
        this.size --;
        return rearElement;
    }

    /**
     * 队头的元素
     *
     * @return 对头的元素
     */
    @Override
    public E front() {
        return this.elements[this.front];
    }

    /**
     * 队尾的元素
     *
     * @return 队尾的元素
     */
    public E rear() {
        return this.elements[index(size-1)];
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DeCircleQueue.class.getSimpleName() + "[", "]")
                .add("front=" + front)
                .add("size=" + size)
                .add("elements=" + Arrays.toString(elements))
                .toString();
    }
}
