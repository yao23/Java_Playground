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
            System.out.println("cur num: " + cur);
            Set<Integer> neighbors = elementNeighbors.get(cur);
            System.out.println("num: " + cur + ", neighbors: " + String.valueOf(neighbors == null));
            if (neighbors == null) {
                return;
            } else {
                for (Integer neighbor : neighbors) {
                    System.out.println("neighbor: " + neighbor);
                    zeroDegreeNeighbors.add(neighbor);
                    int inDegree = elementDegrees.get(neighbor);
                    if (inDegree > 0) {
                        elementDegrees.put(neighbor, inDegree - 1);
                    } else {
                        continue; // TODO: change result list to set and add neighbor
                    }
                    System.out.println("neighbor-degree: " + neighbor + ", " + elementDegrees.get(neighbor));
                }
            }
        }
    }

    private void printDegrees(Map<Integer, Integer> elementDegrees) {
        for (Map.Entry<Integer, Integer> elementDegree : elementDegrees.entrySet()) {
            System.out.println("num, degree: " + elementDegree.getKey() + ", " + elementDegree.getValue());
            System.out.println();
        }
        System.out.println();
    }

    private void printNeighbors(Map<Integer, Set<Integer>> elementNeighbors) {
        for (Map.Entry<Integer, Set<Integer>> elementNeighbor : elementNeighbors.entrySet()) {
            Set<Integer> set = elementNeighbor.getValue();
            System.out.println("num - neighbors: " + elementNeighbor.getKey());
            for (Integer n : set) {
                System.out.print(n + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int row = prerequisites.length;
        if (row <= 1) {
            return true;
        } else {
            Set<Integer> zeroDegreeElement = new HashSet<>();
            Queue<Integer> zeroDegreeNeighbors = new ArrayDeque<>();
            Map<Integer, Integer> elementDegrees = new HashMap<>();
            Map<Integer, Set<Integer>> elementNeighbors = new HashMap<>();

            for (int i = 0; i < row; i++) {
                int first = prerequisites[i][1], second = prerequisites[i][0];
                addNeighbor(elementNeighbors, first, second);
                addDegree(elementDegrees, first, 0); // 1st num in-degree
                addDegree(elementDegrees, second, 1); // 2nd num in-degree
            }

            printNeighbors(elementNeighbors);
            printDegrees(elementDegrees);

            for (Map.Entry<Integer, Integer> elementDegree : elementDegrees.entrySet()) {
                if (elementDegree.getValue() == 0) { // in-degree is 0
                    int element = elementDegree.getKey();
                    System.out.println("zero degree num: " + element);
                    zeroDegreeElement.add(element);
                    zeroDegreeNeighbors.add(element);
                    processNeighbors(zeroDegreeNeighbors, elementDegrees, elementNeighbors);
                } else { // in-degree is larger than 0
                    continue;
                }
            }
            System.out.println(numCourses + ", " + zeroDegreeElement.size());
            for (Integer n : zeroDegreeElement) {
                System.out.print(n + " ");
            }
            return (numCourses == zeroDegreeElement.size());
        }
    }

    // 2, [[1,0]] => true
    // 2, [[0,1],[1,0]] => false
    // 3, [[1,0],[2,1]] => true
    // 3, [[2,0],[2,1]] => true
    // 3, [[0,1],[0,2],[1,2]] =>
    // 6, [[1,0],[2,0],[3,0],[4,1],[4,2],[4,3],[5,2],[5,3]] => true // Line 39: java.lang.NullPointerException, fixed by null check
}
