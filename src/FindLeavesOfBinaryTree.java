import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class FindLeavesOfBinaryTree { // LC 366
    public List<List<Integer>> findLeaves(TreeNode root) { // beats 18.98%
        List<List<Integer>> res = new ArrayList<>();
        height(root, res);
        return res;
    }

    private int height(TreeNode node, List<List<Integer>> res){
        if (null == node) {
            return -1;
        }
        int level = 1 + Math.max(height(node.left, res), height(node.right, res));
        if (res.size() < level + 1) {
            res.add(new ArrayList<>());
        }
        res.get(level).add(node.val);
        return level;
    }

    public List<List<Integer>> findLeavesV0(TreeNode root) {
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
                    cur.remove(k); // Line 93: java.lang.IndexOutOfBoundsException: Index: 8, Size: 7 (test case 2)
                }
            }
        }
        list.remove(len - 1);

        return res;
    }
}

// [1,2,3,4,5] => [[4, 5, 3], [2], [1]]
// [-64,12,18,-4,-53,null,76,null,-51,null,null,-93,3,null,-31,47,null,3,53,-81,33,4,null,-51,-44,-60,11,null,null,null,null,78,null,-35,-64,26,-81,-31,27,60,74,null,null,8,-38,47,12,-24,null,-59,-49,-11,-51,67,null,null,null,null,null,null,null,-67,null,-37,-19,10,-55,72,null,null,null,-70,17,-4,null,null,null,null,null,null,null,3,80,44,-88,-91,null,48,-90,-30,null,null,90,-34,37,null,null,73,-38,-31,-85,-31,-96,null,null,-18,67,34,72,null,-17,-77,null,56,-65,-88,-53,null,null,null,-33,86,null,81,-42,null,null,98,-40,70,-26,24,null,null,null,null,92,72,-27,null,null,null,null,null,null,-67,null,null,null,null,null,null,null,-54,-66,-36,null,-72,null,null,43,null,null,null,-92,-1,-98,null,null,null,null,null,null,null,39,-84,null,null,null,null,null,null,null,null,null,null,null,null,null,-93,null,null,null,98]