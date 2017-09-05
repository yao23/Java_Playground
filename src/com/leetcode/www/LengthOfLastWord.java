package com.leetcode.www;

public class LengthOfLastWord {
    public int lengthOfLastWord(String s) { // beats 57.84%
        int i = s.length() - 1;
        while( i >= 0 && s.charAt(i) == ' ' )
            i--;
        int len  = 0;
        while( i>= 0 && s.charAt(i) != ' ' ) {
            len++;
            i--;
        }
        return len;
    }

    public int lengthOfLastWordV0(String s) { // beats 12.51%
        if( s.length() == 0 )	return 0;
        String[] strs = s.split(" ");
        for( int i = strs.length - 1; i >= 0; i-- ) {
            if( IsWord(strs[i]) )
                return strs[i].length();
        }
        return 0;
    }

    private boolean IsWord(String s) {
        if( s.length() == 0 )
            return false;
        char c = ' ';
        for( int i = 0; i < s.length(); i++ ) {
            c = s.charAt(i);
            if( (c < 'a'  && c > 'Z') || c < 'A' || c > 'z' )
                return false;
        }
        return true;
    }
}
