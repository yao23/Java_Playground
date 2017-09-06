package com.leetcode.www;

import java.util.ArrayList;
import java.util.List;

public class TextJustification { // LC 68
    public List<String> fullJustify(String[] words, int maxWidth) { // beats 21.53%
        List<String> res = new ArrayList<>();
        int wordsCount = words.length;
        int curLen = 0, lastI = 0;
        for (int i = 0; i <= wordsCount; i++) {
            if (i == wordsCount || curLen + words[i].length() + i - lastI > maxWidth ) {
                StringBuilder sb = new StringBuilder();
                int spaceCount = maxWidth - curLen;
                int spaceSlots = i - lastI - 1;
                if (spaceSlots == 0 || i == wordsCount) {
                    for (int j = lastI; j < i; j++) {
                        sb.append(words[j]);
                        if (j != (i - 1)) {
                            appendSpace(sb, 1);
                        }
                    }
                    appendSpace(sb, maxWidth - sb.length());
                } else {
                    int spaceEach = spaceCount / spaceSlots;
                    int spaceExtra = spaceCount % spaceSlots;
                    for (int j = lastI; j < i; j++) {
                        sb.append(words[j]);
                        if (j != (i - 1)) {
                            appendSpace(sb, spaceEach + (j - lastI < spaceExtra ? 1 : 0));
                        }
                    }
                }
                res.add(sb.toString());
                lastI = i;
                curLen = 0;
            }
            if (i < wordsCount) {
                curLen += words[i].length();
            }
        }
        return res;
    }
    private void appendSpace(StringBuilder sb, int count) {
        for (int i = 0; i < count; i++) {
            sb.append(' ');
        }
    }
}

// words: ["This", "is", "an", "example", "of", "text", "justification."]
// maxWidth (L): 16.

// [
//   "This    is    an"
//   "example  of text",
//   "justification.  "
// ]