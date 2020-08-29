package com.xiuuhui.datastruct;

/**
 * @author xim.hui
 * @date 2020/4/17 17:03
 */
public abstract class AList<E> implements IList<E>{

    protected int size;

    /**
     * 默认的大小
     */
    public static final int DEFAULT_SIZE = 10;


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
     * 检查传入的索引
     *
     * @param index 索引位置
     */
    protected void checkRange(int index){
        if (index < 0 || index >= size) {
            outOfBound(index);
        }
    }

    /**
     * 检查插入时传入的索引
     *
     * @param index 索引位置
     */
    protected void checkRangeForAdd(int index){
        if (index < 0 || index > size) {
            outOfBound(index);
        }
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
     * 抛出异常
     *
     * @param index 错误的索引
     */
    protected void outOfBound(int index){
        throw new IndexOutOfBoundsException("size :"+size +" index:"+index);
    }
}
