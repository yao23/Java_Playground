package com.leetcode.www;

import java.util.*;

public class ReconstructItinerary { // LC 332
    /**
     * Runtime: 38 ms, faster than 11.69% of Java online submissions for Reconstruct Itinerary.
     * Memory Usage: 42.9 MB, less than 66.82% of Java online submissions for Reconstruct Itinerary.
     *
     * @param tickets
     * @return
     */
    public List<String> findItinerary(List<List<String>> tickets) { // iterative
        Map<String, PriorityQueue<String>> targets = new HashMap<>();
        for (List<String>ticket : tickets) {
            targets.computeIfAbsent(ticket.get(0), k -> new PriorityQueue<>()).add(ticket.get(1));
        }
        List<String> route = new LinkedList<>();
        Deque<String> stack = new ArrayDeque<>();
        stack.push("JFK");
        while (!stack.isEmpty()) {
            while (targets.containsKey(stack.peek()) && !targets.get(stack.peek()).isEmpty()) {
                stack.push(targets.get(stack.peek()).poll());
            }
            route.add(0, stack.pop());
        }
        return route;
    }

    public List<String> findItinerary(String[][] tickets) { // beats 15.60%
        Map<String, PriorityQueue<String>> targets = new HashMap<>();
        for (String[] ticket : tickets) {
            targets.computeIfAbsent(ticket[0], k -> new PriorityQueue<>()).add(ticket[1]);
        }
        List<String> route = new LinkedList<>();
        Deque<String> stack = new ArrayDeque<>();
        stack.push("JFK");
        while (!stack.isEmpty()) {
            while (targets.containsKey(stack.peek()) && !targets.get(stack.peek()).isEmpty()) {
                stack.push(targets.get(stack.peek()).poll());
            }
            route.add(0, stack.pop());
        }
        return route;
    }

    /**
     * Runtime: 37 ms, faster than 13.84% of Java online submissions for Reconstruct Itinerary.
     * Memory Usage: 40.3 MB, less than 86.66% of Java online submissions for Reconstruct Itinerary.
     *
     * @param tickets
     * @return
     */
    public List<String> findItineraryV1(List<List<String>> tickets) { // recursion
        for (List<String>ticket : tickets) {
            targets.computeIfAbsent(ticket.get(0), k -> new PriorityQueue<>()).add(ticket.get(1));
        }
        visit("JFK");
        return route;
    }

    public List<String> findItineraryV1(String[][] tickets) { // recursion
        for (String[] ticket : tickets) {
            targets.computeIfAbsent(ticket[0], k -> new PriorityQueue<>()).add(ticket[1]);
        }
        visit("JFK");
        return route;
    }

    private Map<String, PriorityQueue<String>> targets = new HashMap<>();
    private List<String> route = new LinkedList<>();

    private void visit(String airport) {
        while(targets.containsKey(airport) && !targets.get(airport).isEmpty()) {
            visit(targets.get(airport).poll());
        }
        route.add(0, airport);
    }

    public List<String> findItineraryV0(String[][] tickets) { // not working for test case 3
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

        String start = "JFK";
        res.add(start);
        while (res.size() < len + 1) {
            PriorityQueue<String> pq = map.get(start);
            String end = pq.peek();
            pq.poll();
            res.add(end);
            start = end;
        }
        return res;
    }
}

// [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]] => ["JFK", "MUC", "LHR", "SFO", "SJC"]
// [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]] => ["JFK","ATL","JFK","SFO","ATL","SFO"]
// [["JFK","KUL"],["JFK","NRT"],["NRT","JFK"]] => ["JFK","NRT","JFK","KUL"]