/**
 * Created by liyao on 7/17/17.
 */
public class DesignCompressedStringIterator {
    class StringIterator {
        private int curIdx;
        private Character curLetter;
        private int curLetterIdx;
        private int curLetterNum;
        private String compressedStr;

        public StringIterator(String compressedString) {
            compressedStr = compressedString;

            if (curIdx < compressedStr.length()) {
                getNextLetter();
            } else {
                curLetter = ' ';
                curLetterNum = 0;
                curIdx = 0;
            }
            curLetterIdx = 0;
        }

        public char next() {
            if (hasNext()) {
                if (curLetterIdx < curLetterNum) {
                    curLetterIdx++;
                    return curLetter;
                } else { // use up cur letter
                    getNextLetter();
                    return curLetter;
                }
            } else {
                return ' ';
            }
        }

        public boolean hasNext() {
            return curIdx < compressedStr.length();
        }

        private int findNextLetterIdx() {
            for (int i = 1; i < compressedStr.length(); i++) {
                Character c = compressedStr.charAt(i);
                if (i >= '0' && i <= '9') {
                    continue;
                } else {
                    return i;
                }
            }

            return 0;
        }

        private int getLetterNum(int nextLetterIdx) {
            return Integer.valueOf(compressedStr.substring(1, nextLetterIdx));
        }

        private void getNextLetter() {
            curLetter = compressedStr.charAt(0);
            int nextLetterIdx = findNextLetterIdx();
            curLetterNum = getLetterNum(nextLetterIdx);
            curLetterIdx = 0;
            compressedStr = compressedStr.substring(nextLetterIdx); // update for next letter
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
