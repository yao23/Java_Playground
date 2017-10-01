package com.leetcode.www;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RepeatedDNASequences { // LC 187
    public List<String> findRepeatedDnaSequences(String s) { // beats 91.81%
        Set seen = new HashSet(), repeated = new HashSet();
        for (int i = 0; i + 9 < s.length(); i++) {
            String ten = s.substring(i, i + 10);
            if (!seen.add(ten)) {
                repeated.add(ten);
            }
        }
        return new ArrayList<String>(repeated);
    }

    public List<String> findRepeatedDnaSequencesV0(String s) { // beats 52.60%
        Set<Integer> firstTime = new HashSet<>();
        Set<Integer> secondTime = new HashSet<>();
        List<String> list = new ArrayList<>();

        char[] map = new char[26];
        int len = s.length();

        // Hashing function. We have only 4 letters which we can represent by 2 bits.
        map['A' - 'A'] = 0; // A = 00
        map['C' - 'A'] = 1; // B = 01
        map['G' - 'A'] = 2; // C = 10
        map['T' - 'A'] = 3; // D = 11

        for (int i = 0; i <= len - 10; i++) {
            int sequence = 0;
            for (int j = i; j< i + 10; j++) {
                // Shift existing sequence by two to make space for the new character coming
                sequence = sequence << 2;

                // Copy the character from the map and paste those two bits in the newly created space. Read bit wise OR.
                sequence = sequence | map[s.charAt(j) - 'A'];
            }

            // For this number to be added in the list, this should be the second time this number is appearing
            // For this if condition to be true, firstTime.add() should be false.
            // firstTime.add() will be false when there is already the same number present.
            // How it will behave?
            // First time - firstTime.add(sequence) will return T
            // !firstTime.add(sequence) will become F
            // secondTime.add(sequence) will NOT be executed

            // Second time addition:
            // First time - firstTime.add(sequence) will return F
            // !firstTime.add(sequence) will become T
            // secondTime.add(sequence) will be executed
            if (!firstTime.add(sequence) && secondTime.add(sequence)) {
                list.add(s.substring(i, i + 10));
            }
        }

        return list;
    }
}
