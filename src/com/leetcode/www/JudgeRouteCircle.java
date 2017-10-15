package com.leetcode.www;

public class JudgeRouteCircle { // LC 657
    public boolean judgeCircle(String moves) { // beats 89.42%
        int x = 0;
        int y = 0;
        for (char ch : moves.toCharArray()) {
            if (ch == 'U') {
                y++;
            } else if (ch == 'D') {
                y--;
            } else if (ch == 'R') {
                x++;
            } else if (ch == 'L') {
                x--;
            }
        }
        return x == 0 && y == 0;
    }

    public boolean judgeCircleV0(String moves) { // beats 6.94%
        moves=" " + moves + " ";
        return moves.split("L").length == moves.split("R").length &&
                moves.split("U").length == moves.split("D").length;
    }
}
