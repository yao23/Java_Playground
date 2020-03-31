import java.util.Arrays;

public class ReorganizeString { // 767
    /**
     * Runtime: 1 ms, faster than 83.16% of Java online submissions for Reorganize String.
     * Memory Usage: 37.8 MB, less than 7.14% of Java online submissions for Reorganize String.
     *
     * @param S
     * @return
     */
    public String reorganizeString(String S) {
        int N = S.length();
        int[] counts = new int[26];
        for (char c: S.toCharArray()) counts[c-'a'] += 100;
        for (int i = 0; i < 26; ++i) counts[i] += i; // avoid pollute from 1 to 26
        // Encoded counts[i] = 100*(actual count) + (i)
        Arrays.sort(counts);

        char[] ans = new char[N];
        int t = 1;
        for (int code: counts) {
            int ct = code / 100;
            char ch = (char) ('a' + (code % 100));
            if (ct > (N+1) / 2) return ""; // more than half space
            for (int i = 0; i < ct; ++i) {
                if (t >= N) t = 0; // from start to avoid override
                ans[t] = ch;
                t += 2;
            }
        }

        return String.valueOf(ans);
    }
}
