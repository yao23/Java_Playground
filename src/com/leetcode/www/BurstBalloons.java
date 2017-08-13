package com.leetcode.www;

import java.util.ArrayDeque;
import java.util.Deque;

public class BurstBalloons {
    public int maxCoins(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        } else if (len == 1) {
            return nums[0];
        } else {
            int sum = 0;
            int up = 0;
            int down = 0;
            Deque<Integer> stack = new ArrayDeque<>();
            stack.push(nums[0]);
            for (int i = 1; i < len; i++) {
                int cur = nums[i], pre = stack.peek();
                if (cur < pre) { // go down
                    if (i == 1) {
                        stack.push(cur);
                        down++;
                    } else {
                        if (i + 1 < len) {
                            int next = nums[i + 1];
                            if (cur < next) { // cur < pre && cur < next
                                sum += (pre * cur * next);
                            } else { // cur < pre && cur > next
                                stack.push(cur);
                                down++;
                            }
                        } else { // last element
                            if (stack.isEmpty()) {
                                sum += cur;
                            } else {
                                int top = stack.pop();
                                if (stack.isEmpty()) {
                                    sum += (top * cur);
                                    if (top < cur) {
                                        stack.push(cur);
                                    } else {
                                        stack.push(top);
                                    }
                                } else {
                                    if (stack.peek() > top && top < cur) {
                                        sum += (stack.peek() * top * cur);
                                        stack.push(cur);
                                    } else {

                                    }
                                }
                            }
                        }
                    }
                } else { // cur > pre, go up
                    if (i == 1) {
                        stack.push(cur);
                        up++;
                    } else {
                        if (i + 1 < len) {
                            int next = nums[i + 1];
                            if (cur < next) { // cur < pre && cur < next
                                sum += (pre * cur * next);
                            } else { // cur < pre && cur > next
                                stack.push(cur);
                                down++;
                            }
                        } else { // last element
                            stack.push(cur);
                            down++;
                        }
                    }
                }
            }

            return sum;
        }
    }
}
