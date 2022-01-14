package com.leetcode.www;

public class CountAndSay { // LC 38
    /**
     * beats 14.84%
     *
     *  1.     1
     *  2.     11
     *  3.     21
     *  4.     1211
     *   5.     111221
     *  *
     *  *  1 is read off as "one 1" or 11.
     *  *  11 is read off as "two 1s" or 21.
     *  *  21 is read off as "one 2, then one 1" or 1211.
     *
     * @param n
     * @return
     */
    public String countAndSay(int n) {
        String result = "1";
        for (int i = 1; i < n; i++) {
            result = countAndCreate(result);
        }
        return result;
    }
    private String countAndCreate(String result) {
        String tmp = "";
        int last = 0, len = result.length();
        for (int i = 0; i < len; i++) {
            if (result.charAt(i) != result.charAt(last)) {
                tmp = tmp + (i - last) + result.charAt(last);
                last = i;
            }
        }
        if( last < len ) {
            tmp = tmp + (len - last) + result.charAt(last);
        }
        return tmp;
    }

    /**
     *
     * @param n
     * @return
     *
     * https://discuss.leetcode.com/topic/2309/show-an-answer-in-java
     */
    public String countAndSayV0(int n) { // beats 54.56%
        StringBuilder curr = new StringBuilder("1");
        StringBuilder prev;
        int count;
        char say;
        for (int i = 1; i < n; i++) {
            prev = curr;
            curr = new StringBuilder();
            count = 1;
            say = prev.charAt(0);

            for (int j = 1, len = prev.length(); j < len; j++) {
                if (prev.charAt(j) != say) {
                    curr.append(count).append(say);
                    count = 1;
                    say = prev.charAt(j);
                } else {
                    count++;
                }
            }
            curr.append(count).append(say);
        }
        return curr.toString();
    }
}

/**

 */
