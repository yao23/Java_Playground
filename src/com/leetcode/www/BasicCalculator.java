package com.leetcode.www;

import java.util.Deque;
import java.util.LinkedList;

public class BasicCalculator { // LC 224
    public int calculate(String s) {
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
