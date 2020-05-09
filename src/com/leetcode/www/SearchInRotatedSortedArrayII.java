public class SearchInRotatedSortedArrayII { // LC 81
    public boolean search(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (nums[mid] == target) {
                return true;
            }

            // need to handle: 1,3,1,1,1
            while (nums[start] == nums[mid] && start != mid) {
                start ++;
            }
            while (nums[mid] == nums[end] && mid != end) {
                end --;
            }

            // the following is the same as problem I
            if (nums[start] <= nums[mid]) { // left in order
                if (nums[start] <= target && target < nums[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else { // right in order
                if (nums[mid] < target && target <= nums[end]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }

        return false;
    }

    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Search in Rotated Sorted Array II.
     * Memory Usage: 38.9 MB, less than 81.69% of Java online submissions for Search in Rotated Sorted Array II.
     *
     * @param nums
     * @param target
     * @return
     */
    public boolean searchV1(int[] nums, int target) {
        if (nums.length == 0) {
            return false;
        }

        int low = 0;
        int high = nums.length - 1;
        if (nums[low] == target) {
            return true;
        }

        while (low < nums.length && nums[low] == nums[high]) {
            low++;
        }

        return search(nums, target, low, high);
    }

    public boolean search(int[] nums, int target, int low, int high) {
        if (low > high) {
            return false;
        }

        int mid = low + (high - low) / 2;
        if (nums[mid] == target) {
            return true;
        }
        if (nums[low] <= nums[mid]) {
            if (target >= nums[low] && target < nums[mid]) {
                return search(nums, target, low, mid - 1);
            }
            return search(nums, target, mid + 1, high);
        } else {
            if (target > nums[mid] && target <= nums[high]) {
                return search(nums, target, mid + 1, high);
            }
            return search(nums, target, low, mid - 1);
        }
    }
}
