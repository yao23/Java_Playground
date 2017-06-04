/**
 * Created by liyao on 6/3/17.
 */
public class RemoveDuplicatesFromSortedArrayII {
    public int removeDuplicates(int[] nums) {
        int numsLength = nums.length;

        if (numsLength <= 2) { // length is 0, 1, 2
            return numsLength;
        } else {
            int curAvailableIdx = 1; // current available unique index
            int curDupNum = 1; // current duplicates number

            for (int curIdx = 1; curIdx < numsLength; curIdx++) {
                if (nums[curIdx - 1] == nums[curIdx]) { // same with previous element
                    curDupNum++;

                    if (curDupNum == 2) {
                        nums[curAvailableIdx] = nums[curIdx]; // [1, 1, 1, 1, 2, 2, 3]
                        curAvailableIdx++;
                    } else {
                        continue;
                    }

                } else { // different from previous element
                    nums[curAvailableIdx] = nums[curIdx];
                    curAvailableIdx++;
                    curDupNum = 1;
                }
            }

            if (curDupNum >= 2) {
                nums[curAvailableIdx - 1] = nums[numsLength - 1]; // curAvailableIdx updated when curDupNum = 2, [1,1,1,1,3,3]
            }

            return curAvailableIdx;
        }
    }

    // []
    // [1]
    // [1, 1]
    // [1, 1, 1]
    // [1, 1, 1, 1, 1]
    // [1, 1, 1, 1, 3, 3]
    // [1, 1, 1, 1, 2, 2, 3]
    // [1, 1, 1, 2, 2, 3]

    // beats 35.88%, 4.07%
}
