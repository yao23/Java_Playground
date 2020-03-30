public class ValidPalindromeII {
    /**
     * Runtime: 8 ms, faster than 27.12% of Java online submissions for Valid Palindrome II.
     * Memory Usage: 40.6 MB, less than 5.55% of Java online submissions for Valid Palindrome II.
     *
     * @param s
     * @return
     */
    public boolean validPalindrome(String s) {
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                int j = s.length() - 1 - i;
                return (isPalindromeRange(s, i+1, j) ||
                        isPalindromeRange(s, i, j-1));
            }
        }
        return true;
    }

    private boolean isPalindromeRange(String s, int i, int j) {
        for (int k = i; k <= i + (j - i) / 2; k++) {
            if (s.charAt(k) != s.charAt(j - k + i)) return false;
        }
        return true;
    }
}
