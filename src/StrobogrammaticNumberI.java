/**
 * Created by liyao on 6/13/17.
 */
public class StrobogrammaticNumberI {
    private boolean checkTwoNumbers(int a, int b) {
        if ((a == 6 && b == 9) || (a == 9 && b==6)) {
            return true;
        } else if (a == b && a == 0) {
            return true;
        } else if (a == b && a == 1) {
            return true;
        } else if (a == b && a == 8) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isStrobogrammatic(String num) {
        int len = num.length();
        if (len == 0) {
            return true;
        } else {
            int left = 0, right = len -1;

            while (left <= right) {
                int leftNum = Character.getNumericValue(num.charAt(left));
                int rightNum = Character.getNumericValue(num.charAt(right));
                if (checkTwoNumbers(leftNum, rightNum)) {
                    left++;
                    right--;
                } else {
                    return false;
                }
            }

            return true;
        }
    }

    // "" => true
    // "1" => true
    // "7" => false
    // "69" => true
    // "88" => true
    // "818" => true
}
