package com.xiuuhui.datastruct.p01ArrayList.v1;

import com.xiuuhui.datastruct.IList;

import java.util.Arrays;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * 动态数组
 *
 * @author xim.hui
 * @date 2020/4/16 10:01
 */
public class XArrayList<E> implements IList<E> {

    /**
     * 存储元素的个数
     */
    private int size;

    /**
     * 元素的数组
     */
    private Object[] elements;

    /**
     * 数值 0
     */
    public static final int ZERO = 0;

    /**
     * 找不到元素
     */
    public static final int ELEMENT_NOT_FOUND = -1;

    /**
     * 默认的大小
     */
    public static final int DEFAULT_SIZE = 10;

    /**
     * 达到多少容量进行扩容
     */
    public static final double CAPACITY_CRITICAL = 0.75;

    /**
     * 默认构造器，初始化数据为 {@link XArrayList#DEFAULT_SIZE}
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
     * 返回集合数据的个数
     *
     * @return 数据的个数
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * 返回集合是否是空的
     *
     * @return 集合是否是空的
     */
    @Override
    public boolean isEmpty() {
        return this.size == ZERO;
    }

    /**
     * 添加一个元素到最后
     *
     * @param element 新增的元素
     */
    @Override
    public void add(E element) {
        //这里调用指定位置的插入方法，进行数据插入
        add(size,element);
    }

    /**
     * 向指定的位置添加元素
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
        return e;
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
            for (int i = 0; i < size - 1; i++) {
                if (elements[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size - 1; i++) {
                if (element.equals(elements[i])) {
                    return i;
                }
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    /**
     * 是否包含此元素
     *
     * @param element   元素
     * @return          是否包含
     */
    @Override
    public boolean contains(E element) {
        return indexOf(element) > ELEMENT_NOT_FOUND;
    }

    /**
     * 检查传入的索引
     *
     * @param index 索引位置
     */
    private void checkRange(int index){
        if (index < 0 || index >= size) {
            outOfBound(index);
        }
    }

    /**
     * 检查插入时传入的索引
     *
     * @param index 索引位置
     */
    private void checkRangeForAdd(int index){
        if (index < 0 || index > size) {
            outOfBound(index);
        }
    }

    /**
     * 抛出异常
     *
     * @param index 错误的索引
     */
    private void outOfBound(int index){
        throw new IndexOutOfBoundsException("size :"+size +" index:"+index);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", XArrayList.class.getSimpleName() + "[", "]")
                .add("size=" + size)
                .add("elements=" + Arrays.toString(elements))
                .toString();
    }
}
