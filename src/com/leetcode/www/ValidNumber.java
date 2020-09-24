package com.leetcode.www;

public class ValidNumber { // LC 65 (Facebook)
    /**
     *
     * @param s
     * @return
     *
     * We start with trimming.

     * If we see [0-9] we reset the number flags.
     * We can only see . if we didn't see e or ..
     * We can only see e if we didn't see e but we did see a number. We reset numberAfterE flag.
     * We can only see + and - in the beginning and after an e
     * any other character break the validation.
     * At the and it is only valid if there was at least 1 number and if we did see an e then a number after it as well.

     * So basically the number should match this regular expression:

     * [-+]?(([0-9]+(.[0-9]*)?)|.[0-9]+)(e[-+]?[0-9]+)?
     */
    public boolean isNumber(String s) { // beats 77.08%
        s = s.trim();

        boolean pointSeen = false;
        boolean eSeen = false;
        boolean numberSeen = false;
        boolean numberAfterE = true;
        for (int i = 0; i < s.length(); i++) {
            if ('0' <= s.charAt(i) && s.charAt(i) <= '9') {
                numberSeen = true;
                numberAfterE = true;
            } else if (s.charAt(i) == '.') {
                if (eSeen || pointSeen) {
                    return false;
                }
                pointSeen = true;
            } else if (s.charAt(i) == 'e') {
                if (eSeen || !numberSeen) {
                    return false;
                }
                numberAfterE = false;
                eSeen = true;
            } else if (s.charAt(i) == '-' || s.charAt(i) == '+') {
                if (i != 0 && s.charAt(i - 1) != 'e') {
                    return false;
                }
            } else {
                return false;
            }
        }

        return numberSeen && numberAfterE;
    }

    public boolean isNumberV0(String s) { // beats 28.67%
        if (s == null) {
            return false;
        }
        s = s.trim();
        int len = s.length();
        if (len == 0) {
            return false;
        }
        boolean hasE = false;
        int eIndex = -1;
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == 'e' || s.charAt(i) == 'E') {
                if (hasE) {
                    return false;
                } else {
                    hasE = true;
                    eIndex = i;
                }
            }
        }
        if (hasE) {
            String s1 = s.substring(0, eIndex);
            String s2 = s.substring(eIndex + 1);
            return isNumberWithoutE(s1) && isSignedNumber(s2);
        } else {
            return isNumberWithoutE(s);
        }
    }
    private boolean isNumberWithoutE(String s) {
        int len = s.length();
        if (len == 0) {
            return false;
        }
        int start = 0;
        if (s.charAt(0) == '+' || s.charAt(0) == '-') {
            start++;
        }
        if (start == len) {
            return false;
        }
        s = s.substring(start);
        len = s.length();
        boolean hasDot = false;
        int dotIndex = -1;
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == '.') {
                if (hasDot) {
                    return false;
                } else {
                    hasDot = true;
                    dotIndex = i;
                }
            }
        }
        if (hasDot) {
            String s1 = s.substring(0, dotIndex);
            String s2 = s.substring(dotIndex + 1);
            if (s1.length() == 0 && s2.length() == 0) {
                return false;
            }
            if (s1.length() == 0) {
                return isPureNumber(s2);
            }
            if (s2.length() == 0) {
                return isPureNumber(s1);
            }
            return isPureNumber(s1) && isPureNumber(s2);
        } else {
            if (s.length() == 0) {
                return false;
            }
            return isPureNumber(s);
        }
    }
    private boolean isSignedNumber(String s)  {
        int len = s.length();
        if (len == 0) {
            return false;
        }
        int start = 0;
        if (s.charAt(0) == '+' || s.charAt(0) == '-') {
            start++;
        }
        if (start == len) {
            return false;
        }
        s = s.substring(start);
        return isPureNumber(s);
    }
    private boolean isPureNumber(String s) {
        int len = s.length();
        if (len == 0) {
            return false;
        }
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if ('0' <= c && c <= '9') {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }
}

// "0" => true
// " 0.1 " => true
// "abc" => false
// "1 a" => false
// "2e10" => true