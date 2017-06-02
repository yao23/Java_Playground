/**
 * Created by liyao on 6/1/17.
 */

public class PartitionArray {
    private static void swap(int[] arr, int left, int right) {
        int tmp = arr[left];
        arr[left] = arr[right];
        arr[right] = tmp;
    }

    private static int[] partitionArray(int[] arr, int pivot) {
        int left = 0;
        int right = arr.length-1;
        int pivotIdx = -1;

        while (left < right) {
            while (arr[left] < pivot && left <= right) {
                left++;
            }
            while (arr[right] >= pivot && left < right) {
                if (arr[right] == pivot) {
                    pivotIdx = right;
                }
                right--;
            }

            if (left < right) {
                swap(arr, left, right);
                left++;
                right--;
            }
        }

        swap(arr, left, pivotIdx);

        return arr;
    }

    public static void main(String[] args) {

        int[] arr = new int[] {2, 8, 7, 1, 3, 5, 6, 4};

        for (int arrElement : arr) {
            System.out.print(arrElement + " ");
        }

        int[] output = partitionArray(arr, 4);

        System.out.println();

        for (int outputElement : output) {
            System.out.print(outputElement + " ");
        }
    }
}
