package com.xiuuhui.datastruct.p02Linkedlist.v1;

import com.xiuuhui.datastruct.IList;
import com.xiuuhui.datastruct.p01ArrayList.v2.XArrayList;
import com.xiuuhui.datastruct.p02Linkedlist.v4.DoubleEndCircleList;
import org.junit.jupiter.api.Test;


class XLinkedListTest {

    @Test
    void test01() {
        IList<Integer> list = new XLinkedList<>();
        list.add(10);
        list.add(20);
        list.remove(0);
        list.remove(0);
        list.add(0,30);
        list.add(list.size(),40);
        list.add(list.size()-1,50);
        System.out.println(list);
    }

    @Test
    void test02() {
        testList(new DoubleEndCircleList<>());
    }

    @Test
    void test03() {
        IList<Integer> list = new XArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(i);
        }
        for (int i = 0; i < 15; i++) {
            list.remove(0);
        }
        System.out.println(list);
    }

    void testList(IList<Integer> list) {
        list.add(11);
        System.out.println(list);
        list.add(22);
        System.out.println(list);
        list.add(33);
        System.out.println(list);
        list.add(44);
        System.out.println(list);
        list.add(0, 55); // [55, 11, 22, 33, 44]
        System.out.println(list);
        list.add(2, 66); // [55, 11, 66, 22, 33, 44]
        System.out.println(list);
        list.add(list.size(), 77); // [55, 11, 66, 22, 33, 44, 77]
        System.out.println(list);
        list.remove(0); // [11, 66, 22, 33, 44, 77]
        list.remove(2); // [11, 66, 33, 44, 77]
        list.remove(list.size() - 1); // [11, 66, 33, 44]
        assert list.indexOf(44) == 3;
        assert list.indexOf(22) == IList.ELEMENT_NOT_FOUND;
        assert list.contains(33);
        assert list.get(0) == 11;
        assert list.get(1) == 66;
        assert list.get(list.size() - 1) == 44;
        System.out.println(list);
    }
}