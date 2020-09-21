package com.leetcode.www;

import java.util.ArrayList;
import java.util.List;

public class TwentyFourGame { // LC 679 [Google]
    /**
     * Runtime: 9 ms, faster than 76.91% of Java online submissions for 24 Game.
     * Memory Usage: 35 MB, less than 99.92% of Java online submissions for 24 Game.
     *
     * https://leetcode.com/problems/24-game/discuss/276292/Java-Detailed-explanation-with-examples
     *
     * @param nums
     * @return
     */
    public boolean judgePoint24(int[] nums) {
        List<Double> inputList = new ArrayList<>();
        for (int v: nums) {
            inputList.add((double) v);
        }
        return solve(inputList);
    }

    private boolean solve(List<Double> nums) {
        int numLength = nums.size();
        if (numLength == 0) {
            return false;
        }
        if (numLength == 1) {
            return Math.abs(nums.get(0) - 24) < 1e-6;
        }

        // choose two numbers to remove from nums list (order matters)
        for (int i = 0; i < numLength; i++) {
            for (int j = 0; j < numLength; j++) {
                if (i != j) {
                    List<Double> restNums = new ArrayList<>();
                    // add the rest two numbers other than nums[i] and nums[j]
                    for (int k = 0; k < numLength; k++) {
                        if (k != i && k!= j)
                            restNums.add(nums.get(k));
                    }
                    for (int operatorIndex = 0; operatorIndex < 4; operatorIndex++) {
                        if (operatorIndex < 2 && j > i) continue; // avoid duplicate sum or multiplication (calculate when j < i)
                        double a = nums.get(i), b = nums.get(j);
                        switch (operatorIndex) {
                            case 0: {
                                restNums.add(a + b);
                                break;
                            }
                            case 1: {
                                restNums.add(a * b);
                                break;
                            }
                            case 2: {
                                restNums.add(a - b);
                                break;
                            }
                            case 3: {
                                if (b != 0) {
                                    restNums.add(a / b);
                                    break;
                                }
                            }
                            default: continue;
                        }
                        // recursively solve on smaller list of nums
                        if (solve(restNums)) {
                            return true;
                        }
                        restNums.remove(restNums.size() - 1);
                    }
                }
            }
        }
        return false;
    }

//    ex1: [4,1,8,7]  (8-4) * (7-1) = 24
//
//            0  1  2  3
//           [4, 1, 8, 7]    i=3, j=1, k=-
//           [1, 7, 4]       i=1, j=0, k=-
//           [4, 6]          i=0, j=1, k=*
//           [24]            --> true
//
//    ex2: [1,5,5,5]  (5 - 1/5) * 5 = 24
//
//            0      1  2  3
//           [1,     5, 5, 5]    i=0,j=3, k=/
//           [1/5,   5, 5]       i=1, j=0, k=-
//           [5-1/5, 5]          i=0, j=1, k=*
//           [24]                --> true

    /**
     * Runtime: 2 ms, faster than 95.14% of Java online submissions for 24 Game.
     * Memory Usage: 40 MB, less than 36.10% of Java online submissions for 24 Game.
     *
     * DFS
     *
     * https://leetcode.com/problems/24-game/discuss/262558/Java-recursive-easy-to-read
     *
     * @param nums
     * @return
     */
    public boolean judgePoint24V0(int[] nums) {
        double[] doubles = new double[4];
        for (int i = 0; i < 4; i++) {
            doubles[i] = nums[i] / 1.0;
        }
        return dfs(doubles);
    }

    public boolean dfs(double[] nums) {
        int n = nums.length;
        if (n == 1 && Math.abs(nums[0] - 24.0) < 1e-6) {
            return true;
        }
        double[] restNum = new double[n - 1];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = 0, l = 0; k < n; k++) {
                    if (k != i && k != j) {
                        restNum[l] = nums[k];
                        l++;
                    }
                }
                double a = nums[i], b = nums[j];
                restNum[n - 2] = a + b; if (dfs(restNum)) return true;
                restNum[n - 2] = a * b; if (dfs(restNum)) return true;
                restNum[n - 2] = a - b; if (dfs(restNum)) return true;
                restNum[n - 2] = a / b; if (dfs(restNum)) return true;
                restNum[n - 2] = b - a; if (dfs(restNum)) return true;
                restNum[n - 2] = b / a; if (dfs(restNum)) return true;
            }
        }
        return false;
    }
}
