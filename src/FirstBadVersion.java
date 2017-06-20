/**
 * Created by liyao on 6/18/17.
 */
public class FirstBadVersion {
    private boolean isBadVersion(int version) {
        return false; // not correct, should be given in problem and temp return value to avoid error without return value
    }

    public int firstBadVersion(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            if (isBadVersion(n)) {
                return n;
            } else {
                return 0;
            }
        } else {
            int index = Integer.MAX_VALUE;
            int start = 1, end = n;

            while (start + 1 < end) {
                int mid = start + (end - start) / 2;
                if (isBadVersion(mid)) {
                    if (mid < index) {
                        index = mid;
                    }
                    end = mid;
                } else {
                    start = mid;
                }
            }

            if (isBadVersion(start)) {
                if (start < index) {
                    index = start;
                }
            }

            if (isBadVersion(end)) { // start + 1 = end
                if (end < index) {
                    index = end;
                }
            }

            return index;
        }
    }

    // beats 20.57%
}
