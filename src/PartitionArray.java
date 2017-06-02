/**
 * Created by liyao on 6/1/17.
 */
public class PartitionArray {
    public static int[] partitionArray(int[] arr, int pivot) {
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
                // swap arr[left] and arr[right]
                int tmp = arr[left];
                arr[left] = arr[right];
                arr[right] = tmp;
                left++;
                right--;
            }
        }

        int tmp = arr[left];
        arr[left] = arr[pivotIdx];
        arr[pivotIdx] = tmp;

        return arr;
    }

    public static void main(String[] args) {

        int[] arr = new int[] {2, 8, 7, 1, 3, 5, 6, 4};

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }

        int[] output = partitionArray(arr, 4);

        System.out.println();

        for (int i = 0; i < arr.length; i++) {
            System.out.print(output[i] + " ");
        }
    }
}
