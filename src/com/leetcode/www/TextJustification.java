package com.leetcode.www;

import java.util.ArrayList;
import java.util.List;

public class TextJustification {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<String>();
        int WordsCount = words.length;
        int CurLen = 0, LastI = 0;
        for( int i = 0; i <= WordsCount; i++ ) {
            if( i == WordsCount || CurLen + words[i].length() + i - LastI > maxWidth ) {
                StringBuffer buf = new StringBuffer();
                int SpaceCount = maxWidth - CurLen;
                int SpaceSlots = i - LastI - 1;
                if( SpaceSlots == 0 || i == WordsCount ) {
                    for( int j = LastI; j < i; j++ ) {
                        buf.append(words[j]);
                        if( j != (i - 1) )
                            AppendSpace(buf, 1);
                    }
                    AppendSpace(buf, maxWidth - buf.length());
                }
                else {
                    int SpaceEach = SpaceCount / SpaceSlots;
                    int SpaceExtra = SpaceCount % SpaceSlots;
                    for( int j = LastI; j < i; j++ ) {
                        buf.append(words[j]);
                        if( j != (i - 1) )
                            AppendSpace(buf, SpaceEach + (j - LastI < SpaceExtra ? 1 : 0));
                    }
                }
                res.add(buf.toString());
                LastI = i;
                CurLen = 0;
            }
            if( i < WordsCount )
                CurLen += words[i].length();
        }
        return res;
    }
    private void AppendSpace(StringBuffer StrBuf, int count) {
        for( int i = 0; i < count; i++ )
            StrBuf.append(' ');
    }
}
