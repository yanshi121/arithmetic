package com.ding.find;


import java.util.Random;

public class find {
    public static void main(String[] args) {
        findMethod findMethod = new findMethod();
        int[] array = new int[1000];
        for (int i = 0; i < 1000; i++){
            array[i] = i;
        }
        System.out.println(findMethod.binarySearch(array, 173));
        SkipList skipList = new SkipList();
        skipList.insert(50);
        skipList.insert(15);
        skipList.insert(13);
        skipList.insert(20);
        skipList.insert(100);
        skipList.insert(75);
        skipList.insert(99);
        skipList.insert(76);
        skipList.insert(83);
        skipList.insert(65);
        skipList.printList();
        skipList.search(50);
        skipList.remove(50);
        skipList.search(50);
        String str = "aacdesadsdfer";
        String pattern = "adsd";
        System.out.println("第一次出现位置:" + findMethod.rabinKarp(str, pattern));
        int index = findMethod.kmp(str, pattern);
        System.out.println("第一次出现位置:" + index);
    }
}
class findMethod{
    public int binarySearch(int[] array, int target){
        int start = 0;
        int end = array.length - 1;
        int mid;
        while (start <= end){
            mid = start + ((end - start) / 2);
            if (array[mid] == target){
                return mid;
            }else {
                if (array[mid] < target){
                    start = mid + 1;
                }else {
                    end = mid - 1;
                }
            }
        }
        return -1;
    }
    public int rabinKarp(String str, String pattern){
        int m = str.length();
        int n = pattern.length();
        int patternCode = hash(pattern);
        int strCode = hash(str.substring(0, n));
        for (int i = 0; i < (m - n + 1); i++){
            if ((strCode == patternCode) && copmpareString(i, str, pattern)){
                return i;
            }
            if (i < (m - n)){
                strCode = nextHash(str, strCode, i, n);
            }
        }
        return -1;
    }
    private static int hash(String str){
        int hashcode = 0;
        for (int i = 0; i < str.length(); i++){
            hashcode += (str.charAt(i) - 'a');
        }
        return hashcode;
    }
    private static int nextHash(String str, int hash, int index, int n){
        hash -= (str.charAt(index) - 'a');
        hash += (str.charAt(index + n) - 'a');
        return hash;
    }
    private static boolean copmpareString(int i, String str, String pattern){
        String strSub = str.substring(i, i + pattern.length());
        return strSub.equals(pattern);
    }
    public int kmp(String str, String pattern){
        int[] next = getNext(pattern);
        int j = 0;
        for (int i = 0; i < str.length(); i++){
            while ((j > 0) && (str.charAt(i) != pattern.charAt(j))){
                j = next[j];
            }
            if (str.charAt(i) == pattern.charAt(j)){
                j++;
            }
            if (j == pattern.length()){
                return i - pattern.length() + 1;
            }
        }
        return -1;
    }
    private static int[] getNext(String pattern){
        int[] next = new int[pattern.length()];
        int j = 0;
        for (int i = 2; i < pattern.length(); i++){
            while ((j != 0) && (pattern.charAt(i) != pattern.charAt(i - 1))){
                j = next[j];
            }
            if (pattern.charAt(j) == pattern.charAt(i - 1)){
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}
class SkipList{
    private static final double PROMOTE_RATE = 0.5;
    private Node head,tail;
    private int maxLevel;
    public SkipList(){
        head = new Node(Integer.MIN_VALUE);
        tail = new Node(Integer.MAX_VALUE);
        head.right = tail;
        tail.left = head;
    }
    public Node search(int data){
        Node p = findNode(data);
        if (p.data == data){
            System.out.println("找到节点: " + data);
            return p;
        }
        System.out.println("未找到节点: " + data);
        return null;
    }
    public Node findNode(int data){
        Node node = head;
        while (true){
            while (node.right.data != Integer.MAX_VALUE && node.right.data <= data){
                node = node.right;
            }
            if (node.down == null){
                break;
            }
            node = node.down;
        }
        return node;
    }
    public void insert(int data){
        Node preNode = findNode(data);
        if (preNode.data == data){
            return;
        }
        Node node = new Node(data);
        appendNode(preNode, node);
        int currentLevel = 0;
        Random random = new Random();
        while (random.nextDouble() < PROMOTE_RATE){
            if (currentLevel == maxLevel){
                addLevel();
            }
            while (preNode.up == null){
                preNode = preNode.left;
            }
            preNode = preNode.up;
            Node upperNode = new Node(data);
            appendNode(preNode, upperNode);
            upperNode.down = node;
            node.up = upperNode;
            node = upperNode;
            currentLevel++;
        }
    }
    private void appendNode(Node preNode, Node newNode){
        newNode.left = preNode;
        newNode.right = preNode.right;
        preNode.right.left = newNode;
        preNode.right = newNode;
    }
    private void addLevel(){
        maxLevel++;
        Node p1 = new Node(Integer.MIN_VALUE);
        Node p2 = new Node(Integer.MAX_VALUE);
        p1.right = p2;
        p2.left = p1;
        p1.down = head;
        head.up = p1;
        p2.down = tail;
        tail.up = p2;
        head = p1;
        tail = p2;
    }
    public boolean remove(int data){
        Node removeNode = search(data);
        if (removeNode == null){
            return false;
        }
        int currentLevel = 0;
        while (removeNode != null){
            removeNode.right.left = removeNode.left;
            removeNode.left.right = removeNode.right;
            if (currentLevel != 0 && removeNode.left.data == Integer.MIN_VALUE && removeNode.right.data == Integer.MAX_VALUE){
                removeLevel(removeNode.left);
            }else {
                currentLevel++;
            }
            removeNode = removeNode.up;
        }
        return true;
    }
    private void removeLevel(Node leftNode){
        Node rightNode = leftNode.right;
        if (leftNode.up == null){
            leftNode.down = head;
            leftNode.down.up = null;
            rightNode.down.up = null;
        }else {
            leftNode.up.down = leftNode.down;
            leftNode.down.up = leftNode.up;
            rightNode.up.down = rightNode.down;
            rightNode.down.up = rightNode.up;
        }
        maxLevel--;
    }
    public void printList(){
        Node node = head;
        while (node.down != null){
            node = node.down;
        }
        while (node.right.data != Integer.MAX_VALUE){
            System.out.print(node.right.data + " ");
            node = node.right;
        }
        System.out.println();
    }
}
class Node{
    public int data;
    public Node up, down, left, right;
    public Node(int data){
        this.data = data;
    }
}
