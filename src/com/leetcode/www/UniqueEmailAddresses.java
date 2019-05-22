package com.leetcode.www;

import java.util.HashSet;

public class UniqueEmailAddresses {
    /**
     * Runtime: 20 ms, faster than 65.22% of Java online submissions for Unique Email Addresses.
     * Memory Usage: 36.9 MB, less than 99.48% of Java online submissions for Unique Email Addresses.
     *
     * @param emails
     * @return
     */
    public int numUniqueEmails(String[] emails) {
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
