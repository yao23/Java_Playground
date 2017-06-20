/**
 * Created by liyao on 6/20/17.
 */

import java.util.ArrayDeque;
import java.util.Deque;

public class SerializeAndDeserializeBinaryTree { // class Codec in LeetCode
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        } else {
            StringBuilder data = new StringBuilder();
            Deque<TreeNode> queue = new ArrayDeque<>();
            queue.add(root);
            int curLevel = 1;
            StringBuilder curLevelStr = new StringBuilder(root.val + ",");

            while (!queue.isEmpty()) {
                data.append((curLevelStr.toString()));
                curLevelStr = new StringBuilder();
                int nextLevel = 0;

                for (int i = 0; i < curLevel; i++) {
                    TreeNode cur = queue.pop();

                    if (cur.left == null) {
                        curLevelStr.append("null,");
                    } else {
                        curLevelStr.append(cur.left.val);
                        curLevelStr.append(",");
                        queue.add(cur.left);
                        nextLevel++;
                    }

                    if (cur.right == null) {
                        curLevelStr.append("null,");
                    } else {
                        curLevelStr.append(cur.right.val);
                        curLevelStr.append(",");
                        queue.add(cur.right);
                        nextLevel++;
                    }
                }

                curLevel = nextLevel; // update for next level nodes iteration
            }

            return data.toString().substring(0, data.length() - 1); // remove last comma
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.equals("")) {
            return null;
        } else {
            // parent (i), left (2x -1), right (2x)
            String[] strs = data.split(",");
            TreeNode[] nodes = new TreeNode[strs.length];

            for (int i = strs.length - 1; i >= 0; i--) { // one pass, from begin to end with two passes
                if (strs[i].equals("null")) {
                    nodes[i] = null;
                } else {
                    nodes[i] = new TreeNode(Integer.valueOf(strs[i]));
                    int left = 2 * i - 1, right = 2 * i;
                    if (left < strs.length) {
                        addChild(nodes[i], nodes[left], 0);
                    }
                    if (right < strs.length) {
                        addChild(nodes[i], nodes[right], 1);
                    }
                }
            }

            return nodes[0];
        }
    }

    private int power(int n) {
        int result = 1, x = 0;

        while (x < n) {
            result *= 2;
            x++;
        }

        return result;
    }

    private void addChild(TreeNode parent, TreeNode child, int mode) {
        if (mode == 0) { // left
            parent.left = child;
        } else { // right
            parent.right = child;
        }
    }

    // Your Codec object will be instantiated and called as such:
    // Codec codec = new Codec();
    // codec.deserialize(codec.serialize(root));
}
