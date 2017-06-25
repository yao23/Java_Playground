/**
 * Created by liyao on 6/24/17.
 */
import java.util.*;

public class CourseScheduleII {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int row = prerequisites.length;
        if (row <= 1) {
            if (row == 0) {
                if (numCourses == 0) {
                    return (new int[]{});
                } else {
                    int[] result = new int[numCourses];
                    for (int i = 0; i < numCourses; i++) {
                        result[i] = i;
                    }
                    return result;
                }
            } else {
                if (numCourses == 0) {
                    return (new int[]{});
                } else if (numCourses == 1) {
                    return (new int[]{prerequisites[0][1]});
                } else {
                    int[] result = new int[numCourses];
                    result[numCourses - 1] = prerequisites[0][0];
                    result[numCourses - 2] = prerequisites[0][1];

                    int idx = 0; // index for courses not in prerequisites array
                    for (int i = 0; i < numCourses - 2; i++) {
                        while (idx == prerequisites[0][0] || idx == prerequisites[0][1]) {
                            idx++;
                        }
                        result[i] = idx++;
                    }
                    return result;
                }
            }
        } else {
            Set<Integer> zeroDegreeElement = new LinkedHashSet<>();
            Queue<Integer> zeroDegreeNeighbors = new ArrayDeque<>();
            Map<Integer, Integer> elementDegrees = new HashMap<>();
            Map<Integer, Set<Integer>> elementNeighbors = new HashMap<>();

            for (int i = 0; i < numCourses; i++) { // some course info has not been provided (default in-degree as 0) as 2 in test case 6
                elementDegrees.put(i, 0);
            }

            for (int i = 0; i < row; i++) {
                int first = prerequisites[i][1], second = prerequisites[i][0];
                addNeighbor(elementNeighbors, first, second);
                addDegree(elementDegrees, first, 0); // 1st num in-degree
                addDegree(elementDegrees, second, 1); // 2nd num in-degree
            }

            for (Map.Entry<Integer, Integer> elementDegree : elementDegrees.entrySet()) {
                if (elementDegree.getValue() == 0) { // in-degree is 0
                    int element = elementDegree.getKey();
                    zeroDegreeElement.add(element);
                    zeroDegreeNeighbors.add(element);
                } else { // in-degree is larger than 0
                    continue;
                }
            }

            processNeighbors(zeroDegreeNeighbors, elementDegrees, elementNeighbors, zeroDegreeElement);

            if (numCourses == zeroDegreeElement.size()) {
                int[] result = new int[numCourses];
                int i = 0;
                for (Integer element : zeroDegreeElement) {
                    result[i] = element;
                    i++;
                }
                return result;
            } else {
                return (new int[]{});
            }
        }
    }

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

    private void processNeighbors(Queue<Integer> zeroDegreeNeighbors, Map<Integer, Integer> elementDegrees, Map<Integer, Set<Integer>> elementNeighbors, Set<Integer> zeroDegreeElement) {
        while (!zeroDegreeNeighbors.isEmpty()) {
            int cur = zeroDegreeNeighbors.poll();
            Set<Integer> neighbors = elementNeighbors.get(cur);
            if (neighbors == null) {
                continue;
            } else {
                for (Integer neighbor : neighbors) {
                    int inDegree = elementDegrees.get(neighbor);
                    elementDegrees.put(neighbor, inDegree - 1);
                    if (inDegree > 1) {
                        continue;
                    } else { // next zero degree element
                        zeroDegreeElement.add(neighbor);
                        zeroDegreeNeighbors.add(neighbor); // add to queue for next step processing
                    }
                }
            }
        }
    }

    // 1, [] => [0]
    // 2, [] => [1,0]
    // 2, [[1,0]] => [0,1]
    // 2, [[0,1],[1,0]] => []
    // 3, [[1,0],[2,1]] => [0,1,2]
    // 3, [[2,0],[2,1]] => [0,1,2] / [1,0,2]
    // 3, [[0,1],[0,2],[1,2]] => [2,1,0]
    // 4, [[3,0],[0,1]] => [1,2,0,3] / [2,1,0,3]
    // 4, [[1,0],[2,1],[3,2],[1,3]] => []
    // 4, [[0,1],[1,2],[0,3],[3,0]] => []
    // 4, [[1,0],[2,0],[3,1],[3,2]] => [0,1,2,3] / [0,2,1,3]
    // 6, [[1,0],[2,0],[3,0],[4,1],[4,2],[4,3],[5,2],[5,3]] => [0,1,2,3,4,5] / [0,3,2,5,1,4]

    // beats 38.28%
}
