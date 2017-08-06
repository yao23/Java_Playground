import java.util.*;

public class WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> beginSet = new HashSet<>(), endSet = new HashSet<>();

        int len = 1;
        HashSet<String> visited = new HashSet<>();

        beginSet.add(beginWord);
        endSet.add(endWord);
        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            // always start from smaller set
            if (beginSet.size() > endSet.size()) {
                Set<String> set = beginSet;
                beginSet = endSet;
                endSet = set;
            }

            Set<String> nextLevel = new HashSet<>();
            for (String word : beginSet) {
                char[] chs = word.toCharArray();

                for (int i = 0; i < chs.length; i++) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        char old = chs[i];
                        chs[i] = c;
                        String target = String.valueOf(chs);

                        if (endSet.contains(target)) {
                            return len + 1;
                        }

                        if (!visited.contains(target) && wordList.contains(target)) {
                            nextLevel.add(target);
                            visited.add(target);
                        }
                        chs[i] = old;
                    }
                }
            }

            beginSet = nextLevel; // update for next level
            len++;
        }

        return 0;
    }

    public int ladderLengthV0(String beginWord, String endWord, List<String> wordList) {
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

// "hit","cog",["hot","dot","dog","lot","log"] => 5 ("hit" -> "hot" -> "dot" -> "dog" -> "cog")
