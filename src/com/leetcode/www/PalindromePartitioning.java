package com.leetcode.www;

import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning {
    public List<List<String>> partition(String s) {
        List<List<String>> partitions = new ArrayList<>();
        List<String> partition = new ArrayList<String>();
        CreatePartition(0, s, partition, partitions);
        return partitions;
    }
    private void CreatePartition(int depth, String s, List<String> partition,
                                 List<List<String>> partitions) {
        if( depth == s.length() ) {
            List<String> TmpPar = new ArrayList<String>();
            TmpPar.addAll(partition);
            partitions.add(TmpPar);
            return;
        }
        for( int i = depth; i < s.length(); i++ ) {
            if( IsPalidrome(depth, i, s) ) {
                partition.add(s.substring(depth, i + 1));
                CreatePartition(i + 1, s, partition, partitions);
                partition.remove(partition.size() - 1);
            }
        }
    }
    private boolean IsPalidrome(int start, int end, String s) {
        while( start < end ) {
            if( s.charAt(start) != s.charAt(end) )
                return false;
            start++;
            end--;
        }

        return true;
    }
}

// given s = "aab" => [["aa","b"], ["a","a","b"]]
