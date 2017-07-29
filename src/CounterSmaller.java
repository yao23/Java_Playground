import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CounterSmaller {
    public List<Integer> countSmaller(int[] nums) {
        Integer[] res = new Integer[nums.length];

        mergeSort(0, nums.length - 1, nums, res);

        return Arrays.asList(res);
    }

    private List<Integer> mergeSort(int start, int end, int[] nums, Integer[] res) {
        List<Integer> resList = new ArrayList<>();
        if (start == end) {
            resList.add(nums[start]);
            return resList;
        } else {
            int mid = start + (end - start) / 2;
            List<Integer> left = mergeSort(start, mid, nums, res);
            List<Integer> right = mergeSort(mid + 1, end, nums, res);
            return merge(left, right, start, mid + 1, res);
        }
    }

    private List<Integer> merge(List<Integer> left, List<Integer> right, int leftStart, int rightStart, Integer[] res) {
        List<Integer> resList = new ArrayList<>();
        for (int i = 0; i < left.size() + right.size(); i++) {
            if (leftStart >= left.size()) {
                resList.add(right.get(rightStart));
                rightStart++;
            } else if (rightStart >= right.size()) {
                resList.add(left.get(leftStart));
                leftStart++;
            } else if (left.get(leftStart) <= right.get(rightStart)) {
                resList.add(left.get(leftStart));
                leftStart++;
            } else {
                res[leftStart] += 1;
                resList.add(right.get(rightStart));
                rightStart++;
            }
        }
        return resList;
    }
}

// [5,2,6,1] => [2, 1, 1, 0]
// [] => []