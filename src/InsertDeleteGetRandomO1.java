/**
 * Created by liyao on 6/12/17.
 */
import java.util.HashSet;
import java.util.Set;
import java.util.Random;

public class InsertDeleteGetRandomO1 {
    public class RandomizedSet {
        Set<Integer> set;

        /** Initialize your data structure here. */
        public RandomizedSet() {
            set = new HashSet<>();
        }

        /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
        public boolean insert(int val) {
            return set.add(val);
        }

        /** Removes a value from the set. Returns true if the set contained the specified element. */
        public boolean remove(int val) {
            return set.remove(val);
        }

        /** Get a random element from the set. */
        public int getRandom() {
            Random random = new Random();
            int randomIdx = random.nextInt(set.size());
            Integer[] arr = set.toArray(new Integer[set.size()]);
            return arr[randomIdx];
        }
    }

    /**
     * Your RandomizedSet object will be instantiated and called as such:
     * RandomizedSet obj = new RandomizedSet();
     * boolean param_1 = obj.insert(val);
     * boolean param_2 = obj.remove(val);
     * int param_3 = obj.getRandom();
     */
}