package com.leetcode.www;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CountOfSmallerNumbersAfterSelf { // LC 315 [Google]
    /**
     * 1. Construct Binary Search Tree
     * 2. Calculate smaller numbers while constructing
     *
     * Binary Search Tree is ordered, have smaller in left and larger in right
     *
     *
     * [5,2,6,1] => [2, 1, 1, 0]
     * // [] => []
     // [5,7,2,2,6,1] => [3,4,1,1,1,0]

     // beats 30.43%
     *
     * @param nums
     * @return
     */
    public List<Integer> countSmaller(int[] nums) { // beats 77.50%
        Integer[] res = new Integer[nums.length];
        if (nums == null || nums.length == 0) {
            return Arrays.asList(res);
        }

        SmallerTreeNode root = null;

        // guarantee right nodes are in the tree (decide root, traverse right elements right to check smaller or not)
        for (int i = nums.length - 1; i >= 0; i--) {
            root = insert(root, nums[i], res, i, 0);
        }

        return Arrays.asList(res);
    }

    private SmallerTreeNode insert(SmallerTreeNode root, int val, Integer[] res, int index, int curLeftSum) {
        if (root == null) {
            res[index] = curLeftSum;
            return new SmallerTreeNode(val, 0);
        }

        if (root.val > val) { // insert in left (left turn), root.leftCount++, pass curLeftSum down
            root.leftCount++;
            root.left = insert(root.left, val, res, index, curLeftSum);
        } else { // insert in right (right turn), pass curLeftSum + root.leftCount down + 0/1 (test case 3, 2 == 2)
            root.right = insert(root.right, val, res, index, root.leftCount + curLeftSum + (root.val == val ? 0 : 1));
        }

        return root;
    }

    class SmallerTreeNode {
        int val;
        int leftCount;
        SmallerTreeNode left;
        SmallerTreeNode right;

        public SmallerTreeNode(int val, int leftCount) {
            this.val = val;
            this.leftCount = leftCount;
        }
    }

    /**
     * Count smaller numbers while merge
     *
     * @param nums
     * @return
     */
    public List<Integer> countSmallerV0(int[] nums) {
        Integer[] res = new Integer[nums.length];
        if (nums == null || nums.length == 0) {
            return Arrays.asList(res);
        }

        List<Elem> resList = mergeSort(0, nums.length - 1, nums);
        for (int i = 0; i < resList.size(); i++) {
            Elem elem = resList.get(i);
            res[elem.index] = elem.counter;
        }

        return Arrays.asList(res);
    }

    private List<Elem> mergeSort(int start, int end, int[] nums) {
        List<Elem> resList = new ArrayList<>();
        if (start == end) {
            Elem cur = new Elem(start, nums[start], 0);
            resList.add(cur);
            return resList;
        } else {
            int mid = start + (end - start) / 2;
            List<Elem> left = mergeSort(start, mid, nums);
            List<Elem> right = mergeSort(mid + 1, end, nums);
            return merge(left, right);
        }
    }

    private List<Elem> merge(List<Elem> left, List<Elem> right) {
        int leftCur = 0;
        int rightCur = 0;
        int rightSmallerCounter = 0; // accumulate num of smaller ones in right part

        List<Elem> resList = new ArrayList<>();
        for (int i = 0; i < left.size() + right.size(); i++) {
            if (leftCur >= left.size()) {
                resList.add(right.get(rightCur));
                rightCur++;
            } else if (rightCur >= right.size()) {
                left.get(leftCur).counter += rightSmallerCounter;
                resList.add(left.get(leftCur));
                leftCur++;
            } else if (left.get(leftCur).val <= right.get(rightCur).val) {
                left.get(leftCur).counter += rightSmallerCounter;
                resList.add(left.get(leftCur));
                leftCur++;
            } else {
                rightSmallerCounter++;
                resList.add(right.get(rightCur));
                rightCur++;
            }
        }
        return resList;
    }

    class Elem {
        int index;
        int val;
        int counter;

        public Elem(int index, int val, int counter) {
            this.index = index;
            this.val = val;
            this.counter = counter; // num of smaller ones after self
        }
    }
}
