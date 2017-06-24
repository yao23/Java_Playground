/**
 * Created by liyao on 6/22/17.
 */

import java.util.*;

public class CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int row = prerequisites.length;
        if (row <= 1) {
            return true;
        } else {
            Set<Integer> zeroDegreeElement = new HashSet<>();
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

            // System.out.println("num - degree: " + elementDegrees);
            // System.out.println("num - neighbors: " + elementNeighbors);

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

            // System.out.println("zero degree element: " + zeroDegreeElement);

            return (numCourses == zeroDegreeElement.size());
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
                        zeroDegreeNeighbors.add(neighbor);
                    }
                }
            }
        }
    }

    private void processNeighborsV0(Queue<Integer> zeroDegreeNeighbors, Map<Integer, Integer> elementDegrees, Map<Integer, Set<Integer>> elementNeighbors, Set<Integer> zeroDegreeElement) {
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
                    if (!elementDegrees.containsKey(neighbor)) {
                        System.out.println("map has no key: " + neighbor);
                        continue;
                    }
                    int inDegree = elementDegrees.get(neighbor);
                    elementDegrees.put(neighbor, inDegree - 1);
                    if (inDegree > 1) {
                        continue;
                    } else {
                        zeroDegreeElement.add(neighbor);
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

    public boolean canFinishV0(int numCourses, int[][] prerequisites) {
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

//            sortedMap.putAll(elementDegrees);
            elementDegrees = sortByValue(elementDegrees);

            for (Map.Entry<Integer, Integer> elementDegree : elementDegrees.entrySet()) {
                if (elementDegree.getValue() == 0) { // in-degree is 0
                    int element = elementDegree.getKey();
                    System.out.println("zero degree num: " + element);
                    zeroDegreeElement.add(element);
                    zeroDegreeNeighbors.add(element);
                    processNeighbors(zeroDegreeNeighbors, elementDegrees, elementNeighbors, zeroDegreeElement);
                } else { // in-degree is larger than 0
                    continue;
                }
            }
            System.out.println(numCourses + ", " + zeroDegreeElement.size());
            for (Integer n : zeroDegreeElement) {
                System.out.print(n + " ");
            }
            int num = zeroDegreeElement.size();
            return (num > 0 && numCourses >= num);
        }
    }

    private static <K, V extends Comparable<? super V>> Map<K, V> sortByValue( Map<K, V> map ) {
        List<Map.Entry<K, V>> list =
                new LinkedList<>( map.entrySet() );
        Collections.sort( list, new Comparator<Map.Entry<K, V>>() {
            @Override
            public int compare( Map.Entry<K, V> o1, Map.Entry<K, V> o2 ) {
                return ( o1.getValue() ).compareTo( o2.getValue() );
            }
        } );

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put( entry.getKey(), entry.getValue() );
        }
        return result;
    }

    // 2, [[1,0]] => true
    // 2, [[0,1],[1,0]] => false
    // 3, [[1,0],[2,1]] => true
    // 3, [[2,0],[2,1]] => true
    // 3, [[0,1],[0,2],[1,2]] => true
    // 4, [[3,0],[0,1]] => true
    // 4, [[1,0],[2,1],[3,2],[1,3]] => false
    // 4, [[0,1],[1,2],[0,3],[3,0]] => false
    // 6, [[1,0],[2,0],[3,0],[4,1],[4,2],[4,3],[5,2],[5,3]] => true // Line 39: java.lang.NullPointerException, fixed by null check
}
