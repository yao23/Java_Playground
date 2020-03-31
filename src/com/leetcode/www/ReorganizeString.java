import java.util.Arrays;
import java.util.PriorityQueue;

public class ReorganizeString { // 767
    /**
     * Runtime: 1 ms, faster than 83.16% of Java online submissions for Reorganize String.
     * Memory Usage: 37.8 MB, less than 7.14% of Java online submissions for Reorganize String.
     *
     * Time Complexity: O(A(N+logA)), where N is the length of S, and A is the size of the alphabet.
     * In Java, our implementation is O(N+AlogA). If A is fixed, this complexity is O(N).
     *
     * Space Complexity: O(N). In Java, our implementation is O(N + A).
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

    /**
     * Runtime: 2 ms, faster than 71.22% of Java online submissions for Reorganize String.
     * Memory Usage: 37.8 MB, less than 7.14% of Java online submissions for Reorganize String.
     *
     * Time Complexity: O(NlogA)), where N is the length of S, and A is the size of the alphabet.
     * If A is fixed, this complexity is O(N).
     *
     * Space Complexity: O(A). If A is fixed, this complexity is O(1).
     *
     * @param S
     * @return
     */
    public String reorganizeStringV1(String S) {
        int N = S.length();
        int[] count = new int[26];
        for (char c: S.toCharArray()) count[c-'a']++;
        PriorityQueue<MultiChar> pq = new PriorityQueue<MultiChar>((a, b) ->
                a.count == b.count ? a.letter - b.letter : b.count - a.count);

        for (int i = 0; i < 26; ++i) if (count[i] > 0) {
            if (count[i] > (N + 1) / 2) return "";
            pq.add(new MultiChar(count[i], (char) ('a' + i)));
        }

        StringBuilder ans = new StringBuilder();
        while (pq.size() >= 2) {
            MultiChar mc1 = pq.poll();
            MultiChar mc2 = pq.poll();
            /*This code turns out to be superfluous, but explains what is happening
            if (ans.length() == 0 || mc1.letter != ans.charAt(ans.length() - 1)) {
                ans.append(mc1.letter);
                ans.append(mc2.letter);
            } else {
                ans.append(mc2.letter);
                ans.append(mc1.letter);
            }*/
            ans.append(mc1.letter);
            ans.append(mc2.letter);
            if (--mc1.count > 0) pq.add(mc1);
            if (--mc2.count > 0) pq.add(mc2);
        }

        if (pq.size() > 0) ans.append(pq.poll().letter);
        return ans.toString();
    }
}

class MultiChar {
    int count;
    char letter;
    MultiChar(int ct, char ch) {
        count = ct;
        letter = ch;
    }
}
