package com.leetcode.www;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;

public class DesignSnakeGame { // LC 353
    /**
     * Runtime: 97 ms, faster than 98.30% of Java online submissions for Design Snake Game.
     * Memory Usage: 60.2 MB, less than 98.24% of Java online submissions for Design Snake Game.
     *
     * https://leetcode.com/problems/design-snake-game/discuss/294312/Java-straightforward-solution-beats-99
     */
    class SnakeGame {
        int width, height;
        int[] snake;
        LinkedList<int[]> snakePos;
        LinkedList<int[]> foodPos;

        /** Initialize your data structure here.
         @param width - screen width
         @param height - screen height
         @param food - A list of food positions
         E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
        public SnakeGame(int width, int height, int[][] food) {
            this.width = width;
            this.height = height;
            snake = new int[]{0, 0};
            snakePos = new LinkedList<>();
            snakePos.add(new int[]{0, 0});
            foodPos = new LinkedList<>(Arrays.asList(food));
        }

        /** Moves the snake.
         @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
         @return The game's score after the move. Return -1 if game over.
         Game over when snake crosses the screen boundary or bites its body. */
        public int move(String direction) {
            if (direction.equals("U")) snake[0] -= 1;
            if (direction.equals("L")) snake[1] -= 1;
            if (direction.equals("R")) snake[1] += 1;
            if (direction.equals("D")) snake[0] += 1;

            // check if the snake hits wall
            if (snake[0] < 0 || snake[0] > height - 1 || snake[1] < 0 || snake[1] > width - 1) return -1;
            // check if the snake hits itself
            for (int i = snakePos.size()-1; i > 0; i--) {
                int[] curr = snakePos.get(i);
                if (curr[0] == snake[0] && curr[1] == snake[1]) return -1;
            }
            // check if the snake hits food
            if (foodPos.size() != 0) {
                int[] food = foodPos.get(0);
                if (food[0] == snake[0] && food[1] == snake[1]) {
                    snakePos.add(foodPos.remove());
                    return snakePos.size() - 1;
                }
            }

            // move the snake position
            snakePos.remove();
            snakePos.add(new int[]{snake[0], snake[1]});

            return snakePos.size() - 1;
        }
    }

    /**
     * Your SnakeGame object will be instantiated and called as such:
     * SnakeGame obj = new SnakeGame(width, height, food);
     * int param_1 = obj.move(direction);
     */

    /**
     * Runtime: 102 ms, faster than 85.65% of Java online submissions for Design Snake Game.
     * Memory Usage: 61.4 MB, less than 91.19% of Java online submissions for Design Snake Game.
     *
     * https://leetcode.com/problems/design-snake-game/discuss/285569/Java-solution-with-linkedlist-and-hashset-faster-than-93
     */
    class SnakeGameV0 {
        class ListNode{
            int x;
            int y;
            ListNode next;

            public ListNode(int x, int y){
                this.x = x;
                this.y = y;
            }

            @Override
            public int hashCode(){
                return x * y + x - y;
            }

            @Override
            public boolean equals(Object obj){
                if (obj == null) {
                    return false;
                }
                if (obj == this) {
                    return true;
                }
                if (getClass() != obj.getClass()) {
                    return false;
                }
                if (hashCode() != obj.hashCode()) {
                    return false;
                }
                return ((ListNode)obj).x == x && ((ListNode)obj).y == y;
            }
        }

        int width, height;
        ListNode head;
        ListNode tail;
        HashSet<ListNode> snakeSet;
        ListNode foodHead;
        public SnakeGameV0(int width, int height, int[][] food) {
            this.width = width;
            this.height = height;
            head = new ListNode(0,0);
            tail = head;
            snakeSet = new HashSet<>();
            snakeSet.add(head);
            ListNode dummyFoodHead = new ListNode(-1,-1);
            ListNode cur = dummyFoodHead;
            for (int[] pos : food) {
                cur.next = new ListNode(pos[0], pos[1]);
                cur = cur.next;
            }
            foodHead = dummyFoodHead.next;
        }

        /** Moves the snake.
         @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
         @return The game's score after the move. Return -1 if game over.
         Game over when snake crosses the screen boundary or bites its body. */
        public int move(String direction) {
            int[] curPos = {head.x, head.y};
            int[] nextPos = null;

            switch(direction){
                case "U":
                    nextPos = new int[]{curPos[0] - 1, curPos[1]};
                    break;
                case "R":
                    nextPos = new int[]{curPos[0], curPos[1] + 1};
                    break;
                case "D":
                    nextPos = new int[]{curPos[0] + 1, curPos[1]};
                    break;
                case "L":
                    nextPos = new int[]{curPos[0], curPos[1] - 1};
                    break;
                default:
                    return -1;
            }

            // check if nextPos is wall
            if (nextPos[0] < 0 || nextPos[0] > height - 1 || nextPos[1] < 0 || nextPos[1] > width - 1) {
                return -1;
            }

            ListNode nextNode = new ListNode(nextPos[0], nextPos[1]);

            // check if is food
            if (foodHead != null && nextNode.equals(foodHead)) {
                foodHead = foodHead.next;
            } else {
                snakeSet.remove(tail);
                tail = tail.next;
            }

            // check if hit snake
            if (snakeSet.contains(nextNode)) {
                return -1;
            }

            snakeSet.add(nextNode);
            head.next = nextNode;
            head = nextNode;
            if (tail == null) {
                tail = head;
            }
            return snakeSet.size() - 1;
        }
    }
}
