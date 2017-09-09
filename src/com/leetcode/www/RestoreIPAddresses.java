package com.leetcode.www;

import java.util.ArrayList;
import java.util.List;

public class RestoreIPAddresses {
    public List<String> restoreIpAddresses(String s) {
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

    public List<String> restoreIpAddresses2(String s) {
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