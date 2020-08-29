package com.xiuuhui.datastruct;

/**
 * @author xim.hui
 * @date 2020/4/16 10:09
 */
public interface IList<E> {


    /**
     * 数值 0
     */
    int ZERO = 0;

    /**
     * 找不到元素
     */
    int ELEMENT_NOT_FOUND = -1;

    /**
     * 返回集合数据的个数
     *
     * @return 数据的个数
     */
    int size();

    /**
     * 返回集合是否是空的
     *
     * @return 集合是否是空的
     */
    boolean isEmpty();

    /**
     * 添加一个元素到最后
     *
     * @param element 新增的元素
     */
    void add(E element);

    /**
     * 向指定的位置添加元素
     *
     * @param index     索引
     * @param element   元素
     */
    void add(int index ,E element);

    /**
     * 移除对应索引处的元素
     *
     * @param index 移除元素的位置
     * @return      移除的元素
     */
    E remove(int index);

    /**
     * 更新指定位置的元素，返回旧的数据
     *
     * @param index     更新元素的索引
     * @param element   新元素
     * @return          旧元素
     */
    E update(int index,E element);

    /**
     * 查询元素所在的第一个索引
     *
     * @param element   元素
     * @return          索引，找不到返回 -1
     */
    int indexOf(E element);

    /**
     * 获取index位置的元素
     *
     * @param index 索引
     * @return      元素
     */
    E get(int index);

    /**
     * 是否包含此元素
     *
     * @param   element 查找的元素
     * @return  结果，是否包含
     */
    boolean contains(E element);


}
