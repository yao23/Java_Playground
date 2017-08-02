import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class FindLeavesOfBinaryTree { // LC 366
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        List<List<Integer>> list = levelTraversal(root);
        res = getLeaves(list);
        return res;
    }

    private List<List<Integer>> levelTraversal(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Deque<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        int cur = 1;
        while (!q.isEmpty()) {
            int next = 0;
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < cur; i++) {
                TreeNode node = q.remove();
                list.add(node.val);
                if (node.left != null) {
                    next++;
                    q.offer(node.left);
                }
                if (node.right != null) {
                    next++;
                    q.offer(node.right);
                }
            }
            cur = next;
            res.add(list);
        }
        return res;
    }

    private List<List<Integer>> getLeaves(List<List<Integer>> list) {
        int len = list.size();
        if (len == 1) {
            return list;
        } else {
            List<List<Integer>> res = new ArrayList<>();

            while (list.size() > 1) {
                res.add(getCurLevel(list));
            }

            List<Integer> firstLevel = new ArrayList<>();
            firstLevel.add(list.get(0).get(0));
            res.add(firstLevel);
            return res;
        }
    }

    private List<Integer> getCurLevel(List<List<Integer>> list) {
        List<Integer> res = new ArrayList<>();
        int len = list.size();
        res.addAll(list.get(len - 1));
        int removedOffset = 0;
        for (int i = len - 1; i > 0; i--) {
            List<Integer> cur = list.get(i);
            List<Integer> pre = list.get(i - 1);
            int curLen = cur.size(), preLen = pre.size();
            // add leaf nodes in pre upper level
            if (curLen < preLen * 2) {
                int start;
                if (curLen % 2 == 0) { // even
                    start = curLen / 2;
                } else { // odd
                    start = curLen / 2 + 1;
                }
                for (int j = start; j < preLen; j++) {
                    res.add(pre.get(j));
                }
                removedOffset = start;
            }
            // remove leaf nodes
            if (i == len - 1) {
                cur.clear();
            } else {
                for (int k = removedOffset; k < curLen; k++) {
                    cur.remove(k);
                }
            }
        }
        list.remove(len - 1);

        return res;
    }
}
