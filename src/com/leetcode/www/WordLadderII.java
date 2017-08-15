package com.leetcode.www;

import java.util.*;

public class WordLadderII { // LC 126
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) {
            return res;
        }
        
        Set<String> beginSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();

        // initial words in both ends
        beginSet.add(beginWord);
        endSet.add(endWord);

        // we use a map to help construct the final result
        Map<String, List<String>> map = new HashMap<>();

        // build the map
        helper(dict, beginSet, endSet, map, false);

        String[] startStr = new String[]{beginWord};
        List<String> sol = new ArrayList<>(Arrays.asList(startStr));

        // recursively build the final result
        generateList(beginWord, endWord, map, sol, res);

        return res;
    }

    private boolean helper(Set<String> dict, Set<String> beginSet, Set<String> endSet, Map<String, List<String>> map, boolean flip) {
        if (beginSet.isEmpty()) {
            return false;
        }

        if (beginSet.size() > endSet.size()) {
            return helper(dict, endSet, beginSet, map, !flip);
        }

        // remove words on current both ends from the dict
        dict.removeAll(beginSet);
        dict.removeAll(endSet);

        // as we only need the shortest paths
        // we use a boolean value help early termination
        boolean done = false;

        // set for the next level
        Set<String> set = new HashSet<>();

        // for each string in beginSet
        for (String str : beginSet) {
            for (int i = 0; i < str.length(); i++) {
                char[] chars = str.toCharArray();

                // change one character for every position
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    chars[i] = ch;

                    String word = new String(chars);

                    // make sure we construct the tree in the correct direction
                    String key = flip ? word : str;
                    String val = flip ? str : word;

                    if (endSet.contains(word)) {
                        done = true;

                        List<String> list = map.containsKey(key) ? map.get(key) : new ArrayList<>();
                        list.add(val);
                        map.put(key, list);
                    }

                    if (!done && dict.contains(word)) {
                        set.add(word);

                        List<String> list = map.containsKey(key) ? map.get(key) : new ArrayList<>();
                        list.add(val);
                        map.put(key, list);
                    }
                }
            }
        }

        // early terminate if done is true
        return done || helper(dict, endSet, set, map, !flip);
    }

    private void generateList(String start, String end, Map<String, List<String>> map, List<String> sol, List<List<String>> res) {
        if (start.equals(end)) {
            res.add(new ArrayList<>(sol));
            return;
        }

        // need this check in case the diff between start and end happens to be one
        // e.g "a", "c", {"a", "b", "c"}
        if (!map.containsKey(start)) {
            return;
        }

        for (String word : map.get(start)) {
            sol.add(word);
            generateList(word, end, map, sol, res);
            sol.remove(sol.size() - 1);
        }
    }
}

// beginWord = "hit"
// endWord = "cog"
// wordList = ["hot","dot","dog","lot","log","cog"]
// =>
// [
//  ["hit","hot","dot","dog","cog"],
//  ["hit","hot","lot","log","cog"]
// ]

// beats 97.62%