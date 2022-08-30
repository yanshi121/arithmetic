package com.ding.linkedlist;

import java.util.Stack;
import java.util.logging.Handler;

public class singleLinkedList {
    public static void main(String[] args) {
        //测试
        //先创建节点
        heroNode hero1 =  new heroNode(1, "宋江", "及时雨");
        heroNode hero2 =  new heroNode(2, "卢俊义", "玉麒麟");
        heroNode hero3 =  new heroNode(3, "吴用", "智多星");
        heroNode hero4 =  new heroNode(4, "林冲", "豹子头");
        //创建一个链表
        singleLinkedListHero singleLinkedListHero = new singleLinkedListHero();
        //加入
//        singleLinkedListHero.add(hero1);
//        singleLinkedListHero.add(hero2);
//        singleLinkedListHero.add(hero3);
//        singleLinkedListHero.add(hero4);
        //安装编号添加
        singleLinkedListHero.addByOrder(hero1);
        singleLinkedListHero.addByOrder(hero4);
        singleLinkedListHero.addByOrder(hero3);
        singleLinkedListHero.addByOrder(hero2);
        //测试重复编号
        singleLinkedListHero.addByOrder(hero2);
        //修改前
        singleLinkedListHero.list();
        //修改节点
        heroNode newHeroNode  = new heroNode(2, "小卢", "玉麒麟~");
        singleLinkedListHero.update(newHeroNode);
        //修改后
        System.out.println("修改后");
        singleLinkedListHero.list();
        //删除节点
        singleLinkedListHero.del(2);
        System.out.println("修改后");
        //删除后
        singleLinkedListHero.list();
        singleLinkedListHero.del(2);
        //节点个数
        System.out.println("有效节点数" + getLength(singleLinkedListHero.getHead()));
        //倒数第K个节点
        heroNode res = findLastNode(singleLinkedListHero.getHead(), 2);
        System.out.println("res" + res);
        //链表反转
        reverseList(singleLinkedListHero.getHead());
        singleLinkedListHero.list();
        //翻转输出
        System.out.println("逆序打印");
        reversePrint(singleLinkedListHero.getHead());
    }
    //利用栈将链表数据翻转
    public static void reversePrint(heroNode head){
        if (head.next == null){
            return;//空链表
        }
        //创建一个栈，将节点压入栈
        Stack<heroNode> stack = new Stack<heroNode>();
        heroNode cur = head.next;
        //将链表所有数据压入栈
        while (cur != null){
            stack.push(cur);
            cur = cur.next;//cur后移
        }
        //将栈中节点进行打印
        while (stack.size() > 0){
            System.out.println(stack.pop());//
        }
    }
    //单链表反转
    public static void reverseList(heroNode head){
        //如果为空，或者只有一个节点，无需反正，直接返回
        if (head.next == null || head.next.next == null){
            return;
        }
        //定义一个辅助指针，帮助遍历原来的链表
        heroNode cur = head.next;
        heroNode next = null;//指向当前节点的下一个节点
        heroNode reverseHead = new heroNode(0, "", "");
        //遍历原来的链表，每遍历一个就将其取出，并放在新链表的最前面
        while (cur != null){
            next = cur.next;//先暂时保存当前节点的下一个节点因为后面需要使用
            cur.next = reverseHead.next;//将cur的下一个节点指向新的链表的最前端
            reverseHead.next = cur;//将cur连接到新的链表
            cur = next;//让cur后移
        }
        //将head.next指向reverseHead.next，实现单链表反正
        head.next = reverseHead.next;
    }
    //查找单链表倒数第k个节点
    //1.一个方法接收head节点，同时接收一个index
    //2.index表示倒数第k个节点
    //3.先把链表从头到尾遍历，得到链表总的长度
    //4.得到size后从链表的第一个开始遍历（size-index）个，就可以得到
    //5.如果找到，则返回节点，否则返回null
    public static heroNode findLastNode(heroNode head, int index){
        //判断如果链表为空，返回null
        if (head.next == null){
            return null;
        }
        //第一个遍历得到链表长度
        int size = getLength(head);
        //第二次遍历size-index的位置，就是倒数的第K个元素
        //先做一个index的校验
        if (index <= 0 || index > size){
            return null;
        }
        //定义辅助变量,for循环定位到倒数index
        heroNode cur = head.next;
        for (int i = 0; i < size - index; i++){
            cur = cur.next;
        }
        return cur;
    }
    //获取到单链表节点个数
    public static int getLength(heroNode head){
        if (head.next == null){
            //空链表
            return 0;
        }
        //定义辅助变量,没有统计头结点
        int length = 0;
        heroNode cur = head.next;
        while (cur != null){
            length++;
            cur = cur.next;//遍历
        }
        return length;

    }
}
//定义singleLinkedList管理英雄
class singleLinkedListHero{
    //先初始化一个头结点，不动，不存放具体数据
    private heroNode head = new heroNode(0, "", "");
    //返回头结点
    public heroNode getHead(){
        return head;
    }
    //添加节点到单项列表
    //思路，当不考虑编号顺序时
    //1.找到当前链表的最后节点
    //2.将最后这个节点的next指向新的节点
    public void add(heroNode heroNode){
        //因为head节点不能动，因此需要一个辅助遍历 temp
        heroNode temp = head;
        //遍历链表，找到最后
        while (true){
            //找到链表的最后
            if (temp.next == null){
                break;
            }
            //如果没有找到最后，将temp后移
            temp = temp.next;
        }
        //当退出while循环时，temp就指向链表的最后
        //将最后这个节点next指向新节点
        temp.next = heroNode;
    }
    //修改节点信息，根据no编号来修改，即no编号不能改
    //根据newHeroNode的no来修改
    public void update(heroNode newHeroNode){
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点，根据no编号
        //定义一个辅助变量
        heroNode temp = head.next;
        boolean flag = false;//表示找到该节点
        while (true){
            if (temp == null){
                break;
                //已遍历完链表
            }
            if (temp.no == newHeroNode.no){
                //找到
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //根据flag判断是否找到修改节点
        if (flag){
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        }else {
            //还没有找到
            System.out.printf("没有找到编号%d的节点\n", newHeroNode.no);
        }
    }
    //删除节点
    //1.head不能动，因此我们需要一个temp辅助节点找到待删除的前一个节点
    //2.说明我们在比较时，是temp.next.no和需要删除的节点的no比较
    public void del(int no){
        heroNode temp = head;
        boolean flag = false;//标志是否找到待删除节点
        while (true) {
            if (temp.next == null) {
                //已经找到链表的最后
                break;
            }
            if (temp.next.no == no) {
                //找到待删除节点的前一个节点temp
                flag = true;
                break;
            }
            temp = temp.next;//temp后移，遍历
        }
        //判断flag
        if (flag){
            //找到，可以删除

            temp.next = temp.next.next;
        }else {
            System.out.printf("要删除的%d节点找不到\n", no);
        }
    }
    //显示链表篇[遍历]
    public void list(){
        //判断链表是否为空
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        //因为头结点不能动，需要辅助变量来遍历
        heroNode temp = head.next;
        while (true){
            //判断是否到最后
            if (temp == null){
                break;
            }
            //输出节点信息
            System.out.println(temp);
            //将next后移
            temp = temp.next;
        }
    }
    //根据排名插入指定位置
    //如果有此排名，则添加失败，给出提示
    public void addByOrder(heroNode heroNode){
        //因为head节点不能动，因此需要一个辅助指针来帮助找到添加位置
        //因为单链表，因此我们找的temp是位于添加位置的前一个节点，否则添加不了
        heroNode temp = head;
        boolean flag = false;//标识添加的编号是否存在，默认false
        while (true){
            if (temp.next == null){
                //说明temp在链表最后
                break;
            }
            if (temp.next.no > heroNode.no){
                //位置找到了
                break;
            }else if (temp.next.no == heroNode.no){
                //说明希望添加的heroNode的编号已存在
                flag = true;//说明编号存在
                break;
            }
            temp = temp.next;
        }
        //判断flag的值
        if (flag){
            //不能添加，说明编号存在
            System.out.printf("此英雄编号 %d 已经存在\n", heroNode.no);
        }else {
            //插入到链表中，temp后面
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }
}
//定义HeroNode，每个HeroNode对象就是一个节点
class heroNode{
    public int no;
    public String name;
    public String nickname;
    public heroNode next;//指向下一节点
    //构造器
    public heroNode(int no, String name, String nickname){
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }
    //为了显示方便，重写头String方法
    @Override
    public String toString(){
        return "heroNode [no=" + no + ", name=" + name + ", nickname=" + nickname + "]";
    }
}
