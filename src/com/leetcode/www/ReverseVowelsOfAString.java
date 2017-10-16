package com.leetcode.www;

public class ReverseVowelsOfAString { // LC 345
    public String reverseVowels(String s) { // beats 94.22%
        if (s.length() < 2) {
            return s;
        }

        char[] tab = s.toCharArray();
        int j = tab.length - 1;
        int i = 0;

        while( i < j ) {
            if (!isVowel(tab[i])) {
                i++;
            } else {
                while (j != i && !isVowel(tab[j])) {
                    j--;
                }

                char temp = tab[i];
                tab[i] = tab[j];
                tab[j] = temp;
                i++;
                j--;
            }
        }
        return new String(tab);
    }

    private boolean isVowel(char a){
        switch(a){
            case ('a') : return true;
            case ('e') : return true;
            case ('i') : return true;
            case ('o') : return true;
            case ('u') : return true;
            case ('A') : return true;
            case ('E') : return true;
            case ('I') : return true;
            case ('O') : return true;
            case ('U') : return true;
            default : return false;
        }
    }

    public String reverseVowelsV0(String s) { // beats 12.77%
        if (s == null || s.length() == 0) {
            return s;
        }
        String vowels = "aeiouAEIOU";
        char[] chars = s.toCharArray();
        int start = 0;
        int end = s.length() - 1;
        while (start < end) {
            while (start < end && !vowels.contains(chars[start] + "")) {
                start++;
            }
            while (start < end && !vowels.contains(chars[end] + "")){
                end--;
            }

            char temp = chars[start];
            chars[start] = chars[end];
            chars[end] = temp;

            start++;
            end--;
        }
        return new String(chars);
    }
}

// Given s = "hello", return "holle".
// Given s = "leetcode", return "leotcede".