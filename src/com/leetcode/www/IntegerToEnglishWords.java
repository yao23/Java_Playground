package com.leetcode.www;

public class IntegerToEnglishWords { // LC 273 (Facebook)
    private final String[] LESS_THAN_20 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine",
            "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private final String[] TENS = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    private final String[] THOUSANDS = {"", "Thousand", "Million", "Billion"};

    public String numberToWords(int num) { // beats 45.94%
        if (num == 0) {
            return "Zero";
        }

        int i = 0;
        String words = "";

        while (num > 0) {
            if (num % 1000 != 0) {
                words = helper(num % 1000) + THOUSANDS[i] + " " + words;
            }
            num /= 1000;
            i++;
        }

        return words.trim();
    }

    private String helper(int num) {
        if (num == 0) {
            return "";
        } else if (num < 20) {
            return LESS_THAN_20[num] + " ";
        } else if (num < 100) {
            return TENS[num / 10] + " " + helper(num % 10);
        } else {
            return LESS_THAN_20[num / 100] + " Hundred " + helper(num % 100);
        }
    }

    private String[] oneToNineteen = new String[] {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight",
            "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen",
            "Nineteen"};

    private String[] twentyToNinety = new String[] {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy",
            "Eighty", "Ninety"};

    private String[] hunderedToBillion = new String[] {"Hundred", "Thousand", "Million", "Billion"};

    public String numberToWordsV0(int num) {
        if (num == 0){
            return "Zero";
        }

        StringBuilder builder = new StringBuilder();
        convert(num, builder);
        return builder.toString().trim();
    }

    private void convert(int n, StringBuilder builder){
        if (n == 0) {
            return;
        }

        if (n < 20) {
            builder.append(oneToNineteen[n]);
            builder.append(" ");
        } else if (n < 100) {
            builder.append(twentyToNinety[n / 10]);
            builder.append(" ");
            convert(n%10, builder);
        } else if (n < 1000) {
            convert(n / 100, builder);
            builder.append(hunderedToBillion[0]);
            builder.append(" ");
            convert(n % 100, builder);
        } else if (n < 1000000) {
            convert(n / 1000, builder);
            builder.append(hunderedToBillion[1]);
            builder.append(" ");
            convert(n % 1000, builder);
        } else if(n < 1000000000) {
            convert(n / 1000000, builder);
            builder.append(hunderedToBillion[2]);
            builder.append(" ");
            convert(n % 1000000, builder);
        } else {
            convert(n / 1000000000, builder);
            builder.append(hunderedToBillion[3]);
            builder.append(" ");
            convert(n%1000000000, builder);
        }
    }
}

// 123 -> "One Hundred Twenty Three"
// 12345 -> "Twelve Thousand Three Hundred Forty Five"
// 1234567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
