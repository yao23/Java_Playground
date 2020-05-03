public class BullsAndCows { // 299 [Google]
    /**
     * Runtime: 16 ms, faster than 10.00% of Java online submissions for Bulls and Cows.
     * Memory Usage: 40 MB, less than 5.26% of Java online submissions for Bulls and Cows.
     *
     * @param secret
     * @param guess
     * @return
     */
    public String getHint(String secret, String guess) {
        if (secret == null || guess == null) {
            return "0A0B";
        }
        int len = secret.length(), countA = 0, countB = 0;
        int[] count = new int[10];
        for (int i = 0; i < len; i++) {
            if (secret.charAt(i) == guess.charAt(i)) {
                countA++;
            } else {
                int chS = secret.charAt(i) - '0', chG = guess.charAt(i) - '0';
                if (count[chS] < 0) {
                    countB++;
                }
                count[chS]++;
                if (count[chG] > 0) {
                    countB++;
                }
                count[chG]--;
            }
        }
        return countA + "A" + countB + "B";
    }
}
