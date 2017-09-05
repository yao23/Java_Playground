package com.leetcode.www; /**
 * Created by liyao on 6/11/17.
 */
import java.util.List;
import java.util.ArrayList;

public class LetterCombinationsOfAPhoneNumber { // LC 17
    private char[] getLetters(char c) {
        char[] letters;
        switch (c) {
            case '0':
                letters = new char[]{};
                break;
            case '1':
                letters = new char[]{};
                break;
            case '2':
                letters = new char[]{'a','b','c'};
                break;
            case '3':
                letters = new char[]{'d','e','f'};
                break;
            case '4':
                letters = new char[]{'g','h','i'};
                break;
            case '5':
                letters = new char[]{'j','k','l'};
                break;
            case '6':
                letters = new char[]{'m','n','o'};
                break;
            case '7':
                letters = new char[]{'p','q','r','s'};
                break;
            case '8':
                letters = new char[]{'t','u','v'};
                break;
            case '9':
                letters = new char[]{'w','x','y','z'};
                break;
            default:
                letters = new char[]{};
        }

        return letters;
    }

    private void helper(int depth, String digits, StringBuilder str, List<String> result) {
        if (depth == digits.length()) {
            String s = str.toString();
            result.add(s);
            return;
        }

        char[] chars = getLetters(digits.charAt(depth));
        for (char c : chars) {
            str.append(c);
            helper(depth+1, digits, str, result);
            str.deleteCharAt(str.length()-1); // delete last added char
        }
    }

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits.length() == 0) {
            return result;
        }
        helper(0, digits, new StringBuilder(), result);
        return result;
    }

    // "" => []
    // "23" => ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]

    // beats 47.01%
}
