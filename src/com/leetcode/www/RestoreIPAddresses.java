package com.leetcode.www;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RestoreIPAddresses { // LC 93
    /**
     * Runtime: 1 ms, faster than 99.75% of Java online submissions for Restore IP Addresses.
     * Memory Usage: 35.7 MB, less than 66.82% of Java online submissions for Restore IP Addresses.
     *
     * @param s
     * @return
     */
    public List<String> restoreIpAddresses(String s) { // beats 97.62%
        List<String> results = new ArrayList<>();
        if (s.length() == 0 || s == null) {
            return results;
        }
        int depth = 0, start = 0;
        String ip = "";
        generateIP(s, start, depth, results, ip);
        return results;
    }

    private void generateIP(String s, int start, int depth, List<String> results, String ip) {
        if ((s.length() - start) > (4 - depth) * 3) {
            return;
        }
        if (s.length() - start < 4 - depth) {
            return;
        }
        if (depth == 4) {
            ip = ip.substring(0, ip.length() - 1);
            if (!results.contains(ip)) {
                results.add(ip);
            }
            return;
        }
        int num = 0;
        for (int i = start; i < Math.min(start + 3, s.length()); i++) {
            num = num * 10 + (s.charAt(i) - '0');
            if (num <= 255) {
                generateIP(s, i + 1, depth + 1, results, ip + num + '.');
            }
            if (num == 0) {
                break;
            }
        }
    }

    /**
     * Runtime: 3 ms, faster than 23.55% of Java online submissions for Restore IP Addresses.
     * Memory Usage: 35.6 MB, less than 67.38% of Java online submissions for Restore IP Addresses.
     *
     * @param string
     * @return
     */
    public List<String> restoreIpAddressesV1(String string) {
        List<String> res = new LinkedList<>();

        if (string == null || string.length() < 4 || string.length() > 12) {
            return res;
        }

        if (string.length() == 4) {
            String s = "";
            for (Character c : string.toCharArray()) {
                s = s + c + ".";
            }
            res.add(s.substring(0, s.length() - 1));
            return res;
        }

        helper(string, res, "", 0, 0);
        return res;

    }

    private void helper(String string, List<String> res, String tmpS, int count, int index) {
        if (index == string.length() && count == 4) {
            res.add(tmpS.substring(0, tmpS.length() - 1));
            return;
        }

        if (count >= 4 || index >= string.length()) {
            return;
        }


        for (int i = index; i < string.length(); i++) {
            long tmp = Long.valueOf(string.substring(index, i + 1));
            if (tmp >= 0 && tmp <= 255 && String.valueOf(tmp).length() == string.substring(index, i + 1).length()) {
                helper(string, res, tmpS + tmp + ".", count + 1, i + 1);
            }
        }
    }

    public List<String> restoreIpAddressesV0(String s) { // beats 5.98%
        List<String> res = new ArrayList<>();
        if (s.length() < 4 || s.length() > 12) {
            return res;
        }
        dfs(s, "", res, 0);
        return res;
    }

    public void dfs(String s, String tmp, List<String> res, int depth){
        if (depth == 3 && isValid(s)) {
            res.add(tmp + s);
            return;
        }
        for (int i = 1; i < 4 && i < s.length(); i++) {
            String substr = s.substring(0, i);
            if (isValid(substr)) {
                dfs(s.substring(i), tmp + substr + '.', res, depth + 1);
            }
        }
    }

    private boolean isValid(String s) {
        if (s.charAt(0) == '0') {
            return s.equals("0");
        }
        int num = Integer.parseInt(s);
        return num <= 255 && num > 0;
    }
}

// Given "25525511135", return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)