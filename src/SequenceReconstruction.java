import java.util.*;

public class SequenceReconstruction {
    public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {
        int len = seqs.size();
        if (len == 0) {
            return false;
        } else if (len == 1) {
            List<Integer> list = seqs.get(0);
            return validate(org, list);
        } else {
            List<Integer> res = new ArrayList<>();
            Map<Integer, Integer> degrees = new HashMap<>();
            Map<Integer, Set<Integer>> graph = new HashMap<>();
            for (List<Integer> list : seqs) {
                if (list.size() == 1) { // test case 5
                    updateDegreeForStart(degrees, list.get(0));
                } else {
                    for (int i = 0; i < list.size() - 1; i++) {
                        int start = list.get(i), end = list.get(i + 1);

                        if (updateGraph(graph, start, end)) { // edge from start to end is not existing (test case 4)
                            updateDegreeForStart(degrees, start);
                            updateDegreeForEnd(degrees, end);
                        }
                    }
                }
            }
            Deque<Integer> q = new ArrayDeque<>();
            for (Map.Entry<Integer, Integer> entry : degrees.entrySet()) {
                if (entry.getValue() == 0) {
                    int k = entry.getKey();
                    q.add(k);
                    res.add(k);
                }
            }

            while (!q.isEmpty()) {
                if (q.size() != 1) {
                    return false;
                } else {
                    int k = q.remove();
                    if (graph.get(k) != null) {
                        for (Integer elem : graph.get(k)) {
                            degrees.put(elem, degrees.get(elem) - 1);
                            if (degrees.get(elem) == 0) {
                                q.add(elem);
                                res.add(elem);
                            }
                        }
                    }
                }
            }

            if (res.size() == degrees.size()) {
                return validate(org, res);
            } else {
                return false; // test case 6
            }
        }
    }

    private void updateDegreeForStart(Map<Integer, Integer> degrees, int start) {
        if (!degrees.containsKey(start)) {
            degrees.put(start, 0);
        }
    }

    private void updateDegreeForEnd(Map<Integer, Integer> degrees, int end) {
        if (!degrees.containsKey(end)) {
            degrees.put(end, 0);
        }
        degrees.put(end, degrees.get(end) + 1);
    }

    private boolean updateGraph(Map<Integer, Set<Integer>> graph, int start, int end) {
        if (!graph.containsKey(start)) {
            graph.put(start, new HashSet<>());
        }
        return graph.get(start).add(end);
    }

    private boolean validate(int[] org, List<Integer> list) {
        if (org.length == list.size()) {
            for (int i = 0; i < org.length; i++) {
                if (org[i] != list.get(i)) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }
}

// orig: [1,2,3], seqs: [[1,2],[1,3]] => false ([1,3,2])
// orig: [1,2,3], seqs: [[1,2]] => false ([1,2])
// orig: [1,2,3], seqs: [[1,2],[1,3],[2,3]] => true
// orig: [4,1,5,2,6,3], seqs: [[5,2,6,3],[4,1,5,2]] => true
// orig: [1], seqs: [[1],[1],[1]] => true
// orig: [1], seqs: [[1],[2,3],[3,2]] => false