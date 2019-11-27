package io.educative.www.Coding.P02_TwoPointers;

class P02_12_MaximumTrappingWater {
    /**
     * Time complexity
     * The time complexity of the above algorithm will be O(N).
     *
     * Space complexity
     * The algorithm runs in constant space O(1).
     *
     * @param buildingHeights
     * @return
     */
    public static int findMaxWater(int[] buildingHeights) {
        // use two pointer approach to find the maximum area
        int left = 0, right = buildingHeights.length - 1;
        int maxArea = 0, currentArea = 0;

        while (left < right) {
            if (buildingHeights[left] < buildingHeights[right]) {
                currentArea = (right - left) * buildingHeights[left];
                left++;
            } else {
                currentArea = (right - left) * buildingHeights[right];
                right--;
            }
            maxArea = Math.max(maxArea, currentArea);
        }

        return maxArea;
    }

    public static void main(String[] args) {
        System.out.println(P02_12_MaximumTrappingWater.findMaxWater(new int[] { 1, 3, 5, 4, 1 }));
        System.out.println(P02_12_MaximumTrappingWater.findMaxWater(new int[] { 3, 2, 5, 4, 2 }));
        System.out.println(P02_12_MaximumTrappingWater.findMaxWater(new int[] { 1, 4, 3, 2, 5, 8, 4 }));
    }
}