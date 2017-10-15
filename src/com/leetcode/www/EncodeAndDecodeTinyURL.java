package com.leetcode.www;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class EncodeAndDecodeTinyURL { // LC 535
    /**
     * https://leetcode.com/articles/encode-and-decode-tinyurl/
     */

    // random function
    class Codec {
        Map<Integer, String> map = new HashMap<>();
        Random r = new Random();
        int key = r.nextInt(10000);
        public String encode(String longUrl) {
            while (map.containsKey(key)) {
                key = r.nextInt(10000);
            }
            map.put(key, longUrl);
            return "http://tinyurl.com/" + key;
        }
        public String decode(String shortUrl) {
            return map.get(Integer.parseInt(shortUrl.replace("http://tinyurl.com/", "")));
        }
    }

    // hashcode
    class CodecV1 { // beats 29.34%
        Map<Integer, String> map = new HashMap<>();
        public String encode(String longUrl) {
            map.put(longUrl.hashCode(), longUrl);
            return "http://tinyurl.com/" + longUrl.hashCode();
        }
        public String decode(String shortUrl) {
            return map.get(Integer.parseInt(shortUrl.replace("http://tinyurl.com/", "")));
        }
    }

    // counter
    class CodecV0 { // beats 29.34%
        Map<Integer, String> map = new HashMap<>();
        int i = 0;
        // Encodes a URL to a shortened URL.
        public String encode(String longUrl) {
            map.put(i, longUrl);
            return "http://tinyurl.com/" + i++;
        }

        // Decodes a shortened URL to its original URL.
        public String decode(String shortUrl) {
            return map.get(Integer.parseInt(shortUrl.replace("http://tinyurl.com/", "")));
        }
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(url));

// "https://leetcode.com/problems/design-tinyurl" => "https://leetcode.com/problems/design-tinyurl"