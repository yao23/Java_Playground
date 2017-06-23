/**
 * Created by liyao on 6/22/17.
 */

import java.util.*;

public class CourseSchedule {
    private void addDegree(Map<Integer, Integer> elementDegrees, int element, int mode) {
        if (mode == 0) { // add degree for 1st num
            if (elementDegrees.containsKey(element)) { // added before
                return;
            } else {
                elementDegrees.put(element, 0);
            }
        } else { // add degree for 2nd num
            if (elementDegrees.containsKey(element)) {
                int degree = elementDegrees.get(element);
                elementDegrees.put(element, degree + 1);
            } else {
                elementDegrees.put(element, 1);
            }
        }
    }

    private void addNeighbor(Map<Integer, Set<Integer>> elementNeighbors, int element, int neighbor) {
        if (elementNeighbors.containsKey(element)) {
            elementNeighbors.get(element).add(neighbor);
        } else {
            Set<Integer> set = new HashSet<>();
            set.add(neighbor);
            elementNeighbors.put(element, set);
        }
    }

    private void processNeighbors(Queue<Integer> zeroDegreeNeighbors, Map<Integer, Integer> elementDegrees, Map<Integer, Set<Integer>> elementNeighbors) {
        while (!zeroDegreeNeighbors.isEmpty()) {
            int cur = zeroDegreeNeighbors.poll();
            Set<Integer> neighbors = elementNeighbors.get(cur);
            for (Integer neighbor : neighbors) {
                zeroDegreeNeighbors.add(neighbor);
                int inDegree = elementDegrees.get(neighbor);
                elementDegrees.put(neighbor, inDegree - 1);
            }
        }
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int row = prerequisites.length;
        if (row <= 1) {
            return true;
        } else {
            List<Integer> zeroDegreeElement = new ArrayList<>();
            Queue<Integer> zeroDegreeNeighbors = new ArrayDeque<>();
            Map<Integer, Integer> elementDegrees = new HashMap<>();
            Map<Integer, Set<Integer>> elementNeighbors = new HashMap<>();

            for (int i = 0; i < row; i++) {
                int first = prerequisites[i][1], second = prerequisites[i][0];
                addNeighbor(elementNeighbors, first, second);
                addDegree(elementDegrees, first, 0); // 1st num in-degree
                addDegree(elementDegrees, second, 1); // 2nd num in-degree
            }

            for (Map.Entry<Integer, Integer> elementDegree : elementDegrees.entrySet()) {
                if (elementDegree.getValue() == 0) { // in-degree is 0
                    zeroDegreeNeighbors.add(elementDegree.getKey());
                    processNeighbors(zeroDegreeNeighbors, elementDegrees, elementNeighbors);
                } else { // in-degree is larger than 0
                    continue;
                }
            }

            return (numCourses >= zeroDegreeElement.size());
        }
    }
}
