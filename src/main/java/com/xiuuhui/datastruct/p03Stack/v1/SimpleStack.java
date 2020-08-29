package com.xiuuhui.datastruct.p03Stack.v1;

import com.xiuuhui.datastruct.p02Linkedlist.v1.XLinkedList;

/**
 * 栈实现（使用单链表）
 *
 * @author xim.hui
 * @version 1.0
 * @date 2020/4/20 16:44
 */
public class SimpleStack<E> {

    private final XLinkedList<E> list = new XLinkedList<>();

    /**
     * 返回栈的大小
     *
     * @return 栈内元素的大小
     */
    public int size(){
        return list.size();
    }

    /**
     * 栈内是否是空的
     *
     * @return 栈是否为空
     */
    public boolean isEmpty(){
        return list.isEmpty();
    }

    /**
     * 入栈
     *
     * @param element 需要入栈的元素
     */
    public void push(E element){
        list.add(element);
    }

    /**
     * 出栈
     *
     * @return 弹出的元素
     */
    public E pop(){
        return list.remove(list.size()-1);
    }

    /**
     * 查看栈顶的元素
     *
     * @return 栈顶的元素
     */
    public E peek(){
        return list.get(list.size()-1);
    }
}
