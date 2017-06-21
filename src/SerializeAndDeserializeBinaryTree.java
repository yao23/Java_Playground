/**
 * Created by liyao on 6/20/17.
 */

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class SerializeAndDeserializeBinaryTree { // class Codec in LeetCode
    // Encodes a tree to a single string.
    public String serializeV0(TreeNode root) {
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

    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        } else {
            StringBuilder data = new StringBuilder();
            Map<Integer,TreeNode> map = new HashMap<>();
            int height = buildMap(root, 0, map); System.out.println("height: " + height);
            int numNode = getNumNode(height); System.out.println("numNode: " + numNode);

            for (int i = 0; i < numNode; i++) {
                if (map.containsKey(i)) {
                    data.append(map.get(i).val);
                } else {
                    data.append("null");
                }
                data.append(",");
            }

            return data.toString().substring(0, data.length() - 1); // remove last comma
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.equals("")) {
            return null;
        } else {
            String[] strs = data.split(",");

            return buildTree(0, strs);
        }
    }

    private TreeNode getNode(String data) {
        if (data.equals("null")) {
            return null;
        } else {
            return new TreeNode(Integer.valueOf(data));
        }
    }

    private TreeNode buildTree(int index, String[] strs) {
        if (index >= strs.length) {
            return null;
        } else {
            TreeNode cur = getNode(strs[index]);
            if (cur == null) {
                return cur;
            } else {
                TreeNode left = buildTree(2 * index + 1, strs);
                TreeNode right = buildTree(2 * index + 2, strs);
                cur.left = left;
                cur.right = right;
                return cur;
            }
        }
    }

    private int buildMap(TreeNode root, int index, Map<Integer, TreeNode> map) {
        int left, right;
        map.put(index, root);
        if (root.left != null) {
            left = buildMap(root.left, 2 * index + 1, map);
        } else {
            left = 0;
        }
        if (root.right != null) {
            right = buildMap(root.right, 2 * index + 2, map);
        } else {
            right = 0;
        }
        return 1 + Math.max(left, right);
    }

    private int getNumNode(int height) {
        int num = 0;
        for (int i = 0; i < height; i++) {
            num += ((int)Math.pow(2, i));
        }
        return num;
    }

    // Your Codec object will be instantiated and called as such:
    // Codec codec = new Codec();
    // codec.deserialize(codec.serialize(root));

    // [] => []
    // [1] => [1]
    // [1,2,3,null,4,5,null] => [1,2,3,null,4,5,null]
    // [1,2,3,null,null,4,5] => [1,2,3,null,null,4,5]
    // [1,2,3,4,5,6,7] => [1,2,3,4,5,6,7]
    // [5,2,3,null,null,2,4,3,1] => [5,2,3,null,null,2,4,3,1] ([5,2,3,null,null,2,4])


}
