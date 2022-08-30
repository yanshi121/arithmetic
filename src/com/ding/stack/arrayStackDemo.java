package com.ding.stack;

import java.util.Scanner;

public class arrayStackDemo {
    public static void main(String[] args) {
        //测试`
        ArrayStack Stack = new ArrayStack(4);
        String key = "";
        boolean loop = true;//控制菜单
        Scanner scanner = new Scanner(System.in);
        while (loop){
            System.out.println("show:显示栈");
            System.out.println("exit:退出");
            System.out.println("push:添加");
            System.out.println("pop:取出");
            System.out.println("请输入选择");
            key = scanner.next();
            switch (key){
                case "show":
                    Stack.list();
                    break;
                case "push":
                    System.out.println("输入一个数");
                    int value = scanner.nextInt();
                    Stack.push(value);
                    break;
                case "pop":
                    try {
                        int res = Stack.pop();
                        System.out.printf("取出数据:%d", res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出");
    }
}
//定义一个arrayStack表示栈
class ArrayStack{
    private int maxSize;//栈大小
    private int[] stack;//数组。数组模拟栈
    private int top = -1;//top表示栈顶，初始化为-1
    //构造器
    public ArrayStack(int maxSize){
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }
    //栈满
    public boolean isFull(){
        return top == maxSize - 1;
    }
    //栈空
    public boolean isEmpty(){
        return top == -1;
    }
    //入栈
    public void push(int value){
        //判断栈满
        if (isFull()){
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }
    //出栈
    public int pop(){
        //判空
        if (isEmpty()){
            //抛出异常
            throw new RuntimeException("栈空");
        }
        int value = stack[top];
        top--;
        return value;
    }
    //遍历栈
    public void list(){
        if (isEmpty()){
            System.out.println("栈空");
            return;
        }
        for (int i = top; i >= 0; i--){
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }
}