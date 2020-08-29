package com.xiuuhui.datastruct.p01ArrayList.test;

import com.xiuuhui.datastruct.IList;
import com.xiuuhui.datastruct.p01ArrayList.v2.XArrayList;
import org.junit.jupiter.api.Test;

/**
 * @author xim.hui
 * @date 2020/4/16 13:52
 */
public class TestdyArray {

    @Test
    void test001() {
        IList<Integer> xArrayList = new XArrayList<>();
        xArrayList.add(12);
        xArrayList.add(13);
        xArrayList.add(14);
        xArrayList.add(15);
        xArrayList.add(2,16);
        for (int i = 0; i < 30; i++) {
            xArrayList.add(i);
        }
        System.out.println(xArrayList);
    }

    @Test
    void test002() {
        int oldLength = 10;
        System.out.println(10>>1);
        System.out.println(oldLength + (oldLength >> 1));
    }
}
