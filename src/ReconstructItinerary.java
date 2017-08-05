import java.util.*;

public class ReconstructItinerary { // LC 332
    public List<String> findItinerary(String[][] tickets) {
        // Priority Queue to store neighbors in lexical order
        // DFS
        // Eulerian Path
        List<String> res = new ArrayList<>();
        int len = tickets.length;
        if (len == 0) {
            return res;
        }
        Map<String, PriorityQueue<String>> map = new HashMap<>();
        for (String[] t : tickets) {
            String start = t[0];
            String end = t[1];
            if (!map.containsKey(start)) {
                map.put(start, new PriorityQueue<>());
            }
            map.get(start).offer(end);
        }
        for (Map.Entry<String, PriorityQueue<String>> entry : map.entrySet()) {
            System.out.println("key: " + entry.getKey());
            System.out.println(entry.getValue());
        }

        String start = "JFK";
        res.add(start);
        while (res.size() < len + 1) {
            PriorityQueue<String> pq = map.get(start);
            if (pq == null) {
                System.out.println("pq is null");
                // return res;
            } else {
                System.out.println("pq is not null: " + pq.peek());
                // return res;
            }
            String end = pq.peek();
            pq.poll();
            res.add(end);
            start = end;
        }
        return res;
    }
}
