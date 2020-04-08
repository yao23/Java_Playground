import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

public class AllOOneDataStructure { // 432
    /**
     * Runtime: 17 ms, faster than 64.23% of Java online submissions for All O`one Data Structure.
     * Memory Usage: 45.5 MB, less than 42.86% of Java online submissions for All O`one Data Structure.
     */
    class AllOne {
        TreeMap<Integer, Set<String>> treemap;
        HashMap<String, Integer> map ;
        /** Initialize your data structure here. */
        public AllOne() {
            map = new HashMap();
            treemap = new TreeMap();
        }

        /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
        public void inc(String key) {
            int newfreq = 1;
            Integer val = map.get(key);
            if(val!=null){
                treemap.get(val).remove(key);
                if(treemap.get(val).size()==0) treemap.remove(val);
                map.put(key, ++val);
                newfreq = val;
            }
            else{
                map.put(key, 1);
            }
            treemap.putIfAbsent(newfreq, new HashSet());
            treemap.get(newfreq).add(key);
        }

        /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
        public void dec(String key) {
            Integer val = map.get(key);
            if(val==null) return ;
            treemap.get(val).remove(key);
            if(treemap.get(val).size()==0) treemap.remove(val);
            val--;
            if(val==0){
                map.remove(key);
            }
            else{
                map.put(key, val);
                treemap.putIfAbsent(val, new HashSet());
                treemap.get(val).add(key);
            }
        }

        /** Returns one of the keys with maximal value. */
        public String getMaxKey() {
            if(treemap.size()==0) return "";
            Set<String> l = treemap.get(treemap.lastKey());
            return l.isEmpty() ? "" : l.iterator().next();
        }

        /** Returns one of the keys with Minimal value. */
        public String getMinKey() {
            if(treemap.size()==0) return "";
            Set<String> l = treemap.get(treemap.firstKey());
            return l.isEmpty() ? "" : l.iterator().next();
        }
    }
}

/**
 * Implement a data structure supporting the following operations:
 *
 * Inc(Key) - Inserts a new key with value 1. Or increments an existing key by 1. Key is guaranteed to be a non-empty string.
 * Dec(Key) - If Key's value is 1, remove it from the data structure. Otherwise decrements an existing key by 1.
 *            If the key does not exist, this function does nothing. Key is guaranteed to be a non-empty string.
 * GetMaxKey() - Returns one of the keys with maximal value. If no element exists, return an empty string "".
 * GetMinKey() - Returns one of the keys with minimal value. If no element exists, return an empty string "".
 * Challenge: Perform all these in O(1) time complexity.
 *
 *
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */