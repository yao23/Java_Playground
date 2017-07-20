/**
 * Created by liyao on 7/17/17.
 */
public class DesignCompressedStringIterator {
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
                // System.out.println("next: " + compressedStr + ", " + curIdx + ", " + curLetter + ", " + curLetterIdx + ", " + curLetterNum);
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
                if (Character.isDigit(c)) { //System.out.println("digit: " + c + ", " + i); // i >= '0' && i <= '9'
                    continue;
                } else { //System.out.println("letter: " + c + ", " + i);
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
            curLetter = compressedStr.charAt(0); //System.out.println("curLetter: " + curLetter);
            int nextLetterIdx = findNextLetterIdx(); //System.out.println("nextLetterIdx: " + nextLetterIdx);
            curLetterNum = getLetterNum(nextLetterIdx); //System.out.println("curLetterNum: " + curLetterNum);
            curLetterIdx = 0;
            if (nextLetterIdx < compressedStr.length()) {
                compressedStr = compressedStr.substring(nextLetterIdx); //System.out.println("compressedStr: " + compressedStr);// update for next letter
            } else {
                compressedStr = "";
            }
            curIdx += nextLetterIdx; //System.out.println("curIdx: " + curIdx);

            // System.out.println(compressedStr + ", " + curIdx + ", " + curLetter + ", " + curLetterIdx + ", " + curLetterNum);
        }

        // ["StringIterator","next","next","next","next","next","next","hasNext","next","hasNext"]
        // [["L1e2t1C1o1d1e1"],[],[],[],[],[],[],[],[],[]]
        // => [null,'L','e','e','t','C','o',true,'d',true]

        // ["StringIterator","next","next","next","hasNext","next","next","next","next","next","next","next","hasNext","next","next","next","next","next","hasNext","next","next","next","next","next","hasNext","next","next","next","next","hasNext","next","next","next","next","next","next","next","next","next","next","next","next","next","next","next","next","next","next","next","hasNext","next","hasNext","next","next","next","next","next","next","hasNext","next","next","next","next","next","next","next","next","next","next","next","next","next","next","hasNext","next","next","next","hasNext","next","next","hasNext","next","next","next","next","next"]
        // [["x6"],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[]]
        // => [null,'x','x','x',true,'x','x','x',' ',' ',' ',' ',false,' ',' ',' ',' ',' ',false,' ',' ',' ',' ',' ',false,' ',' ',' ',' ',false,' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',false,' ',false,' ',' ',' ',' ',' ',' ',false,' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',false,' ',' ',' ',false,' ',' ',false,' ',' ',' ',' ',' ']
    }

    /**
     * Your StringIterator object will be instantiated and called as such:
     * StringIterator obj = new StringIterator(compressedString);
     * char param_1 = obj.next();
     * boolean param_2 = obj.hasNext();
     */
}
