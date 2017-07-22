public class LongestSubstringWithAtMostTwoDistinctCharacters {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int k = 2, left = 0, num = 0, res = 0;
        int[] count = new int[256];
        for (int right = 0; right < s.length(); right++) {
            // update cur char
            char rightChar = s.charAt(right);
            if (count[rightChar] == 0) {
                num++;
            }
            count[rightChar] += 1;

            // process previous chars if more than k distinct characters
            if (num > k) {
                count[s.charAt(left)] -= 1;
                while (count[s.charAt(left)] > 0) {
                    left++;
                    count[s.charAt(left)] -= 1;
                }
                left++;
                num--;
            }

            int len = right - left + 1;
            if (len > res) {
                res = len;
            }
        }

        return res;
    }

    // "eceba", 2 => 3 ("ece")

    // beats 79.44%

    // similar problem: LC 340 (Longest Substring with At Most K Distinct Characters)
}
