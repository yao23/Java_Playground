package com.leetcode.www;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class MiniParser { // LC 385
    /**
     * If encounters '[', push current NestedInteger to stack and start a new one.
     * If encounters ']', end current NestedInteger and pop a NestedInteger from stack to continue.
     * If encounters ',', append a new number to curr NestedInteger, if this comma is not right after a brackets.
     * Update index l and r, where l shall point to the start of a integer substring, while r shall points to the end+1 of substring.
     *
     * @param s
     * @return
     */
    public NestedInteger deserialize(String s) { // beats 53.05%
        if (s.isEmpty()) {
            return null;
        }
        if (s.charAt(0) != '[') { // ERROR: special case
            return new NestedInteger(Integer.valueOf(s));
        }

        Deque<NestedInteger> stack = new ArrayDeque<>();
        NestedInteger curr = null;
        int l = 0; // l shall point to the start of a number substring;
        // r shall point to the end + 1 of a number substring
        for (int r = 0; r < s.length(); r++) {
            char ch = s.charAt(r);
            if (ch == '[') {
                if (curr != null) {
                    stack.push(curr);
                }
                curr = new NestedInteger();
                l = r + 1;
            } else if (ch == ']') {
                String num = s.substring(l, r);
                if (!num.isEmpty()) {
                    curr.add(new NestedInteger(Integer.valueOf(num)));
                }
                if (!stack.isEmpty()) {
                    NestedInteger pop = stack.pop();
                    pop.add(curr);
                    curr = pop;
                }
                l = r + 1;
            } else if (ch == ',') {
                if (s.charAt(r - 1) != ']') {
                    String num = s.substring(l, r);
                    curr.add(new NestedInteger(Integer.valueOf(num)));
                }
                l = r + 1;
            }
        }

        return curr;
    }

    class NestedInteger {
        private List<NestedInteger> list;
        private Integer integer;

        public NestedInteger(List<NestedInteger> list){
            this.list = list;
        }

        public void add(NestedInteger nestedInteger) {
            if(this.list != null){
                this.list.add(nestedInteger);
            } else {
                this.list = new ArrayList<>();
                this.list.add(nestedInteger);
            }
        }

        public void setInteger(int num) {
            this.integer = num;
        }

        public NestedInteger(Integer integer){
            this.integer = integer;
        }

        public NestedInteger() {
            this.list = new ArrayList<>();
        }

        public boolean isInteger() {
            return integer != null;
        }

        public Integer getInteger() {
            return integer;
        }

        public List<NestedInteger> getList() {
            return list;
        }

        public String printNi(NestedInteger thisNi, StringBuilder sb){
            if(thisNi.isInteger()) {
                sb.append(thisNi.integer);
                sb.append(",");
            }
            sb.append("[");
            for(NestedInteger ni : thisNi.list){
                if(ni.isInteger()) {
                    sb.append(ni.integer);
                    sb.append(",");
                }
                else {
                    printNi(ni, sb);
                }
            }
            sb.append("]");
            return sb.toString();
        }
    }
}


