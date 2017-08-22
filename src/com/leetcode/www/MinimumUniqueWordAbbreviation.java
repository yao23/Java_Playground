package com.leetcode.www;

public class MinimumUniqueWordAbbreviation { // LC 411
    public String minAbbreviation(String target, String[] dictionary) { // beats 98.79%
        char[] c = target.toCharArray();
        char[] tmp = new char[c.length];
        // traverse length from min to max
        for (int l = 1; l <= target.length(); l++) {
            String abbr = minAbbreviation(c, 0, tmp, 0, dictionary, l);
            if (abbr != null) {
                return abbr;
            }
        }
        return null;
    }

    private String minAbbreviation(char[] c, int p, char[] tmp, int t, String[] dictionary, int l){
        if (l == 0) {// all length has been used up
            if (p == c.length && !conflict(tmp, t, dictionary, c.length)) {
                return new String(tmp, 0, t);
            } else {
                return null;
            }
        }
        if (t == 0|| tmp[t - 1] > '9') {// can use abbr
            // c.length - 1 - (end + 1) + 1 >= l - 1 => c.length - end >= l
            // we don't need to check length of abbr = 1,
            // it will have the same length with the one that does not use abbr and has less elements to distinguish a word
            for (int end = p + 1; end <= c.length - l; end++) {
                int s = end - p + 1;
                if (s >= 10) {
                    tmp[t] = (char)(s / 10 + '0');
                    tmp[t + 1] = (char)(s % 10 + '0');
                    String r = minAbbreviation(c, end + 1, tmp, t + 2, dictionary, l - 1);
                    if (r != null) {
                        return r;
                    }
                } else {
                    tmp[t] = (char)(s + '0');
                    String r = minAbbreviation(c, end + 1, tmp, t + 1, dictionary, l - 1);
                    if (r != null) {
                        return r;
                    }
                }
            }
        }
        // use original character
        tmp[t] = c[p];
        return minAbbreviation(c, p + 1, tmp, t + 1, dictionary, l - 1);
    }

    private boolean conflict(char[] abbr, int t, String[] dictionary, int l) {
        char[] pattern = new char[abbr.length];
        int p = 0; // pointer for pattern
        int count = 0;
        for (int i = 0; i < t; i++) {
            char c = abbr[i];
            if (c <= '9') {
                count = count * 10 + c - '0';
            } else{
                if (count != 0) {
                    // store count to pattern. (note that count must be less than 22)
                    pattern[p++] = (char)count;
                    count = 0;
                }
                pattern[p++] = c;
            }
        }
        //if (count != 0) pattern[p++] = (char)count; tailing pattern doesn't need to check
        for (String s : dictionary) {
            if (s.length() != l) {
                continue;
            }
            int j = 0;
            boolean match = true;
            for (int i = 0; i < p; i++) {
                if (pattern[i] < 22) {
                    j += pattern[i]; // pass count characters
                } else if (s.charAt(j) != pattern[i]) {
                    match = false;
                    break;
                } else {
                    j++; // match one character
                }
            }
            if (match) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param target
     * @param dictionary
     * @return
     *
     * Basically as commented, transform all dictionary words to binary form with 1 represent different char and
     * 0 represent same char, while we transform target string using another binary form with 1 represent abbreviated
     * char and 0 represent kept char.
     * Example :

     * Target : "apple", and test binary 01100 = a2le
     * Dictionary : "accle" -> 01100

     * Goal will be to find the shortest binary form which does not cover all difference bit of each word in dictionary:
     * targetBinary & wordBinary != wordBinary
     * Thus the test binary is not valid, instead we should use "1p3" (10111)
     */
    public String minAbbreviationV0(String target, String[] dictionary) {
        if (target == null || target.length() == 0) {
            return target;
        }
        if (dictionary.length == 0) {
            return String.valueOf(target.length());
        }
        // Save the difference information say "abble" in dictionary against target "apple" : 01100
        int[] binaryDict = new int[dictionary.length];
        // Check if there are same length words in ditionary or not, save the loop effort
        boolean exist = false;
        for (int i = 0; i < target.length(); i++) {
            for (int j = 0; j < dictionary.length; j++) {
                String word = dictionary[j];
                if (word.length() != target.length()) {
                    continue;
                }
                exist = true;
                if (word.charAt(i) != target.charAt(i)) {
                    binaryDict[j] += (1 << i);
                }
            }
        }
        if (!exist) {
            return String.valueOf(target.length());
        }
        // Use all 1 'binary' to initialize the check, which means abbreviating every char, 11111 : 5
        int binary = generateAndCheck(binaryDict, (1 << target.length()) - 1, 0, target.length());
        return binaryToString(target, binary);
    }

    // Recover string from the 'binary' for target, say "apple", then 01100 -> a2le
    private String binaryToString(String target, int binary) {
        StringBuilder builder = new StringBuilder();
        int count = 0;
        for (int i = 0; i < target.length(); i++) {
            if (binary % 2 == 0) {
                if (count != 0) {
                    builder.append(count);
                    count = 0;
                }
                builder.append(target.charAt(i));
            } else {
                count++;
            }
            binary >>>= 1;
        }
        if (count != 0) {
            builder.append(count);
        }
        return builder.toString();
    }

    // Generate new 'binary' format of target and check with all words
    private int generateAndCheck(int[] binaryDict, int target, int index, int length) {
        // 0 is the ending condition with every char not abbreviated
        if (index == length) {
            return 0;
        }
        // Generate next binary (with bit at index set to 0)
        int nextBinary = target - (1 << index);
        boolean valid = true;
        for (int i = 0; i < binaryDict.length; i++) {
            // Which means either that word has different length or totally different characters
            if (binaryDict[i] == 0) {
                continue;
            }
            int check = binaryDict[i];
            // Key : *The abbreviation bit* of binary format (or 1) of target *should not cover* all *difference bit* of the word, otherwise they is confict
            // Like a4e (011110) will 'cover' appce (00010) or accle (01100)
            valid = valid & ((nextBinary & check) != check);
        }
        int better = shorterBinary(generateAndCheck(binaryDict, nextBinary, index + 1, length),
                generateAndCheck(binaryDict, target, index + 1, length));
        if (valid) {
            better = shorterBinary(better, nextBinary);
        }
        return better;
    }
    // Find a shorter binary format -> with more characters eliminated (continuous 1s)
    private int shorterBinary(int i1, int i2) {
        int count1 = 0;
        int count2 = 0;
        boolean flag1 = false;
        boolean flag2 = false;
        int temp1 = i1;
        int temp2 = i2;
        while (i1 != 0 || i2 != 0) {
            if (i1 % 2 == 1) {
                if (flag1) {
                    count1++;
                } else {
                    flag1 = true;
                }
            } else {
                flag1 = false;
            }
            if (i2 % 2 == 1) {
                if (flag2) {
                    count2++;
                } else {
                    flag2 = true;
                }
            } else {
                flag2 = false;
            }
            i1 >>>= 1;
            i2 >>>= 1;
        }
        return count1 > count2 ? temp1 : temp2;
    }
}
