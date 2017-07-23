public class LargestRectangleInHistogram {
    public int largestRectangleArea(int[] heights) {
        int res = 0;
        for (int i = 0; i < heights.length; i++) {
            int area = getArea(heights, i);
            if (area > res) {
                res = area;
            }
        }
        return res;
    }

    private int getArea(int[] heights, int cur) {
        int leftIdx = findLeftIdx(heights, cur);
        int rightIdx = findRightIdx(heights, cur);
        return heights[cur] * (rightIdx - leftIdx + 1);
    }

    private int findLeftIdx(int[] heights, int cur) {
        int idx = cur - 1;
        while (idx >= 0 && heights[cur] <= heights[idx]) {
            idx--;
        }
        return idx + 1;
    }

    private int findRightIdx(int[] heights, int cur) {
        int idx = cur + 1;
        int len = heights.length;
        while (idx < len && heights[cur] <= heights[idx]) {
            idx++;
        }
        return idx - 1;
    }
}
