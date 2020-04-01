import java.util.*;

public class FindAllAnagramsInAString { // 438
    /**
     * Runtime: 5 ms, faster than 94.54% of Java online submissions for Find All Anagrams in a String.
     * Memory Usage: 40.6 MB, less than 12.00% of Java online submissions for Find All Anagrams in a String.
     *
     * Time complexity: O(N_s + N_p) since it's one pass along both strings.
     * Space complexity: O(1), because pCount and sCount contain not more than 26 elements.
     *
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagramsV1(String s, String p) {
        int ns = s.length(), np = p.length();
        if (ns < np) return new ArrayList();

        int [] pCount = new int[26];
        int [] sCount = new int[26];
        // build reference array using string p
        for (char ch : p.toCharArray()) {
            pCount[(ch - 'a')]++;
        }

        List<Integer> output = new ArrayList();
        // sliding window on the string s
        for (int i = 0; i < ns; ++i) {
            // add one more letter on the right side of the window
            sCount[(s.charAt(i) - 'a')]++;
            // remove one letter from the left side of the window
            if (i >= np) {
                sCount[(s.charAt(i - np) - 'a')]--;
            }
            // compare array in the sliding window with the reference array
            if (Arrays.equals(pCount, sCount)) {
                output.add(i - np + 1);
            }
        }
        return output;
    }

    /**
     * Runtime: 66 ms, faster than 22.38% of Java online submissions for Find All Anagrams in a String.
     * Memory Usage: 42 MB, less than 6.00% of Java online submissions for Find All Anagrams in a String.
     *
     * Time complexity: O(N_s + N_p) since it's one pass along both strings.
     * Space complexity: O(1), because pCount and sCount contain not more than 26 elements.
     *
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams(String s, String p) {
        int ns = s.length(), np = p.length();
        if (ns < np) return new ArrayList<>();

        Map<Character, Integer> pCount = new HashMap<>();
        Map<Character, Integer> sCount = new HashMap<>();
        // build reference hashmap using string p
        for (char ch : p.toCharArray()) {
            if (pCount.containsKey(ch)) {
                pCount.put(ch, pCount.get(ch) + 1);
            } else {
                pCount.put(ch, 1);
            }
        }

        List<Integer> output = new ArrayList<>();
        // sliding window on the string s
        for (int i = 0; i < ns; ++i) {
            // add one more letter on the right side of the window
            char ch = s.charAt(i);
            if (sCount.containsKey(ch)) {
                sCount.put(ch, sCount.get(ch) + 1);
            } else {
                sCount.put(ch, 1);
            }
            // remove one letter from the left side of the window and update sCount
            if (i >= np) {
                ch = s.charAt(i - np);
                if (sCount.get(ch) == 1) {
                    sCount.remove(ch);
                } else {
                    sCount.put(ch, sCount.get(ch) - 1);
                }
            }
            // compare hashmap in the sliding window with the reference hashmap
            if (pCount.equals(sCount)) {
                output.add(i - np + 1);
            }
        }
        return output;
    }
}
