import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithAtMostKDistinctCharacters {
    private Map<Character, Integer> map = new HashMap<>();
    private int counter = 0;

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int len = s.length();
        if (len == 0 || k == 0) {
            return 0;
        } else {
            int left = 0, right = 0, maxLen = 0;
            while (right < len) { System.out.println("right: " + right);
                char c = s.charAt(right);
                if (map.containsKey(c)) { System.out.println("map contains");
                    if (counter < k) {
                        map.put(c, map.get(c) + 1);
                    } else {
                        if (map.get(c) == 0) {
                            while (left <= right && counter == k) {
                                char leftChar = s.charAt(left);
                                int leftCharCounter = map.get(leftChar);
                                map.put(leftChar, leftCharCounter - 1);
                                if (leftCharCounter == 1) { // pass all left char
                                    counter--;
                                }
                                left++;
                            }

                            map.put(c, 1); System.out.println("map after: "); System.out.println(map);
                            counter++; System.out.println("counter after: " + counter);
                        } else {
                            map.put(c, map.get(c) + 1);
                        }
                    }
                } else { // map doesn't contain c
                    if (counter < k) { System.out.println("[without] counter & k: " + counter + ", " + k);
                        map.put(c, 1);
                        counter++;
                    } else { System.out.println("[without] map before: "); System.out.println(map);
                        while (left <= right && counter == k) {
                            char leftChar = s.charAt(left);
                            int leftCharCounter = map.get(leftChar);
                            map.put(leftChar, leftCharCounter - 1);
                            if (leftCharCounter == 1) { // pass all left char
                                counter--;
                            }
                            left++;
                        }

                        map.put(c, 1); System.out.println("map after: "); System.out.println(map);
                        counter++; //System.out.println("counter after: " + counter);
                    }
                }

                maxLen = updateMaxLen(maxLen);
                right++;
            }

            maxLen = updateMaxLen(maxLen);

            return maxLen;
        }
    }

    private int updateMaxLen(int maxLen) {
        int tmpLen = getLen();
        if (tmpLen > maxLen) {
            maxLen = tmpLen;
        }
        return maxLen;
    }

    private int getLen() {
        int len = 0;
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            int val = entry.getValue();
            if (val > 0) {
                len += val;
            }
        }

        return len;
    }

    // "aba",1 => 1 ("a")
    // "eceba",2 => 3 ("ece")
    // "eceeeecba",2 => 7 ("eceeeec")
    // "abacd",3 => 4 ("abac")
}
