public class LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        int left = 0, res = 0;
        int[] count = new int[256];

        for (int cur = 0; cur < s.length(); cur++) {
            char curChar = s.charAt(cur);
            if (count[curChar] == 0) {
                count[curChar] = 1;
            } else { // repeated
                count[s.charAt(left)] = 0;
                while (s.charAt(left) != curChar) { // find repeated character
                    left++;
                    count[s.charAt(left)] = 0;
                }
                left++;
                count[curChar] = 1;
            }

            int len = cur - left + 1;
            if (len > res) {
                res = len;
            }
        }

        return res;
    }

    // "" => 0
    // "a" => 1
    // "abcabcbb" => 3 ("abc")
    // "bbbbb" => 1 ("b")
    // "pwwkew" => 3 ("wke")

    // beats 92.44%
}
