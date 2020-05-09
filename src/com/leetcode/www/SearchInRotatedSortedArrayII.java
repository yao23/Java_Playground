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
}
