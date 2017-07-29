import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CounterSmaller {
    public List<Integer> countSmaller(int[] nums) {
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

// [5,2,6,1] => [2, 1, 1, 0]
// [] => []

// beats 30.43%