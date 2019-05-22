package com.leetcode.www;

import java.util.HashSet;
import java.util.Set;

public class UniqueEmailAddresses { // LC 929
    /**
     * Runtime: 5 ms, faster than 99.66% of Java online submissions for Unique Email Addresses.
     * Memory Usage: 36.4 MB, less than 99.86% of Java online submissions for Unique Email Addresses.
     *
     * https://leetcode.com/problems/unique-email-addresses/discuss/289396/Java-simple-modular-solution-(beats-100)
     *
     * @param emails
     * @return
     */
    public int numUniqueEmails(String[] emails) {
        Set<String> uniqueEmails = new HashSet<>();
        for (String email: emails) {
            uniqueEmails.add(processEmail(email));
        }
        return uniqueEmails.size();
    }


    private String processEmail(String email){
        StringBuilder outEmail = new StringBuilder();

        boolean afterPlus = false, endEmail = false;
        for (char c: email.toCharArray()) {
            if (c == '+') {
                afterPlus = true;
            }
            if (c == '@') {
                endEmail = true;
            }
            if (endEmail || (!afterPlus && c != '.')) {
                outEmail.append(c);
            }
        }

        return outEmail.toString();
    }

    /**
     * Runtime: 20 ms, faster than 65.22% of Java online submissions for Unique Email Addresses.
     * Memory Usage: 36.9 MB, less than 99.48% of Java online submissions for Unique Email Addresses.
     *
     * @param emails
     * @return
     */
    public int numUniqueEmailsV0(String[] emails) {
        String localName = "";
        String domainName = "";
        HashSet<String> uniqueEmails = new HashSet<>();
        for (int i = 0; i <= emails.length-1; i++) {
            localName = emails[i].substring(0,emails[i].indexOf("@"));
            domainName = emails[i].substring(emails[i].indexOf("@"), emails[i].length());
            if (localName.contains(".") || localName.contains("+")) {
                localName=localName.replace(".","");
                if (localName.contains("+")) {
                    localName = localName.substring(0,localName.indexOf("+"));

                }

            }
            uniqueEmails.add(localName.concat(domainName));

        }

        return uniqueEmails.size();
    }
}
