package com.ding.recursion;

import java.util.Objects;

public class MiGong {
    public static void main(String[] args) {
        //创建二维数组，模拟迷宫
        //地图
        String[][] map = new String[8][7];
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 7; j++){
                map[i][j] = "-";
            }
        }
        //用1表示墙
        //上下全部置为1
        for (int i = 0; i < 7; i++){
            map[0][i] = "*";
            map[7][i] = "*";
        }
        //左右置为1
        for (int i = 0; i < 8; i++){
            map[i][0] = "*";
            map[i][6] = "*";
        }
        //设置挡板
        map[3][1] = "*";
        map[3][2] = "*";
        map[5][2] = "*";
        map[5][3] = "*";
        map[5][4] = "*";
        map[5][5] = "*";
        //输出地图
        System.out.println("地图情况");
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 7; j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        //使用递归回溯给小球找路
        setWay(map, 1, 1);
        //迷宫路线
        System.out.println("迷宫路线");
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 7; j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
    //使用递归回溯给小球找路
    //1.map表示赌徒
    //2.i,j表示从那个位置开始(1,1)
    //3.如果小球能到map[6][5]位置，这说明通路找到
    //4.约定：当map[i][j]为-表示改点没有走过，当为*时表示墙，^表示通路可走，!表示走不通
    //5.再走迷宫时，需要一个策略（方法）下->右->上->左，如果走不通，在回溯
    /**
     * @param map  表示地图
     * @param i    从哪个位置开始
     * @param j    从哪个位置开始
     * @return     找到路返回TRUE，否则返回FALSE
     */
    public static boolean setWay(String[][] map, int i, int j){
        if (Objects.equals(map[6][5], "^")){
            return true;
        }else {
            if (Objects.equals(map[i][j], "-")){
                //表示当前没有走过
                map[i][j] = "^";//假定可以走通
                if (setWay(map, i + 1, j)){
                    //向下走
                    return true;
                }else if (setWay(map, i, j + 1)){
                    //向右走
                    return true;
                }else if (setWay(map, i - 1, j)){
                    //向上走
                    return true;
                }else if (setWay(map, i, j - 1)){
                    //向左走
                    return true;
                }else {
                    //说明改点走不通，是死路
                    map[i][j] = "!";
                    return false;
                }
            }else {
                return false;
            }
        }
    }
}
