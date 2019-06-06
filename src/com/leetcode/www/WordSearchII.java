package com.leetcode.www;

import java.util.ArrayList;
import java.util.List;

public class WordSearchII { // LC 212
    /**
     * Runtime: 19 ms, faster than 54.34% of Java online submissions for Word Search II.
     * Memory Usage: 45 MB, less than 76.57% of Java online submissions for Word Search II.
     *
     * @param board
     * @param words
     * @return
     */
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        int row = board.length;
        if (row == 0 || words.length == 0) {
            return res;
        }
        int col = board[0].length;
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }

        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                String oneRes = "";
                // 3. Use TrieNode directly. If using trie, it needs to run "search", "startWith" again and again.
                //    using TrieNode can avoid it.
                dfs(board, res, oneRes, r, c, trie.root);
            }
        }

        return res;
    }

    private void dfs(char[][] board, List<String> res, String oneRes, int row, int col, TrieNode root) {
        // find an input string
        if (root != null && root.isWord) {
            if (!res.contains(oneRes)) {
                res.add(oneRes);
            }
            // 1. do NOT return. otherwise "aaa" is hit but "aaab" will be ignored
            //    this is NOT a problem for "word search"
            //return;
        }
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length || root == null) {
            return;
        }

        // 2. this "if" matters. Otherwise TLE. It avoids unnecessary one step try.
        TrieNode[] childList = root.children;
        char c = board[row][col];
        // c is not visited and exists in Trie
        if (c != '#' &&  childList[c - 'a'] != null) {
            int dx[] = new int[]{-1, 1, 0, 0};
            int dy[] = new int[]{0, 0, -1, 1};

            board[row][col] = '#';

            for (int i = 0; i < 4; i++) {
                dfs(board, res, oneRes + c, row + dx[i], col + dy[i],  childList[c - 'a']);
            }

            board[row][col] = c;
        }
    }

    class Trie {
        private TrieNode root;

        /** Initialize your data structure here. */
        public Trie() {
            root = new TrieNode();
            root.val = ' ';
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            TrieNode ws = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (ws.children[c - 'a'] == null) {
                    ws.children[c - 'a'] = new TrieNode(c);
                }
                ws = ws.children[c - 'a'];
            }
            ws.isWord = true;
        }
    }

    class TrieNode {
        public char val;
        public boolean isWord;
        public TrieNode[] children = new TrieNode[26];

        public TrieNode() {}
        TrieNode(char c) {
            TrieNode node = new TrieNode();
            node.val = c;
        }
    }
}

// [
//    ['o','a','a','n'],
//    ['e','t','a','e'],
//    ['i','h','k','r'],
//    ['i','f','l','v']
// ]
// words = ["oath","pea","eat","rain"]
// => ["eat","oath"]

// beats 29.37%
