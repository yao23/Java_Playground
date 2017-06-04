/**
 * Created by liyao on 6/3/17.
 */
public class RemoveDuplicatesFromSortedArray {
    public int removeDuplicates(int[] nums) {
        int numsLength = nums.length;

        if (numsLength <= 1) { // length is 0 or 1
            return numsLength;
        } else {
            int curAvailableIdx = 1; // current available unique index

            for (int curIdx = 1; curIdx < numsLength; curIdx++) {
                if (nums[curIdx - 1] == nums[curIdx]) { // same with previous element
                    continue;
                } else { // different from previous element
                    nums[curAvailableIdx] = nums[curIdx];
                    curAvailableIdx++;
                }
            }

            return curAvailableIdx;
        }
    }

    // []
    // [1, 1, 2]
    // [1, 1, 1]
    // [1, 1, 1, 2, 2]
    // [1, 1, 2, 3, 3, 4, 5, 6, 7, 8]

    // beats 47.64%, 65.40%
}
