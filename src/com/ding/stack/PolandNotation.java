package com.ding.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {
        String expression = "1+((2+3)*4)-5";
        List<String> infixExpressionList = toInfixExpressionList(expression);
        System.out.println(infixExpressionList);
        List<String> paresSuffixExpressionList = paresSuffixExpressionList(infixExpressionList);
        System.out.println(paresSuffixExpressionList);
        System.out.println(calculate(paresSuffixExpressionList));
        //定义一个逆波兰表达式
//        String suffixExpression = "3 4 + 5 * 6 -";
//        List<String> rpnList = getListString(suffixExpression);
//        System.out.println(rpnList);
//        int res = calculate(rpnList);
//        System.out.println(res);
    }
    public static List<String> paresSuffixExpressionList(List<String> ls){
        Stack<String> s1 = new Stack<String>();
        List<String> s2 = new ArrayList<String>();
        for (String item : ls){
            if (item.matches("\\d+")){
                s2.add(item);
            }else if (item.equals("(")){
                s1.push(item);
            }else if (item.equals(")")){
                while (!s1.peek().equals("(")){
                    s2.add(s1.pop());
                }
                s1.pop();
            }else {
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)){
                    s2.add(s1.pop());
                }
                s1.push(item);
            }
        }
        while (s1.size() != 0){
            s2.add(s1.pop());
        }
        return s2;
    }
    //将中缀转为List
    public static List<String> toInfixExpressionList(String s){
        //定义List，存放数据
        List<String> ls = new ArrayList<>();
        int i = 0;//用于遍历s中缀
        String str;//拼接
        char c;//每遍历到一个字符，放入c
        do {
            //如果是非数字，加入ls
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
                ls.add("" + c);
                i++;
            }else {
                str = "";
                while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57){
                    str += c;
                    i++;
                }
                ls.add(str);
            }
        }while (i < s.length());
        return ls;
    }
    //将一个逆波兰表达式，依次将数据和运算符放入数组
    public static List<String> getListString(String suffixExpression){
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<>();
        for (String ele : split){
            list.add(ele);
        }
        return list;
    }
    //完成对逆波兰表达式放入运算
    public static int calculate(List<String> ls){
        //创建栈
        Stack<String> stack = new Stack<String>();
        //遍历
        for (String item : ls){
            //正在表达取出数
            if (item.matches("\\d+")){
                stack.push(item);
            }else {
                int numb2 = Integer.parseInt(stack.pop());
                int numb1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")){
                    res = numb1 + numb2;
                }else if (item.equals("-")){
                    res = numb1 - numb2;
                }else if (item.equals("*")){
                    res = numb1 * numb2;
                }else if (item.equals("/")){
                    res = numb1 / numb2;
                }else {
                    throw new RuntimeException("运算符有误");
                }
                //res入栈
                stack.push(res + "");
            }
        }
        return Integer.parseInt(stack.pop());
    }
}
//优先级类
class Operation{
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;
    public static int getValue(String operation){
        int result = 0;
        switch (operation){
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("不存在次运算符");
                break;
        }
        return result;
    }
}