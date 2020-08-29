package com.xiuuhui.datastruct.p03Stack.v1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleStackTest {

    @Test
    void test01() {
        SimpleStack<Integer> stack = new SimpleStack<>();
        for (int i = 0; i < 15; i++) {
            stack.push(i);
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(stack.pop());
        }
    }
}