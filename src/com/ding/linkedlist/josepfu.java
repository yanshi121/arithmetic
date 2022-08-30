package com.ding.linkedlist;

public class josepfu {
    public static void main(String[] args) {
        //测试
        circleSingleLinkList circleSingleLinkList = new circleSingleLinkList();
        circleSingleLinkList.addBoy(5);
        circleSingleLinkList.showBoy();
        //小孩出圈
        circleSingleLinkList.countBoy(1, 2, 5);
    }
}

//创建环形的单向链表
class circleSingleLinkList {
    //创建first节点
    private boy first = new boy(-1);

    //加入小孩节点,构建成环形链表
    public void addBoy(int numbs) {
        //numbs做一个数据校验
        if (numbs < 1) {
            System.out.println("numbs的值不正确");
            return;
        }
        boy curBoy = null;//辅助指针，帮助创建环形链表
        //使用for循环创建环形链表
        for (int i = 1; i <= numbs; i++) {
            //根据编号创建小孩节点
            boy boy = new boy(i);
            //如果是第一个小孩
            if (i == 1) {
                first = boy;
                first.setNext(first);//构成一个环
                curBoy = first;//让curBoy指向第一个小孩
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    //遍历当前环形链表
    public void showBoy() {
        //链表是否为空
        if (first == null) {
            System.out.println("为空");
            return;
        }
        //因为first不能动，因此任然使用辅助指针完成遍历
        boy curBoy = first;
        while (true) {
            System.out.printf("小孩编号 %d \n", curBoy.getNo());
            if (curBoy.getNext() == first) {//说明已经遍历完毕
                break;
            }
            curBoy = curBoy.getNext();
        }
    }
    //根据用户输入，计算出圈顺序

    /**
     * @param startNo  表示从第几个小孩开始
     * @param countNum 表示数几下
     * @param numbs    表示最初有多少小孩在圈中
     */
    public void countBoy(int startNo, int countNum, int numbs) {
        //先对数据进行检验
        if (first == null || startNo < 1 || startNo > numbs) {
            System.out.println("参数不正确");
            return;
        }
        //创建辅助指针，帮助小孩出圈
        boy helper = first;
        //需要创建一个辅助指针，事先应该指向环形链表的最后一个节点
        while (true) {
            if (helper.getNext() == first) {//说明helper指向最后一个小孩节点
                break;
            }
            helper = helper.getNext();
        }
        //小孩报数前，先让first和helper移动k-1次
        for (int j = 0; j < startNo - 1; j++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        //当小孩报数时，让first和helper指针同时的移动m-1次，然后出圈
        //这里是一个循环操作，知道圈中只有一个节点
        while (true) {
            if (helper == first) {//说明圈中只有一个节点
                break;
            }
            //让first和helper指针同时的移动countNum-1次
            for (int j = 0; j < countNum - 1; j++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            //这时first指向节点，就是要出圈的小孩节点
            System.out.printf("小孩%d出圈\n", first.getNo());
            //这时将first指向小孩节点出圈
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后留在去圈中的小孩编号%d \n", helper.getNo());
    }

}

//创建Boy类，表示一个节点
class boy {
    private int no;//编号
    private boy next;//指向下一节点，默认null

    public boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public boy getNext() {
        return next;
    }

    public void setNext(boy next) {
        this.next = next;
    }
}
