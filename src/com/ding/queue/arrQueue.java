//数字模拟队列，为假队列
package com.ding.queue;

import java.util.Scanner;

public class arrQueue {
    public static void main(String[] args) {
        //创建一个队列
        arrQue arrQue = new arrQue(3);
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
                    arrQue.showQueue();
                    break;
                case 'a':
                    System.out.println("输入数字");
                    int value = scanner.nextInt();
                    arrQue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res = arrQue.getQueue();
                        System.out.printf("取出的数据是%d\n", res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = arrQue.headQueue();
                        System.out.printf("队列投的数据是%d\n", res);
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
//使用数组模拟队列
class arrQue{
    private int maxSize;//数组最大容量
    private int front;//队列头
    private int rear;//队列尾
    private int[] arr;//存放数组
    //队列构造器
    public arrQue(int arrMaxSize){
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = -1;//指向队列头部，分析出front是指向队列头的的第一个位置
        rear = -1;//指向队列尾，指向队尾的数据（即就是队列最后的一个数据）
    }
    //判断队列是否满
    public boolean isFull(){
        return rear == maxSize - 1;
    }
    //判断队列是否为空
    public boolean isEmpty(){
        return  rear == front;
    }
    //添加数据到队列
    public void addQueue(int n){
        //判断队列是否满
        if (isFull()){
            System.out.println("队列满");
            return;
        }
        rear++;//rear后移
        arr[rear] = n;
    }
    //弹出队列数据
    public int getQueue(){
        //判断队列是否空
        if (isEmpty()){
            //抛出异常
            throw new RuntimeException("队列空");
        }
        front++;//front后移
        return arr[front];
    }
    //显示队列数据
    public void showQueue(){
        if (isEmpty()){
            System.out.println("队列空");
            return;
        }
        for (int i = 0; i < arr.length;i++){
            System.out.printf("arr[%d]=%d\n", i, arr[i]);
        }
    }
    //显示队列头数据
    public int headQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列空");
        }
        return arr[front + 1];
    }

}
