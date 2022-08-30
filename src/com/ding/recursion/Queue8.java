package com.ding.recursion;

public class Queue8 {
    //定义一个max表示共有多少个皇后
    int max = 8;
    //定义数组array，保存皇后放置位置
    int[] array = new int[max];
    static int count = 0;
    static int judgeCount = 0;
    public static void main(String[] args) {
        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.printf("%d", count);
        System.out.println();
        System.out.printf("%d", judgeCount);
    }
    //编写方法，放置第n个皇后
    private void check(int n){
        if (n == max){
            print();
            return;
        }
        //一次放入皇后，并判断是否冲突
        for (int i = 0; i < max; i++){
            //先吧当前这个皇后n，放到改行的第1列
            array[n] = i;
            //判断放置第n个皇后到i，是否冲突
            if (judge(n)){
                //接着放
                check(n + 1);
            }
        }
    }
    //查看当我们放置第n皇后，就去检测该皇后是否和前面摆放的冲突
    /**
     * @param n  表示第n个皇后
     */
    private boolean judge(int n){
        judgeCount++;
        for (int i = 0; i < n; i++){
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])){
                return false;
            }
        }
        return true;
    }
    //写一个方法，可以将将皇后拜放位置输出
    private void print(){
        count++;
        for (int i = 0; i < array.length; i++){
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
