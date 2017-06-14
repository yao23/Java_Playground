/**
 * Created by liyao on 6/12/17.
 */
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class InsertDeleteGetRandomO1 {
    public class RandomizedSet {
        private Map<Integer, Integer> map; // store <inputElement, curIndex> for insert and remove in o(1)
        private Map<Integer, Integer> helperMap; // store <curIndex, inputElement> for getRandom in o(1)
        private int counter;

        /** Initialize your data structure here. */
        public RandomizedSet() {
            map = new HashMap<>();
            helperMap = new HashMap<>();
            counter = 0;
        }

        /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
        public boolean insert(int val) {
            if (map.containsKey(val)) {
                return false;
            } else {
                counter++;
                map.put(val,counter);
                helperMap.put(counter,val);
                return true;
            }
        }

        /** Removes a value from the set. Returns true if the set contained the specified element. */
        public boolean remove(int val) {
            if (map.containsKey(val)) {
                helperMap.remove(map.get(val));
                map.remove(val);
                counter--;
                return true;
            } else {
                return false;
            }
        }

        /** Get a random element from the set. */
        public int getRandom() {
            Random random = new Random();
            int randomIdx = random.nextInt(counter);
            return helperMap.get(randomIdx);
        }
    }

    /**
     * Your RandomizedSet object will be instantiated and called as such:
     * RandomizedSet obj = new RandomizedSet();
     * boolean param_1 = obj.insert(val);
     * boolean param_2 = obj.remove(val);
     * int param_3 = obj.getRandom();
     */

    // Init an empty set.
    //RandomizedSet randomSet = new RandomizedSet();

    // Inserts 1 to the set. Returns true as 1 was inserted successfully.
    //randomSet.insert(1);

    // Returns false as 2 does not exist in the set.
    //randomSet.remove(2);

    // Inserts 2 to the set, returns true. Set now contains [1,2].
    //randomSet.insert(2);

    // getRandom should return either 1 or 2 randomly.
    //randomSet.getRandom();

    // Removes 1 from the set, returns true. Set now contains [2].
    //randomSet.remove(1);

    // 2 was already in the set, so return false.
    //randomSet.insert(2);

    // Since 2 is the only number in the set, getRandom always return 2.
    //randomSet.getRandom();
}