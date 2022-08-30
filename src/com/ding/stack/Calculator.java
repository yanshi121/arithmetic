package com.ding.stack;

public class Calculator {
    public static void main(String[] args) {
        //根据前面老师思路，完成表达式的运算
        String expression = "80+2*6-1";
        //创建两个栈，一个数，一个符号
        ArrayStack2 numbStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        //定义需要的相关变量
        int index = 0;
        int numb1 = 0;
        int numb2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';
        String keepNumb = "";
        //循环扫描expression
        while (true) {
            //依次得到expression的每一个字符
            ch = expression.subSequence(index, index + 1).charAt(0);
            //判断ch什么
            if (operStack.isOper(ch)) {//如果是运算符
                //判断当前的符号栈是否为空
                if (!operStack.isEmpty()) {
                    //如果符号栈有操作符，就进行比较，如果当前的操作符的优先级小于或者等于栈中的操作符，就需要从数栈中pop出两个数
                    //再从符号栈中pop出一个符号，进行运算，将得到的结果，入数栈，然后将当前操作符入符号栈
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        numb1 = numbStack.pop();
                        numb2 = numbStack.pop();
                        oper = operStack.pop();
                        res = numbStack.cal(numb1, numb2, oper);
                        //把运算的结果入数栈
                        numbStack.push(res);
                        operStack.push(ch);
                    }else {
                        //如何当前的操作符的优先级大于栈中的操作符，就直接入符号栈
                        operStack.push(ch);
                    }
                } else {
                    //如果为空直接入符号栈
                    operStack.push(ch);
                }
            }else {//如果是数则直接入数栈
                keepNumb += ch;
                if (index == expression.length() - 1){
                    numbStack.push(Integer.parseInt(keepNumb));
                }else {
                    if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                        numbStack.push(Integer.parseInt(keepNumb));
                        keepNumb = "";
                    }
                }
            }
            //让index加1，并判断是否扫描到expression最后
            index++;
            if (index >= expression.length()){
                break;
            }
        }
        //当表达式扫描完后，就顺序从数栈和符号栈中pop出相应的书和符号，并运行
        while (true){
            //如果符号栈为空，则计算到最后的结果，数栈中只有一个数字
            if (operStack.isEmpty()){
                break;
            }
            numb1 = numbStack.pop();
            numb2 = numbStack.pop();
            oper = operStack.pop();
            res = numbStack.cal(numb1, numb2, oper);
            numbStack.push(res);
        }
        System.out.printf("表达式%s = %d", expression, numbStack.pop());
    }
}

//先建一个栈
class ArrayStack2 {
    private int maxSize;//栈大小
    private int[] stack;//数组。数组模拟栈
    private int top = -1;//top表示栈顶，初始化为-1

    //构造器
    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //增加一个方法，可以返回当前顶的值
    public int peek() {
        return stack[top];
    }

    //栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈
    public void push(int value) {
        //判断栈满
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }

    //出栈
    public int pop() {
        //判空
        if (isEmpty()) {
            //抛出异常
            throw new RuntimeException("栈空");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //遍历栈
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }

    //返回运算符优先级，优先级使用数字表示，数字大，优先级高
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;//只有加减乘除
        }
    }

    //判断是不是一个运算符
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    //计算方法
    public int cal(int numb1, int numb2, int oper) {
        int res = 0;
        switch (oper) {
            case '+':
                res = numb1 + numb2;
                break;
            case '-':
                res = numb2 - numb1;
                break;
            case '*':
                res = numb1 * numb2;
                break;
            case '/':
                res = numb2 / numb1;
                break;
            default:
                break;
        }
        return res;
    }
}
