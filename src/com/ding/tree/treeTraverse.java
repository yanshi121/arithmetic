package com.ding.tree;


public class treeTraverse {
    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(7);
        TreeNode treeNode2 = new TreeNode(34);
        TreeNode treeNode3 = new TreeNode(13);
        TreeNode treeNode4 = new TreeNode(3);
        TreeNode treeNode5 = new TreeNode(32);
        TreeNode treeNode6 = new TreeNode(1);
        TreeNode treeNode7 = new TreeNode(10);
        TreeNode treeNode8 = new TreeNode(38);
        TreeNode treeNode9 = new TreeNode(33);
        Tree tree = new Tree();
        tree.add(treeNode1);
        tree.add(treeNode2);
        tree.add(treeNode3);
        tree.add(treeNode4);
        tree.add(treeNode5);
        tree.add(treeNode6);
        tree.add(treeNode7);
        tree.add(treeNode8);
        tree.add(treeNode9);
        tree.preOrderTraverse1(tree.root);
        System.out.println();
        tree.inOrderTraverse1(tree.root);
        System.out.println();
        tree.postOrderTraverse1(tree.root);
//        TreeNode a = tree.findNode(treeNode5);
//        System.out.println(a.father.data);
//        System.out.println();
//        System.out.println("删除节点32");
//        tree.remove(treeNode5);
//        tree.inOrderTraverse1(tree.root);
//        System.out.println();
//        System.out.println("删除节点32");
//        tree.remove(treeNode5);
//        tree.inOrderTraverse1(tree.root);
//        System.out.println();
//        System.out.println("删除节点34");
//        tree.remove(treeNode2);
//        tree.inOrderTraverse1(tree.root);
//        System.out.println();
//        System.out.println("删除节点3");
//        tree.remove(treeNode4);
//        tree.inOrderTraverse1(tree.root);
    }
}
class Tree{
    public TreeNode root = new TreeNode('a');
    public void add(TreeNode treeNode) {
        if (root.data == 'a'){
            root.data = treeNode.data;
            return;
        }
        TreeNode temp = root;
        while (true){
            if (temp.data < treeNode.data){
                if (temp.right == null){
                    break;
                }
                temp = temp.right;
            }else if (temp.data > treeNode.data){
                if (temp.left == null){
                    break;
                }
                temp = temp.left;
            }else {
                break;
            }
        }
        if (temp.data < treeNode.data){
            temp.right = treeNode;
            treeNode.father = temp;
        }else if (temp.data > treeNode.data){
            temp.left = treeNode;
            treeNode.father = temp;
        }
    }
    public TreeNode findNode(TreeNode treeNode){
        TreeNode temp = root;
        while (true) {
            try {
                if (treeNode.data < temp.data) {
                    temp = temp.left;
                } else if (treeNode.data > temp.data) {
                    temp = temp.right;
                } else if (treeNode.data == temp.data) {
                    break;
                }
            } catch (Exception e){
                System.out.println("未找到节点");
                break;
            }
        }
        return temp;
    }
    public void remove(TreeNode treeNode){
        if (root.left == null && root.right == null){
            System.out.println("树为空");
            return;
        }
        TreeNode temp = root;
        while (true) {
            try {
                if (treeNode.data < temp.data) {
                    temp = temp.left;
                } else if (treeNode.data > temp.data) {
                    temp = temp.right;
                } else if (treeNode.data == temp.data) {
                    break;
                }
            } catch (Exception e){
                System.out.println("未找到节点");
                return;
            }
        }
        if (temp.left == null && temp.right == null){
            if (temp.father.left == temp) {
                temp.father.left = null;
            }else if (temp.father.right == temp) {
                temp.father.right = null;
            }
        }else if (temp.left == null || temp.right != null){
            if (temp.father.left == temp) {
                temp.father.left = temp.left;
            }else if (temp.father.right == temp) {
                temp.father.right = temp.left;
            }
        }else if (temp.right == null || temp.left != null) {
            System.out.println(1);
        }else if (temp.left != null && temp.right != null){
            if (temp.father.left == temp) {
                TreeNode temp1 = temp.right;
                while (true) {
                    if (temp1.left == null) {
                        break;
                    }
                    temp1 = temp1.left;
                }
                temp1.left = temp.left;
            }else if (temp.father.right == temp){
                TreeNode temp1 = temp.right;
                while (true) {
                    if (temp1.right == null) {
                        break;
                    }
                    temp1 = temp1.right;
                }
                temp1.left = temp.left;
                temp.father.right = temp1;
            }
        }
    }
    public void preOrderTraverse1(TreeNode root) {
        if (root != null) {
            System.out.print(root.data+"  ");
            preOrderTraverse1(root.left);
            preOrderTraverse1(root.right);
        }
    }
    public void inOrderTraverse1(TreeNode root) {
        if (root != null) {
            inOrderTraverse1(root.left);
            System.out.print(root.data+"  ");
            inOrderTraverse1(root.right);
        }
    }
    public void postOrderTraverse1(TreeNode root) {
        if (root != null) {
            postOrderTraverse1(root.left);
            postOrderTraverse1(root.right);
            System.out.print(root.data+"  ");
        }
    }
}
class TreeNode{
    public int data;
    public TreeNode left;
    public TreeNode right;
    public TreeNode father;
    public TreeNode(int data){
        this.data = data;
    }
}