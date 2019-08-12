package io.educative.www.Coding.P10_Subsets;

import java.util.*;

public class P10_04_StringPermutationsByChangingCase {
    /**
     * Time complexity
     * Since we can have 2^N permutations at the most and while processing each permutation we convert it into a
     * character array, the overall time complexity of the algorithm will be O(N*2^N).
     *
     * Space complexity
     * All the additional space used by our algorithm is for the output list. Since we can have a total of O(2^N)
     * permutations, the space complexity of our algorithm is O(2^N).
     *
     * @param str
     * @return
     */
    public static List<String> findLetterCaseStringPermutations(String str) {
        List<String> permutations = new ArrayList<>();
        if (str == null)
            return permutations;

        permutations.add(str);
        // process every character of the string one by one
        for (int i = 0; i < str.length(); i++) {
            if (Character.isLetter(str.charAt(i))) { // only process characters, skip digits
                // we will take all existing permutations and change the letter case appropriately
                int n = permutations.size();
                for (int j = 0; j < n; j++) {
                    char[] chs = permutations.get(j).toCharArray();
                    // if the current character is in upper case change it to lower case or vice versa
                    if (Character.isUpperCase(chs[i]))
                        chs[i] = Character.toLowerCase(chs[i]);
                    else
                        chs[i] = Character.toUpperCase(chs[i]);
                    permutations.add(String.valueOf(chs));
                }
            }
        }
        return permutations;
    }

    public static void main(String[] args) {
        List<String> result = P10_04_StringPermutationsByChangingCase.findLetterCaseStringPermutations("ad52");
        System.out.println(" String permutations are: " + result);

        result = P10_04_StringPermutationsByChangingCase.findLetterCaseStringPermutations("ab7c");
        System.out.println(" String permutations are: " + result);
    }
}
