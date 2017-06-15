/**
 * Created by liyao on 6/14/17.
 */
public class SearchInsertPosition {
    public int searchInsert(int[] arr, int target) {
        int start = 0, end = arr.length - 1;
        if (target < arr[start]) { // smaller than start
            return start;
        } else if (target > arr[end]) { // larger than end
            return (end + 1);
        }

        while (start + 1 < end) {
            int mid = start + (end - start)/2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }

        if (arr[start] < target) {
            return (start + 1);
        } else {
            return start;
        }
    }

    // [1,3,5,6], 5 -> 2
    // [1,3,5,6], 2 -> 1
    // [1,3,5,6], 7 -> 4
    // [1,3,5,6], 0 -> 0

    // beats 31.75%
}
