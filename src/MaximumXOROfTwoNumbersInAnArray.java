import java.util.HashSet;
import java.util.Set;

public class MaximumXOROfTwoNumbersInAnArray { // LC 421
    public int findMaximumXOR(int[] nums) { // beats 81.20%
        int max = 0, mask = 0;
        for (int i = 31; i >= 0; i--) {
            mask = mask | (1 << i);
            Set<Integer> set = new HashSet<>();
            for (int num : nums) {
                set.add(num & mask);
            }
            int tmp = max | (1 << i);
            for (int prefix : set) {
                if (set.contains(tmp ^ prefix)) {
                    max = tmp;
                    break;
                }
            }
        }
        return max;
    }
}

// [3, 10, 5, 25, 2, 8] => 28 (5 ^ 25)

