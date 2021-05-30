package com.leetcode.www;

public class CompareVersionNumbers { // LC 165
    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Compare Version Numbers.
     * Memory Usage: 33.3 MB, less than 64.51% of Java online submissions for Compare Version Numbers.
     *
     * "1", "0" => 1
     * beats 96.33%
     * @param version1
     * @param version2
     * @return
     */
    public int compareVersion(String version1, String version2) {
        int temp1 = 0, temp2 = 0;
        int len1 = version1.length(), len2 = version2.length();
        int i = 0, j = 0;
        while (i < len1 || j < len2) {
            temp1 = 0;
            temp2 = 0;
            while (i < len1 && version1.charAt(i) != '.') {
                temp1 = temp1 * 10 + version1.charAt(i++) - '0';
            }
            while (j < len2 && version2.charAt(j) != '.') {
                temp2 = temp2 * 10 + version2.charAt(j++) - '0';
            }
            if (temp1 > temp2) {
                return 1;
            } else if(temp1 < temp2) {
                return -1;
            } else {
                i++;
                j++;
            }
        }
        return 0;
    }

    /**
     * Runtime: 2 ms, faster than 16.34% of Java online submissions for Compare Version Numbers.
     * Memory Usage: 33.4 MB, less than 63.19% of Java online submissions for Compare Version Numbers.
     *
     * @param version1
     * @param version2
     * @return
     */
    public int compareVersionV0(String version1, String version2) { // beats 11.74%
        String[] levels1 = version1.split("\\.");
        String[] levels2 = version2.split("\\.");

        int length = Math.max(levels1.length, levels2.length);
        for (int i = 0; i < length; i++) {
            Integer v1 = i < levels1.length ? Integer.parseInt(levels1[i]) : 0;
            Integer v2 = i < levels2.length ? Integer.parseInt(levels2[i]) : 0;
            int compare = v1.compareTo(v2);
            if (compare != 0) {
                return compare;
            }
        }

        return 0;
    }
}


