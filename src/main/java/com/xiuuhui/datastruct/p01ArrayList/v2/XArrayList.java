package com.xiuuhui.datastruct.p01ArrayList.v2;

import com.xiuuhui.datastruct.AList;
import com.xiuuhui.datastruct.IList;

import java.util.Arrays;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * 动态数组优化
 *
 * @author xim.hui
 * @date 2020/4/16 10:01
 */
public class XArrayList<E> extends AList<E> implements IList<E> {

    /**
     * 元素的数组
     */
    private Object[] elements;

    /**
     * 达到多少容量进行扩容
     */
    public static final double CAPACITY_CRITICAL = 0.75;

    /**
     * 缩容的临界值
     */
    public static final double CAPACITY_DECRITICAL = 0.6;

    /**
     * 默认构造器，初始化数据为 {@link AList#DEFAULT_SIZE}
     */
    public XArrayList() {
        this.elements = new Object[DEFAULT_SIZE];
    }

    /**
     * 指定的默认容量大小
     *
     * @param defaultCap 默认的容量大小
     */
    public XArrayList(int defaultCap) {
        if (defaultCap < ZERO) {
            this.elements = new Object[DEFAULT_SIZE];
        } else {
            this.elements = new Object[defaultCap];
        }
    }


    /**
     * 向指定的位置添加元素
     * <p>
     * 使用 <code>{@link XArrayList#capacity}</code> 方法确保容量合适
     * </p>
     *
     * @param index   索引
     * @param element 元素
     */
    @Override
    public void add(int index, E element) {

        capacity(size + 1);
        checkRangeForAdd(index);
        for (int i = size-1 ; i >= index ; i--) {
            elements[i+1] = elements[i];
        }
        elements[index] = element;
        size++;
    }

    /**
     * 确保容量
     *
     * @param size 长度
     */
    private void capacity(int size) {
        int oldLength = elements.length;
        if (size > oldLength * CAPACITY_CRITICAL ) {
            int newLength = oldLength + (oldLength >> 1);
            Object[] newElement = new Object[newLength];
            //为加深理解，不使用 System.arraycopy： if (size - 1 >= 0) System.arraycopy(elements, 0, newElement, 0, size - 1);
            for (int i = 0; i < size - 1; i++) {
                newElement[i] = elements[i];
            }
            elements = newElement;
            System.out.println("oldLength:"+oldLength+"  newLength:"+newLength);
        }
    }

    /**
     * 链表缩容
     *
     */
    private void deCapacity(){
        int oldLength =  elements.length;
        int newLength = oldLength >> 1;
        if (size < newLength && newLength > DEFAULT_SIZE ) {
            E[] newElement = (E[])new Object[newLength];
            for (int i = 0; i < size; i++) {
                newElement[i] = (E)elements[i];
            }
            elements = newElement;
            System.out.println("缩容： oldLength" +oldLength+" newLength："+newLength);
        }
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
        E e = (E) elements[index];
        //这里 使用 如下可以优化 if (size - index >= 0) System.arraycopy(elements, index + 1, elements, index, size - index);
        for (int i = index; i < size; i++) {
            elements[i] = elements[i+1];
        }
        //对于对象类型要去除引用帮助GC
        elements[--size] = null;
        deCapacity();
        return e;
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
        E e = (E)elements[index];
        elements[index] = element;
        return e;
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
            for (int i = 0; i < size ; i++) {
                if (elements[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size ; i++) {
                if (element.equals((E)elements[i])) {
                    return i;
                }
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
        checkRange(index);
        return (E)elements[index];
    }


    @Override
    public String toString() {
        return new StringJoiner(", ", XArrayList.class.getSimpleName() + "[", "]")
                .add("size=" + size)
                .add("elements=" + Arrays.toString(elements))
                .toString();
    }
}
