package com.leetcode.www;

public class ScrambleString { // LC 87
    public boolean isScramble(String s1, String s2) {

    }
}

// choose any non-leaf node and swap its two children

// s1 = "great", s2 = "rgeat" => true
//        great
//        /    \
//       gr    eat
//      / \    /  \
//     g   r  e   at
//                / \
//               a   t
//
// choose "gr"
//
//         rgeat
//        /    \
//       rg    eat
//      / \    /  \
//     r   g  e   at
//                / \
//               a   t
//
// if continue to swap the children of nodes "eat" and "at"
//
//         rgtae
//        /    \
//       rg    tae
//      / \    /  \
//     r   g  ta  e
//            / \
//           t   a
//
// s1 = "great", s2 = "rgtae" => true