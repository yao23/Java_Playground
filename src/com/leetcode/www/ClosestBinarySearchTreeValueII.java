import java.util.*;
import com.leetcode.www.TreeNode;

public class ClosestBinarySearchTreeValueII { // LC 272
    /**
     * https://kennyzhuang.gitbooks.io/leetcode-lock/content/270_closest_binary_search_tree_value_ii.html
     *
     * @param root
     * @param target
     * @param k
     * @return
     */
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        LinkedList<Integer> list = new LinkedList<>();
        closestKValuesHelper(list, root, target, k);
        return list;
    }

    /**
     * @return <code>true</code> if result is already found.
     */
    private boolean closestKValuesHelper(LinkedList<Integer> list, TreeNode root, double target, int k) {
        if (root == null) {
            return false;
        }

        if (closestKValuesHelper(list, root.left, target, k)) {
            return true;
        }

        if (list.size() == k) {
            if (Math.abs(list.getFirst() - target) < Math.abs(root.val - target)) {
                return true;
            } else {
                list.removeFirst();
            }
        }

        list.addLast(root.val);
        return closestKValuesHelper(list, root.right, target, k);
    }

    public List<Integer> closestKValuesV1(TreeNode root, double target, int k) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> pred = new Stack<>(), succ = new Stack<>();
        initStack(pred, succ, root, target);
        while (k-- > 0) {
            if (succ.isEmpty() || !pred.isEmpty() && target - pred.peek().val < succ.peek().val - target) {
                list.add(pred.peek().val);
                getPredecessor(pred);
            } else { //Since N > k, always have something to add
                list.add(succ.peek().val);
                getSuccessor(succ);
            }
        }
        return list;
    }

    private void initStack(Stack<TreeNode> pred, Stack<TreeNode> succ, TreeNode root, double target) {
        while (root != null) {
            if (root.val <= target) {
                pred.push(root);
                root = root.right;
            } else {
                succ.push(root);
                root = root.left;
            }
        }
    }
    private void getPredecessor(Stack<TreeNode> st) {
        TreeNode node = st.pop();
        if (node.left != null) {
            st.push(node.left);
            while (st.peek().right != null)  {
                st.push(st.peek().right);
            }
        }
    }
    private void getSuccessor(Stack<TreeNode> st) {
        TreeNode node = st.pop();
        if (node.right != null) {
            st.push(node.right);
            while (st.peek().left != null) {
                st.push(st.peek().left);
            }
        }
    }

    public class Solution {
        public List<Integer> closestKValues(TreeNode root, double target, int k) {
            List<Integer> result = new ArrayList<>();
            if (root == null) {
                return result;
            }

            Stack<Integer> precedessor = new Stack<>();
            Stack<Integer> successor = new Stack<>();

            getPredecessor(root, target, precedessor);
            getSuccessor(root, target, successor);

            for (int i = 0; i < k; i++) {
                if (precedessor.isEmpty()) {
                    result.add(successor.pop());
                } else if (successor.isEmpty()) {
                    result.add(precedessor.pop());
                } else if (Math.abs((double) precedessor.peek() - target) < Math.abs((double) successor.peek() - target)) {
                    result.add(precedessor.pop());
                } else {
                    result.add(successor.pop());
                }
            }

            return result;
        }

        private void getPredecessor(TreeNode root, double target, Stack<Integer> precedessor) {
            if (root == null) {
                return;
            }
            getPredecessor(root.left, target, precedessor);
            if (root.val > target) {
                return;
            }
            precedessor.push(root.val);
            getPredecessor(root.right, target, precedessor);
        }

        private void getSuccessor(TreeNode root, double target, Stack<Integer> successor) {
            if (root == null) {
                return;
            }
            getSuccessor(root.right, target, successor);
            if (root.val <= target) {
                return;
            }
            successor.push(root.val);
            getSuccessor(root.left, target, successor);
        }
    }
}
