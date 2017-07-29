public class SegmentedTree {
    private SegmentedTreeNode build(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        SegmentedTreeNode root = new SegmentedTreeNode(start, end);
        if (start == end) {
            root.sum = nums[end];
        } else {
            int mid = start + (end - start) / 2;
            root.left = build(nums, start, mid);
            root.right = build(nums, mid + 1, end);
            root.sum = root.left.sum + root.right.sum;
        }

        return root;
    }

    private void update(SegmentedTreeNode root, int index, int newVal) {
        if (root.start == root.end) {
            root.sum = newVal;
            return;
        }
        int mid = root.start + (root.end - root.start) / 2;
        if (mid >= index) {
            update(root.left, index, newVal);
        } else {
            update(root.right, index, newVal);
        }
        root.sum = root.left.sum + root.right.sum;
    }

    private int sumRange(SegmentedTreeNode root, int start, int end) {
        if (root.start == start && root.end == end) {
            return root.sum;
        }
        int mid = root.start + (root.end - root.start) / 2;
        if (mid >= end) {
            return sumRange(root.left, start, end);
        } else if (mid < start) {
            return sumRange(root.right, start, end);
        } else {
            return sumRange(root.left, start, mid) + sumRange(root.right, mid + 1, end);
        }
    }

    class SegmentedTreeNode {
        int start;
        int end;
        SegmentedTreeNode left;
        SegmentedTreeNode right;
        int sum;

        public SegmentedTreeNode (int start, int end) {
            this.start = start;
            this.end = end;
            this.left = null;
            this.right = null;
            this.sum = 0;
        }
    }
}
