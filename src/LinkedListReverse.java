/**
 * Created by liyao on 5/23/17.
 */

public class LinkedListReverse {
    private static ListNode iterativeMethod(ListNode root) {
        if (root == null) {
            return root;
        }

        ListNode preNode = null;
        ListNode curNode = root;

        while (curNode != null) {
            ListNode tmpNode = curNode.next;
            curNode.next = preNode;
            preNode = curNode;
            curNode = tmpNode;
        }

        return preNode;
    }

    private static ListNode recursiveMethod(ListNode root) {
        if (root == null || root.next == null) {
            return root;
        }

        ListNode nextNode = root.next;

        ListNode newHeadNode = recursiveMethod(root.next);
        nextNode.next = root;
        root.next = null;

        return newHeadNode;
    }

    private static void printElements(ListNode root) {
        while (root != null) {
            System.out.println("num: " + root.val);

            root = root.next;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 5, 3, 2, 6};
        ListNode prevNode = null;

        for (int i = nums.length - 1; i >= 0; i--) {
            ListNode curNode = new ListNode(nums[i]);
            curNode.next = prevNode;
            prevNode = curNode;
        }

        System.out.println("before reverse: ");
        printElements(prevNode);

        ListNode rootRecursive = recursiveMethod(prevNode);

        System.out.println("after reverse (recursive): ");
        printElements(rootRecursive);

        prevNode = rootRecursive;

        ListNode rootIterative = iterativeMethod(prevNode);

        System.out.println("after reverse (iterative): ");
        printElements(rootIterative);
    }
}
