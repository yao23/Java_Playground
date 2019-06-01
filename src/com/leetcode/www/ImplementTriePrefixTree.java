package com.leetcode.www;

import java.util.ArrayList;

/**
 * Created by liyao on 7/18/17.
 */
public class ImplementTriePrefixTree { // LC 208
    /**
     * Runtime: 79 ms, faster than 56.07% of Java online submissions for Implement Trie (Prefix Tree).
     * Memory Usage: 50.5 MB, less than 98.94% of Java online submissions for Implement Trie (Prefix Tree).
     */
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

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            TrieNode ws = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (ws.children[c - 'a'] == null) {
                    return false;
                }
                ws = ws.children[c - 'a'];
            }
            return ws.isWord;
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            TrieNode ws = root;
            for (int i = 0; i < prefix.length(); i++) {
                char c = prefix.charAt(i);
                if (ws.children[c - 'a'] == null) {
                    return false;
                }
                ws = ws.children[c - 'a'];
            }
            return true;
        }
    }

    class TrieNode {
        public char val;
        public boolean isWord;
        public TrieNode[] children = new TrieNode[26];

        public TrieNode() {}
        TrieNode(char c){
            TrieNode node = new TrieNode();
            node.val = c;
        }
    }

    // beats 51.91%


    class TrieV0 { // Credit Karma interview questions
        private char val;
        ArrayList<TrieV0> childrenNodes;

        public TrieV0(char val) {
            this.val = val;
            this.childrenNodes = new ArrayList<>();
        }

        public void addNodeToTree(String word, TrieV0 rootNode) {
            int i = 0;
            TrieV0 startNode = new TrieV0(' ');
            for (; i < word.length(); i++) {
                char curChar = word.charAt(i);
                // iterate nodes and find 1st missing one
                ArrayList<TrieV0> nodes = new ArrayList<>();
                nodes.add(rootNode);
                Boolean found = false;
                // traversal tree level by level
                while (!nodes.isEmpty()) {
                    TrieV0 curNode = nodes.remove(0); // first node
                    if (curChar != curNode.val) {
                        startNode = curNode;
                        found = true;
                        break;
                    } else {
                        nodes.addAll(curNode.childrenNodes);
                    }
                }
                if (found == true) {
                    break;
                }
            }

            if (i < word.length()) { // 1st char missing in the tree
                for (int j = i; j < word.length(); j++) {
                    TrieV0 newNode = new TrieV0(word.charAt(j));
                    startNode.childrenNodes.add(newNode);
                    startNode = newNode;
                }
            }
        }
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
