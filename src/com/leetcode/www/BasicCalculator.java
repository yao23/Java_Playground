package com.leetcode.www;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class BasicCalculator { // LC 224
    public int calculate(String s) { // beats 13.07%
        return evaluatePostfix(infixToPostfix(s));
    }

    private List<Object> infixToPostfix(String s) {
        Deque<Character> operators = new ArrayDeque<>();
        List<Object> postfix = new LinkedList<>();

        int numberBuffer = 0;
        boolean bufferingOperand = false;
        for (char c : s.toCharArray()) {
            if (c >= '0' && c <= '9') {
                numberBuffer = numberBuffer * 10 + c - '0';
                bufferingOperand = true;
            } else {
                if(bufferingOperand) {
                    postfix.add(numberBuffer);
                }
                numberBuffer = 0;
                bufferingOperand = false;

                if (c == ' '|| c == '\t') {
                    continue;
                }

                if (c == '(') {
                    operators.push('(');
                } else if (c == ')') {
                    while (operators.peek() != '(') {
                        postfix.add(operators.pop());
                    }
                    operators.pop(); // popping "("
                } else { // operator
                    while (!operators.isEmpty() && rank(c) <= rank(operators.peek())) {
                        postfix.add(operators.pop());
                    }
                    operators.push(c);
                }
            }

        }
        if (bufferingOperand) {
            postfix.add(numberBuffer);
        }

        while (!operators.isEmpty()) {
            postfix.add(operators.pop());
        }

        return postfix;
    }

    private int rank(char op) {
        // the bigger the number, the higher the rank
        switch(op){
            case '+':
                return 1;
            case '-':
                return 1;
            case '*':
                return 2;
            case '/':
                return 2;
            default:
                return 0; // '('
        }
    }

    private int evaluatePostfix(List<Object> postfix) {
        Deque<Integer> operands = new ArrayDeque<>();
        int a = 0, b = 0;
        for (Object s : postfix) {
            if (s instanceof Character) {
                char c = (Character) s;
                b = operands.pop();
                a = operands.pop();
                switch (c) {
                    case '+':
                        operands.push(a + b);
                        break;
                    case '-':
                        operands.push(a - b);
                        break;
                    case '*':
                        operands.push(a * b);
                        break;
                    default :
                        operands.push(a / b);
                }
            } else { // instanceof Integer
                operands.push((Integer)s);
            }
        }
        return operands.pop();
    }

    public int calculateV0(String s) { // not working for test case 4 (1st '+' and NullPointerException due to 2nd poll() for valStack)
        Deque<Integer> valStack = new LinkedList<>(); // numbers
        Deque<Character> opStack = new LinkedList<>(); // operators
        char[] tokens = s.toCharArray();

        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i] == '(') {
                opStack.offerLast(tokens[i]);
            } else if (tokens[i] == ')') {
                while (opStack.peekLast() != '(') {
                    valStack.offerLast(cal(opStack.pollLast(), valStack.pollLast(), valStack.pollLast()));
                }
                opStack.pollLast(); // poll '('
            } else if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/') {
                while (!opStack.isEmpty() && !isHigher(tokens[i], opStack.peekLast())) {
                    valStack.offerLast(cal(opStack.pollLast(), valStack.pollLast(), valStack.pollLast()));
                }
                opStack.offerLast(tokens[i]);
            } else if (tokens[i] != ' ') {
                valStack.offerLast(Integer.parseInt("" + tokens[i]));
            }
        }

        while (!opStack.isEmpty()) {
            valStack.offerLast(cal(opStack.pollLast(), valStack.pollLast(), valStack.pollLast()));
        }

        return valStack.pollLast();
    }

    private int cal(char operator, int operand1, int operand2) {
        if (operator == '+') {
            return (operand1 + operand2);
        } else if (operator == '-') {
            return (operand2 - operand1);
        } else if (operator == '*') {
            return (operand1 * operand2);
        } else {
            return (operand2 / operand1);
        }
    }

    private boolean isHigher(char cur, char toPeek) {
        return (toPeek == '+' || toPeek == '-') && (cur == '*' || cur == '/');
    }
}

// "0" => 0
// "1 + 1" => 2
// " 2-1 + 2 " => 3
// "(1+(4+5+2)-3)+(6+8)" => 23