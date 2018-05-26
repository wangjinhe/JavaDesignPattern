package com.yixiu.designpattern.behavior._8interpreter;

import java.util.*;

/**
 * 客户端测试程序
 */
class Client {
    public  static void main(String [] args) {
        Calcalator calcalator = new Calcalator("a+b-c+a");
        Context context = new Context();
        context.addVariable('a',10);
        context.addVariable('b',5);
        context.addVariable('c',3);
        calcalator.setContext(context);

        Integer value = calcalator.calcalate();
        System.out.println(calcalator.getExpression() + "=" + value);
    }
}




/**
 * 简单计算器
 * 注意：
 * (1)为简单演示解释器模式，只能输入一个字母的变量,表达式要连续，不能有除/+-*之外的字符(比如空格)
 * 输入样例 a+b-c+a
 */
public class Calcalator {
    private String expression; // 表达式
    private  Context context; // 上下文
    public  Calcalator(String expression) {
        this.expression = expression;
    }

    public String getExpression() {
        return this.expression;
    }


    public  void setContext(Context context) {
        this.context = context;
    }
    // 计算表达式，获取值
    public Integer calcalate() {
        char [] vars = expression.toCharArray();
        Stack<Expression> expressionStack = new Stack<Expression>(); // 表达式栈

        expressionStack.push(new VariableExpression( new Character(vars[0]))); // 第1个元素先入栈
        Expression left , right ;
        // 从运算符开始解析，每次解析两个
        for(int i = 1; i < vars.length; i += 2)
        {
            left = expressionStack.pop();
            right = new VariableExpression(new Character(vars[i+1]));

            switch (vars[i])
            {
                case '+' :
                    expressionStack.push(new PlusExpression(left,right));
                    break;
                case '-':
                    expressionStack.push(new SubExpression(left,right));
                    break;
                case '*':
                    expressionStack.push(new MulExpression(left,right));
                    break;
                case  '/':
                    expressionStack.push(new DivExpression(left,right));
                    break;
            }
        }
        Expression expression = expressionStack.pop();
        Integer value = expression.interpret(context);
        expressionStack.clear();
        return  value;
    }
}


/**
 * 上下文：保存变量和值
 */
class Context {
    // 保存变量和值
    private Map<Character,Integer> variable;
    public Context() {
        this.variable = new HashMap<Character, Integer>();
    }
    // 添加一个变量
    public boolean addVariable(Character key,Integer value) {
        if(variable.containsKey(key)) {
            return  false;
        } else {
            variable.put(key,value);
            return true;
        }
    }
    //返回一个值
    public  Integer getVariable(Character key) {
        if(variable.containsKey(key)) {
            return  variable.get(key);
        } else {
            return null;
        }
    }
}

/**
 * 抽象解释器
 */
abstract class Expression {
    // 解释接口
    public abstract Integer interpret(Context context);
}

/**
 * 变量解释器，获取一个变量的值
 */
class VariableExpression extends Expression {
    private Character character; // 保存一个变量

    public VariableExpression(Character character) {
        this.character = character;
    }

    // 解释变量
    @Override
    public Integer interpret(Context context) {
        return context.getVariable(this.character);
    }


}

/**
 * 操作符解释器
 * 注意：操作符解释器是一个抽象类，两个变量都是protected类型的，子类需要变量
 */
abstract class OperatorExpression extends  Expression {
    protected Expression left; // 左边的变量
    protected Expression right; // 右边的变量
    public OperatorExpression(Expression left,Expression right) {
        this.left = left;
        this.right = right;
    }


}

/**
 * 加法解释器
 */
class PlusExpression extends  OperatorExpression {
    public PlusExpression(Expression left,Expression right)
    {
        super(left, right);
    }
    @Override
    public Integer interpret(Context context) {
        System.out.println( String.format( "开始解析加法:left=%s,right=%s",left.getClass().getSimpleName(),right.getClass().getSimpleName()) );
        return this.left.interpret(context) + this.right.interpret(context);
    }
}

/**
 * 减法解释器
 */
class SubExpression extends  OperatorExpression {
    public SubExpression(Expression left,Expression right)
    {
        super(left, right);
    }
    @Override
    public Integer interpret(Context context) {
        System.out.println( String.format( "开始解析减法:left=%s,right=%s",left.getClass().getSimpleName(),right.getClass().getSimpleName()) );
        return this.left.interpret(context) - this.right.interpret(context);
    }
}


/**
 * 乘法解释器
 */
class MulExpression extends  OperatorExpression {
    public MulExpression(Expression left,Expression right)
    {
        super(left, right);
    }
    @Override
    public Integer interpret(Context context) {
        System.out.println( String.format( "开始解析乘法:left=%s,right=%s",left.getClass().getSimpleName(),right.getClass().getSimpleName()) );
        return this.left.interpret(context) * this.right.interpret(context);
    }
}


/**
 * 除法解释器
 */
class DivExpression extends  OperatorExpression {
    public DivExpression(Expression left,Expression right)
    {
        super(left, right);
    }
    @Override
    public Integer interpret(Context context) {
        System.out.println( String.format( "开始解析除法:left=%s,right=%s",left.getClass().getSimpleName(),right.getClass().getSimpleName()) );
        return this.left.interpret(context) / this.right.interpret(context);
    }
}

