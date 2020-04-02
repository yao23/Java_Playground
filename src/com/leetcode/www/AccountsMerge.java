import java.util.*;

public class AccountsMerge { // 721
    /**
     * Runtime: 33 ms, faster than 71.47% of Java online submissions for Accounts Merge.
     * Memory Usage: 44.3 MB, less than 100.00% of Java online submissions for Accounts Merge.
     *
     * Time Complexity: O(∑a_i * loga_i), where a_i is the length of accounts[i].
     * Without the log factor, this is the complexity to build the graph and search for each component.
     * The log factor is for sorting each component at the end.
     *
     * Space Complexity: O(∑ai), the space used by our graph and our search.
     *
     * @param accounts
     * @return
     */
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, String> emailToName = new HashMap<>();
        Map<String, ArrayList<String>> graph = new HashMap<>();
        for (List<String> account: accounts) {
            String name = "";
            for (String email: account) {
                if (name.equals("")) { // account 1st element is name
                    name = email;
                    continue;
                }
                graph.computeIfAbsent(email, x-> new ArrayList<>()).add(account.get(1));
                graph.computeIfAbsent(account.get(1), x-> new ArrayList<>()).add(email);
                emailToName.put(email, name);
            }
        }

        Set<String> seen = new HashSet<>();
        List<List<String>> ans = new ArrayList<>();
        for (String email: graph.keySet()) {
            if (!seen.contains(email)) {
                seen.add(email);
                Stack<String> stack = new Stack<>();
                stack.push(email);
                List<String> component = new ArrayList<>();
                while (!stack.empty()) {
                    String node = stack.pop();
                    component.add(node);
                    for (String nei: graph.get(node)) {
                        if (!seen.contains(nei)) {
                            seen.add(nei);
                            stack.push(nei);
                        }
                    }
                }
                Collections.sort(component);
                component.add(0, emailToName.get(email));
                ans.add(component);
            }
        }
        return ans;
    }
}

// Input:
// accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"],
// ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
// Output: [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],
// ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
// Explanation:
// The first and third John's are the same person as they have the common email "johnsmith@mail.com".
// The second John and Mary are different people as none of their email addresses are used by other accounts.
// We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'],
// ['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
