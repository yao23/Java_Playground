/**
 * Created by liyao on 6/3/17.
 */
public class SortColors {
    private static void swap(int[] nums, int left, int right) {
        int tmp = nums[left];
        nums[left] = nums[right];
        nums[right] = tmp;
    }

    private static int sortHelper(int[] nums, int i, int j, boolean shiftLeft) {
        while (i < j) {
            while (nums[i] == 0 && i < j) { // find white or blue in left side
                i++;
            }

            while (nums[j] == 2 && i < j) { // find white or red in right side
                j--;
            }

            if (i < j) {
                if (nums[i] == 1 && nums[j] == 1) { // left and right are white, but some colors are in middle
                    if (shiftLeft) {
                        j--;
                    } else {
                        i++;
                    }
                } else {
                    swap(nums, i, j);
                }
            }
        }

        return j;
    }

    public static void sortColors(int[] nums) {
        if (nums.length <= 1) { // array length is 0 or 1
            return;
        }

        int i = 0, j = nums.length - 1;

        j = sortHelper(nums, i, j, true);

        boolean isInOrder = true;

        for (int m = 1; m < nums.length; m++) {
            if (nums[m - 1] > nums[m]) {
                isInOrder = false;
                break;
            } else {
                continue;
            }
        }

        if (!isInOrder) {
            i = j;
            j = nums.length - 1;

            sortHelper(nums, i, j, false);
        }
    }

    private static void testResults(int[] arr, int testCaseIdx) {
        System.out.println("test case " + testCaseIdx);

        for (int inputElement : arr) {
            System.out.print(inputElement + " ");
        }

        sortColors(arr);

        System.out.println();

        for (int outputElement : arr) {
            System.out.print(outputElement + " ");
        }

        System.out.println();
    }

    public static void main(String[] args) {

        int[] arr0 = new int[] {2, 1, 2, 0, 0};
        int[] arr1 = new int[] {2, 2, 1, 0, 0};
        int[] arr2 = new int[] {2, 2, 1, 1, 0, 0, 0, 0};
        int[] arr3 = new int[] {2, 2, 1, 0, 0, 1, 1, 0, 0, 0, 0};
        int[] arr4 = new int[] {2, 2, 1, 0, 2, 1, 2, 0, 1, 1, 0, 0, 0, 0};

        // case 0
        testResults(arr0, 0);

        // case 1
        testResults(arr1, 1);

        // case 2
        testResults(arr2, 2);

        // case 3
        testResults(arr3, 3);

        // case 4
        testResults(arr4, 4);
    }
}
