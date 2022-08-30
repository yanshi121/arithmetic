package com.ding.linkedlist;

public class doubleLinkList {
    public static void main(String[] args) {
        //先创建节点
        heroNode2 hero1 =  new heroNode2(1, "宋江", "及时雨");
        heroNode2 hero2 =  new heroNode2(2, "卢俊义", "玉麒麟");
        heroNode2 hero3 =  new heroNode2(3, "吴用", "智多星");
        heroNode2 hero4 =  new heroNode2(4, "林冲", "豹子头");
        //创建一个链表
        doubleList doubleList = new doubleList();
        doubleList.add(hero1);
        doubleList.add(hero2);
        doubleList.add(hero3);
        doubleList.add(hero4);
        //输出
        System.out.println("原始");
        doubleList.list();
        System.out.println("修改");
        heroNode2 newHeroNode = new heroNode2(4, "公孙胜", "入云龙");
        doubleList.update(newHeroNode);
        doubleList.list();
        System.out.println("删除");
        doubleList.del(3);
        doubleList.list();
    }
}
//创建双向链表类
class doubleList{
    //先初始化一个头结点，不动，不存放具体数据
    private heroNode2 head = new heroNode2(0, "", "");
    //返回头结点
    public heroNode2 getHead(){
        return head;
    };
    //遍历双向链表方法
    //显示链表篇[遍历]
    public void list(){
        //判断链表是否为空
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        //因为头结点不能动，需要辅助变量来遍历
        heroNode2 temp = head.next;
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
    //添加节点到双向列表
    public void add(heroNode2 heroNode){
        //因为head节点不能动，因此需要一个辅助遍历 temp
        heroNode2 temp = head;
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
        //形成双向链表
        temp.next = heroNode;
        heroNode.pre = temp;
    }
    //修改节点内容，与单向链表一样
    public void update(heroNode2 newHeroNode){
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点，根据no编号
        //定义一个辅助变量
        heroNode2 temp = head.next;
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
    //从双向链表删除节点
    //1.对于双向链表，直接找到
    //2.找到后自我删除即可
    public void del(int no){
        //判断空
        if (head.next == null){
            System.out.println("链表空");
            return;
        }
        heroNode2 temp = head.next;
        boolean flag = false;//标志是否找到待删除节点
        while (true) {
            if (temp == null) {
                //已经找到链表的最后
                break;
            }
            if (temp.no == no) {
                //找到待删除节点的前一个节点temp
                flag = true;
                break;
            }
            temp = temp.next;//temp后移，遍历
        }
        //判断flag
        if (flag){
            //找到，可以删除
            temp.pre.next = temp.next;
            //预防空指针
            if (temp.next != null){
                temp.next.pre = temp.pre;
            }
        }else {
            System.out.printf("要删除的%d节点找不到\n", no);
        }
    }
}
//定义HeroNode2，每个HeroNode2对象就是一个节点
class heroNode2{
    public int no;
    public String name;
    public String nickname;
    public heroNode2 next;//指向下一节点
    public heroNode2 pre;//指向上一节点
    //构造器
    public heroNode2(int no, String name, String nickname){
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
