package com.leetcode.www;

import java.util.Arrays;
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
}
