package com.leetcode.www;

/**
 * Created by liyao on 7/17/17.
 *
 * ["StringIterator","next","next","next","next","next","next","hasNext","next","hasNext"]
 * [["L1e2t1C1o1d1e1"],[],[],[],[],[],[],[],[],[]]
 * => [null,'L','e','e','t','C','o',true,'d',true]

 * ["StringIterator","next","next","next","hasNext","next","next","next","next","next","next","next","hasNext","next","next","next","next","next","hasNext","next","next","next","next","next","hasNext","next","next","next","next","hasNext","next","next","next","next","next","next","next","next","next","next","next","next","next","next","next","next","next","next","next","hasNext","next","hasNext","next","next","next","next","next","next","hasNext","next","next","next","next","next","next","next","next","next","next","next","next","next","next","hasNext","next","next","next","hasNext","next","next","hasNext","next","next","next","next","next"]
 * [["x6"],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[]]
 * => [null,'x','x','x',true,'x','x','x',' ',' ',' ',' ',false,' ',' ',' ',' ',' ',false,' ',' ',' ',' ',' ',false,' ',' ',' ',' ',false,' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',false,' ',false,' ',' ',' ',' ',' ',' ',false,' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',false,' ',' ',' ',false,' ',' ',false,' ',' ',' ',' ',' ']

 * / beats 83.25
 */
public class DesignCompressedStringIterator { // LC 604
    class StringIterator {
        private int curIdx;
        private char curLetter;
        private int curLetterIdx;
        private int curLetterNum;
        private String compressedStr;

        public StringIterator(String compressedString) {
            compressedStr = compressedString;
            curIdx = 0;

            if (curIdx < compressedStr.length()) {
                getNextLetter();
            } else {
                curLetter = ' ';
                curLetterNum = 0;
                curLetterIdx = 0;
            }
        }

        public char next() {
            if (hasNext()) {
                if (curLetterIdx < curLetterNum) {
                    curLetterIdx++;
                    return curLetter;
                } else { // use up cur letter
                    getNextLetter();
                    curLetterIdx++;
                    return curLetter;
                }
            } else {
                return ' ';
            }
        }

        public boolean hasNext() {
            return compressedStr.length() > 0 || curLetterIdx < curLetterNum;
        }

        private int findNextLetterIdx() {
            int i = 0;
            for (i = 1; i < compressedStr.length(); i++) {
                char c = compressedStr.charAt(i);
                if (Character.isDigit(c)) { // i >= '0' && i <= '9' not working for internationalized purpose
                    continue;
                } else {
                    return i;
                }
            }

            return i;
        }

        private int getLetterNum(int nextLetterIdx) {
            if (nextLetterIdx < compressedStr.length()) {
                return Integer.valueOf(compressedStr.substring(1, nextLetterIdx));
            } else {
                return Integer.valueOf(compressedStr.substring(1));
            }
        }

        private void getNextLetter() {
            curLetter = compressedStr.charAt(0);
            int nextLetterIdx = findNextLetterIdx();
            curLetterNum = getLetterNum(nextLetterIdx);
            curLetterIdx = 0;
            if (nextLetterIdx < compressedStr.length()) {
                compressedStr = compressedStr.substring(nextLetterIdx); // update for next letter
            } else {
                compressedStr = "";
            }
            curIdx += nextLetterIdx;
        }
    }

    /**
     * Your StringIterator object will be instantiated and called as such:
     * StringIterator obj = new StringIterator(compressedString);
     * char param_1 = obj.next();
     * boolean param_2 = obj.hasNext();
     */
}
