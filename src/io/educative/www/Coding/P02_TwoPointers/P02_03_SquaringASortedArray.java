package io.educative.www.Coding.P02_TwoPointers;

class P02_03_SquaringASortedArray {
    /**
     * Time complexity
     * The time complexity of the above algorithm will be O(N) as we are iterating the input array only once.
     *
     * Space complexity
     * The space complexity of the above algorithm will also be O(N); this space will be used for the output array.
     *
     * @param arr
     * @return
     */
    public static int[] makeSquares(int[] arr) {
        int n = arr.length;
        int[] squares = new int[n];
        int highestSquareIdx = n - 1;
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int leftSquare = arr[left] * arr[left];
            int rightSquare = arr[right] * arr[right];
            if (leftSquare > rightSquare) {
                squares[highestSquareIdx--] = leftSquare;
                left++;
            } else {
                squares[highestSquareIdx--] = rightSquare;
                right--;
            }
        }
        return squares;
    }

    public static void main(String[] args) {

        int[] result = P02_03_SquaringASortedArray.makeSquares(new int[] { -2, -1, 0, 2, 3 });
        for (int num : result)
            System.out.print(num + " ");
        System.out.println();

        result = P02_03_SquaringASortedArray.makeSquares(new int[] { -3, -1, 0, 1, 2 });
        for (int num : result)
            System.out.print(num + " ");
        System.out.println();
    }
}