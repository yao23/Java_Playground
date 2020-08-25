import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OptimalAccountBalancing { // 465 [Google]
    class Solution {
        /**
         * Recursive
         *
         * @param transactions
         * @return
         */
        public int minTransfers(int[][] transactions) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int[] t : transactions) {
                map.put(t[0], map.getOrDefault(t[0], 0) - t[2]);
                map.put(t[1], map.getOrDefault(t[1], 0) + t[2]);
            }
            return balance(new ArrayList<>(map.values()), 0);
        }

        private int balance(List<Integer> list, int start) {
            if (start == list.size() - 2) return list.get(list.size() - 2) == 0 ? 0 : 1;
            if (list.get(start) == 0) return balance(list, start + 1);
            int res = Integer.MAX_VALUE;
            for (int i = start + 1; i < list.size(); i++) {
                int balance = list.get(i);
                if (balance * list.get(start) < 0) {
                    list.set(i, balance + list.get(start));
                    res = Math.min(res, 1 + balance(list, start + 1));
                    list.set(i, balance);
                }
            }
            return res;
        }
    }

    /**
     * Time : O(n!), Space : O(n) for recursion call stack
     *
     * @param transactions
     * @return
     */
    public int minTransfers(int[][] transactions) {
        if (transactions == null || transactions.length == 0) return -1;

        int len = transactions.length;

        Map<Integer, Integer> balance = new HashMap<>();
        for (int[] tx : transactions) {
            int from = tx[0];
            int to = tx[1];
            int money = tx[2];

            balance.merge(from, -money, Integer::sum);
            balance.merge(to, money, Integer::sum);
        }
        List<Integer> balances =
                balance.values().stream().filter(i -> i != 0).collect(Collectors.toList());

        int[] min = new int[1];
        min[0] = Integer.MAX_VALUE;
        dfs(balances, 0, 0, min);

        return min[0];
    }

    private void dfs(List<Integer> balances, int settled, int from, int[] min) {
        int n = balances.size();

        while (from < n && balances.get(from) == 0) from++;
        if (from == n) {
            min[0] = Math.min(settled, min[0]);
            return;
        }

        for (int to = from + 1; to < n; to++) {
            if (balances.get(to) * balances.get(from) < 0) {
                balances.set(to, balances.get(to) + balances.get(from));
                dfs(balances, settled + 1, from + 1, min);
                balances.set(to, balances.get(to) - balances.get(from));
            }
        }
    }
}

/**
 A group of friends went on holiday and sometimes lent each other money. For example, Alice paid for Bill's lunch for $10.
 Then later Chris gave Alice $5 for a taxi ride. We can model each transaction as a tuple (x, y, z) which means person x
 gave person y $z. Assuming Alice, Bill, and Chris are person 0, 1, and 2 respectively (0, 1, 2 are the person's ID),
 the transactions can be represented as [[0, 1, 10], [2, 0, 5]].

 Given a list of transactions between a group of people, return the minimum number of transactions required to settle the debt.

 Note:

 A transaction will be given as a tuple (x, y, z). Note that x ≠ y and z > 0.
 Person's IDs may not be linear, e.g. we could have the persons 0, 1, 2 or we could also have the persons 0, 2, 6.
 Example 1:

 Input:
 [[0,1,10], [2,0,5]]

 Output:
 2

 Explanation:
 Person #0 gave person #1 $10.
 Person #2 gave person #0 $5.

 Two transactions are needed. One way to settle the debt is person #1 pays person #0 and #2 $5 each.
 Example 2:

 Input:
 [[0,1,10], [1,0,1], [1,2,5], [2,0,5]]

 Output:
 1

 Explanation:
 Person #0 gave person #1 $10.
 Person #1 gave person #0 $1.
 Person #1 gave person #2 $5.
 Person #2 gave person #0 $5.

 Therefore, person #1 only need to give person #0 $4, and all debt is settled.
*/