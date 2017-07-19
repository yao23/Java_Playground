/**
 * Created by liyao on 7/18/17.
 */

public class AddAndSearchWord {
    class WordDictionary {
        

        private ImplementTriePrefixTree.Trie trieTree;

        /** Initialize your data structure here. */
        public WordDictionary() {
            trieTree = new ImplementTriePrefixTree.Trie(' ');
        }

        /** Adds a word into the data structure. */
        public void addWord(String word) {
            trieTree.add(word);
        }

        /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
        public boolean search(String word) {
            for (int i = 0; i < word.length(); i++) {
                if (!searchInTrieTree(word.substring(0, i + 1))) {
                    return false;
                }
            }

            return true;
        }

        private searchInTrieTree(String str) {
            int len = str.length();
            if (str.charAt(len - 1) == '.') {
                for (char c = 'a'; c <= 'z'; c++) {
                    char[] tmp = str.toCharArray();
                    tmp[len - 1] = c;
                    if (trieTree.startWith(new String(tmp))) {
                        return true;
                    } else {
                        continue;
                    }
                }
                return false;
            } else {
                return trieTree.startWith(str);
            }
        }
    }

    /**
     * Your WordDictionary object will be instantiated and called as such:
     * WordDictionary obj = new WordDictionary();
     * obj.addWord(word);
     * boolean param_2 = obj.search(word);
     */
}
