package com.leetcode.www;

import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning { // LC 131
    public List<List<String>> partition(String s) { // beats 68.43%
        List<List<String>> partitions = new ArrayList<>();
        List<String> partition = new ArrayList<>();
        createPartition(0, s, partition, partitions);
        return partitions;
    }

    private void createPartition(int depth, String s, List<String> partition, List<List<String>> partitions) {
        if (depth == s.length()) {
            List<String> tmpPar = new ArrayList<>();
            tmpPar.addAll(partition);
            partitions.add(tmpPar);
            return;
        }
        for (int i = depth; i < s.length(); i++) {
            if (isPalidrome(depth, i, s)) {
                partition.add(s.substring(depth, i + 1));
                createPartition(i + 1, s, partition, partitions);
                partition.remove(partition.size() - 1);
            }
        }
    }
    private boolean isPalidrome(int start, int end, String s) {
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }

        return true;
    }
}

// given s = "aab" => [["aa","b"], ["a","a","b"]]
