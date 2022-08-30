package com.ding.linkedlist;

import java.util.Stack;

public class testStack {
    public static void main(String[] args) {
        Stack<String> stack = new Stack();
        //入栈
        stack.add("jack");
        stack.add("tom");
        stack.add("smith");
        //出栈
        while (stack.size() > 0){
            System.out.println(stack.pop());//取出栈顶元素
        }
    }
}
