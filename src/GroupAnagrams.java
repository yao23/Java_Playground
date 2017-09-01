/**
 * Created by liyao on 6/10/17.
 */
import java.util.*;

public class GroupAnagrams { // LC 49
    private String sortString(String str) {
        char[] charArray = str.toCharArray();
        Arrays.sort(charArray);
        return new String(charArray);
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        if (strs.length == 0) {
            return result;
        }
        Map<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            String sortedStr = sortString(str);
            List<String> strList = map.get(sortedStr);
            if (strList == null) { // no sortedStr as key in map
                strList = new ArrayList<String>();
                strList.add(str);
                map.put(sortedStr,strList);
            } else { // has key sortedStr in map
                strList.add(str);
            }
        }

        for (String key: map.keySet()) {
            result.add(map.get(key)); // add strLists into result
        }

        return result;
    }

    // [] => [[]]
    // ["eat", "tea", "tan", "ate", "nat", "bat"] => [["ate", "eat","tea"],["nat","tan"],["bat"]]

    // beats 36.66%
}