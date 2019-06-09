package com.leetcode.www;

import java.util.LinkedList;

/**
 * Created by liyao on 6/22/17.
 */
public class SerializeAndDeserializeBST { // LC 449
    /**
     * Runtime: 10 ms, faster than 31.91% of Java online submissions for Serialize and Deserialize BST.
     * Memory Usage: 39.6 MB, less than 73.73% of Java online submissions for Serialize and Deserialize BST.
     *
     * @param root
     * @return
     */
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        } else {
            StringBuilder data = new StringBuilder();
            LinkedList<TreeNode> queue = new LinkedList<>();
            queue.add(root);

            while (!queue.isEmpty()) {
                TreeNode cur = queue.poll();
                if (cur != null) {
                    data.append(cur.val);
                    data.append(",");
                    queue.add(cur.left);
                    queue.add(cur.right);
                } else {
                    data.append("#,");
                }
            }

            data.deleteCharAt(data.length() - 1); // remove last comma

            return data.toString();
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.equals("")) {
            return null;
        } else {
            String[] strs = data.split(",");

            return buildTree(strs);
        }
    }

    private TreeNode buildTree(String[] strs) {
        TreeNode root = getNode(strs[0]);
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int i = 1;
        while (!queue.isEmpty()) {
            TreeNode t = queue.poll();
            if (t == null) {
                continue;
            } else {
                t.left = getNode(strs[i]);
                queue.offer(t.left);
                i++;

                t.right = getNode(strs[i]);
                queue.offer(t.right);
                i++;
            }
        }

        return root;
    }

    private TreeNode getNode(String data) {
        if (data.equals("#")) {
            return null;
        } else {
            return new TreeNode(Integer.valueOf(data));
        }
    }

    // [] => []
    // [1] => [1]
    // [2,1,3] => [2,1,3]

    // beats 52.89%
}
