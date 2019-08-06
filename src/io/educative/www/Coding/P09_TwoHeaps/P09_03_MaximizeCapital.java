package io.educative.www.Coding.P09_TwoHeaps;

import java.util.*;

public class P09_03_MaximizeCapital {
    /**
     * Time complexity
     * Since, at the most, all the projects will be pushed to both the heaps once, the time complexity of our algorithm
     * is O(NlogN + KlogN), where ‘N’ is the total number of projects and ‘K’ is the number of projects we are selecting.
     *
     * Space complexity
     * The space complexity will be O(N) because we will be storing all the projects in the heaps.
     *
     * @param capital
     * @param profits
     * @param numberOfProjects
     * @param initialCapital
     * @return
     */
    public static int findMaximumCapital(int[] capital, int[] profits, int numberOfProjects, int initialCapital) {
        int n = profits.length;
        PriorityQueue<Integer> minCapitalHeap = new PriorityQueue<>(n, (i1, i2) -> capital[i1] - capital[i2]);
        PriorityQueue<Integer> maxProfitHeap = new PriorityQueue<>(n, (i1, i2) -> profits[i2] - profits[i1]);

        // insert all project capitals to a min-heap
        for (int i = 0; i < n; i++)
            minCapitalHeap.offer(i);

        // let's try to find a total of 'numberOfProjects' best projects
        int availableCapital = initialCapital;
        for (int i = 0; i < numberOfProjects; i++) {
            // find all projects that can be selected within the available capital and insert them in a max-heap
            while (!minCapitalHeap.isEmpty() && capital[minCapitalHeap.peek()] <= availableCapital)
                maxProfitHeap.add(minCapitalHeap.poll());

            // terminate if we are not able to find any project that can be completed within the available capital
            if (maxProfitHeap.isEmpty())
                break;

            // select the project with the maximum profit
            availableCapital += profits[maxProfitHeap.poll()];
        }

        return availableCapital;
    }

    public static void main(String[] args) {
        int result = P09_03_MaximizeCapital.findMaximumCapital(new int[] { 0, 1, 2 }, new int[] { 1, 2, 3 }, 2, 1);
        System.out.println("Maximum capital: " + result);
        result = P09_03_MaximizeCapital.findMaximumCapital(new int[] { 0, 1, 2, 3 }, new int[] { 1, 2, 3, 5 }, 3, 0);
        System.out.println("Maximum capital: " + result);
    }
}
