package io.educative.www.Coding.P11_ModifiedBinarySearch;

public class P11_03_NextLetter {
    /**
     * Time complexity
     * Since, we are reducing the search range by half at every step, this means that the time complexity of our
     * algorithm will be O(logN) where ‘N’ is the total elements in the given array.
     *
     * Space complexity
     * The algorithm runs in constant space O(1).
     *
     * @param letters
     * @param key
     * @return
     */
    public static char searchNextLetter(char[] letters, char key) {
        int n = letters.length;
        if (key < letters[0] || key > letters[n - 1])
            return letters[0];

        int start = 0, end = n - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (key < letters[mid]) {
                end = mid - 1;
            } else { //if (key >= letters[mid]) {
                start = mid + 1;
            }
        }
        // since the loop is running until 'start <= end', so at the end of the while loop, 'start == end+1'
        return letters[start % n];
    }

    public static void main(String[] args) {
        System.out.println(P11_03_NextLetter.searchNextLetter(new char[] { 'a', 'c', 'f', 'h' }, 'f'));
        System.out.println(P11_03_NextLetter.searchNextLetter(new char[] { 'a', 'c', 'f', 'h' }, 'b'));
        System.out.println(P11_03_NextLetter.searchNextLetter(new char[] { 'a', 'c', 'f', 'h' }, 'm'));
        System.out.println(P11_03_NextLetter.searchNextLetter(new char[] { 'a', 'c', 'f', 'h' }, 'h'));
    }
}
