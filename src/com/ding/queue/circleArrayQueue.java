//数字模拟环形队列，为假队列
package com.ding.queue;

import java.util.Scanner;

public class circleArrayQueue {
    public static void main(String[] args) {
        //创建一个队列
        circleArray circleArray = new circleArray(4);//有效数据最大为3
        char key = ' ';//接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop){
            System.out.println("s(show);显示队列");
            System.out.println("e(exit);退出程序");
            System.out.println("a(add);添加数据到队列");
            System.out.println("g(get);从队列取出数据");
            System.out.println("h(head);查看队列头的数据");
            key = scanner.next().charAt(0);//接收一个字符
            switch (key){
                case 's':
                    circleArray.showQueue();
                    break;
                case 'a':
                    System.out.println("输入数字");
                    int value = scanner.nextInt();
                    circleArray.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res = circleArray.getQueue();
                        System.out.printf("取出的数据是%d\n", res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = circleArray.headQueue();
                        System.out.printf("队列头的数据是%d\n", res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                default:
                    break;
            }
        }
        System.out.println("程序退出");
    }
}
class circleArray{
    private int maxSize;//数组最大容量
    private int front;//队列头
    private int rear;//队列尾
    private int[] arr;//存放数组
    public circleArray(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
    }
    public boolean isFull(){
        return (rear + 1) % maxSize == front;
    }
    public boolean isEmpty(){
        return rear == front;
    }
    public void addQueue(int n){
        if (isFull()){
            System.out.println("队列满");
            return;
        }
        arr[rear] = n;
        //将rear后移，必须考虑取模
        rear = (rear + 1) % maxSize;
    }
    public int getQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列空");
        }
        //分析出front是指向队列的第一个元素
        // 1.先把front对应的值保留到一个临时变量
        // 2.将front后移，考虑取模
        // 3.将临时变量返回
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }
    public void showQueue(){
        if (isEmpty()){
            System.out.println("队列空");
            return;
        }
        for (int i = front; i < front + size();i++){
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }
    //求出当前队列的有效数据个数
    public int size(){
        return (rear + maxSize - front) % maxSize;
    }
    public int headQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列空");
        }
        return arr[front];
    }
}

