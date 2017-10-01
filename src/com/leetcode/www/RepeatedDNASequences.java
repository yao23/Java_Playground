package com.leetcode.www;

import java.util.*;

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

    /**
     * The idea is to use rolling hash technique or in case of string search also known as Rabin-Karp algorithm. As our
     * alphabet A consists of only 4 letters we can be not afraid of collisions. The hash for a current window slice
     * could be found in a constant time by subtracting the former first character times size of the A in the power of
     * 9 and updating remaining hash by the standard rule: hash = hash*A.size() + curr_char.
     */
    private static final Map<Character, Integer> A = new HashMap<>();
    static { A.put('A',0); A.put('C',1); A.put('G',2); A.put('T',3); }
    private final int A_SIZE_POW_9 = (int) Math.pow(A.size(), 9);

    public List<String> findRepeatedDnaSequencesV1(String s) { // beats 18.37%
        Set<String> res = new HashSet<>();
        Set<Integer> hashes = new HashSet<>();
        for (int i = 0, rhash = 0; i < s.length(); i++) {
            if (i > 9) {
                rhash -= A_SIZE_POW_9 * A.get(s.charAt(i - 10));
            }
            rhash = A.size() * rhash + A.get(s.charAt(i));
            if (i > 8 && !hashes.add(rhash)) {
                res.add(s.substring(i - 9, i + 1));
            }
        }
        return new ArrayList<>(res);
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

// All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG".
// When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.
//
// Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.
//
// For example,
//
// Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",
//
// Return:
// ["AAAAACCCCC", "CCCCCAAAAA"].