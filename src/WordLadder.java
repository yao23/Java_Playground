import java.util.*;

public class WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (beginWord == null || endWord == null || wordList == null) {
            return 0;
        }
        if (wordList.size() == 0) {
            return 0;
        }
        Deque<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        int steps = 0;

        queue.offerLast(beginWord);
        visited.add(beginWord);

        while (!queue.isEmpty()) {
            int size = queue.size();
            steps++;
            for (int i = 0; i < size; i++) {
                String curStr = queue.pollFirst();
                for (int j = 0; j < curStr.length(); j++) {
                    for (char k = 'a'; k <= 'z'; k++) {
                        String toStr = replace(curStr, j, k); // find a candidate for next level
                        if (toStr.equals(endWord)) {
                            steps++;
                            return steps;
                        }
                        if (visited.add(toStr) && wordList.contains(toStr)) {
                            queue.offerLast(toStr);
                        }
                    }
                }
            }
        }

        return 0;
    }

    private String replace(String str, int i, char c) {
        char[] chs = str.toCharArray();
        chs[i] = c;
        return new String(chs);
    }
}
