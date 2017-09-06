package com.leetcode.www;

public class ValidNumber {
    public boolean isNumber(String s) {
        if( s == null )	return false;
        s = s.trim();
        int len = s.length();
        if( len == 0 ) return false;
        boolean HasE = false;
        int eIndex = -1;
        for( int i = 0; i < len; i++ ) {
            if( s.charAt(i) == 'e' || s.charAt(i) == 'E' ) {
                if( HasE ) return false;
                else {
                    HasE = true;
                    eIndex = i;
                }
            }
        }
        if( HasE ) {
            String s1 = s.substring(0, eIndex);
            String s2 = s.substring(eIndex + 1);
            return IsNumberWithoutE(s1) && IsSignedNumber(s2);
        }
        else
            return IsNumberWithoutE(s);
    }
    private boolean IsNumberWithoutE(String s) {
        int len = s.length();
        if( len == 0 )	return false;
        int start = 0;
        if( s.charAt(0) == '+' || s.charAt(0) == '-' )
            start++;
        if( start == len ) return false;
        s = s.substring(start);
        len = s.length();
        boolean HasDot = false;
        int DotIndex = -1;
        for( int i = 0; i < len; i++ ) {
            if( s.charAt(i) == '.' ) {
                if( HasDot ) return false;
                else {
                    HasDot = true;
                    DotIndex = i;
                }
            }
        }
        if( HasDot ) {
            String s1 = s.substring(0, DotIndex);
            String s2 = s.substring(DotIndex + 1);
            if( s1.length() == 0 && s2.length() == 0 )
                return false;
            if( s1.length() == 0 )
                return IsPureNumber(s2);
            if( s2.length() == 0 )
                return IsPureNumber(s1);
            return IsPureNumber(s1) && IsPureNumber(s2);
        }
        else {
            if( s.length() == 0 )	return false;
            return IsPureNumber(s);
        }
    }
    private boolean IsSignedNumber(String s)  {
        int len = s.length();
        if( len == 0 )	return false;
        int start = 0;
        if( s.charAt(0) == '+' || s.charAt(0) == '-' )
            start++;
        if( start == len )	return false;
        s = s.substring(start);
        return IsPureNumber(s);
    }
    private boolean IsPureNumber(String s) {
        int len = s.length();
        if( len == 0 )	return false;
        for( int i = 0; i < len; i++ ) {
            char c = s.charAt(i);
            if( '0' <= c && c <= '9' )
                continue;
            else
                return false;
        }
        return true;
    }
}
