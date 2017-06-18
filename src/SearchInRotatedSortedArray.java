/**
 * Created by liyao on 6/15/17.
 */
public class SearchInRotatedSortedArray {
    public int search(int[] nums, int target) {
        int index = -1;
        int len = nums.length;
        if (len == 0) {
            return index;
        } else if (len == 1) {
            if (nums[0] == target) {
                return 0;
            } else {
                return index;
            }
        } else {
            int start = 0, end = len - 1;

            while (start + 1 < end) {
                int mid = start + (end - start) / 2;
                if (nums[mid] == target) {
                    return mid;
                }
                if (nums[start] < nums[mid]) { // left part is in order
                    if (nums[mid] < target) {
                        start = mid;
                    } else {
                        if (nums[start] == target) {
                            return start;
                        } else if (nums[start] < target) { // target maybe locate in left part
                            end = mid;
                        } else { // target maybe locate in right part
                            start = mid;
                        }
                    }
                } else { // right part is in order
                    if (nums[mid] < target) {
                        if (nums[end] == target) {
                            return end;
                        } else if (nums[end] < target) { // target maybe locate in left part
                            end = mid;
                        } else { // target maybe locate in right part
                            start = mid;
                        }
                    } else {
                        end = mid;
                    }
                }
            }

            if (nums[start] == target) {
                return start;
            } else if (nums[start + 1] == target) {
                return start + 1;
            } else {
                return index;
            }
        }
    }

    // [],5 => -1
    // [1],5 => -1
    // [5],5 => 0
    // [4,5,6,7,0,1,2],5 => 1
    // [4,5,6,7,0,1,2],2 => 6
    // [4,5,0,1,2],2 => 4
    // [4,5,0,1,2],-1 => -1

    // beats 53.55%
}
