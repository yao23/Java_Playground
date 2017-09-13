/**
 * Created by liyao on 6/11/17.
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MissingRanges {
    /**
     *  Given a sorted integer array where the range of elements are [0, 99] inclusive, return its missing ranges.
     *  For example, given [0, 1, 3, 50, 75], return [“2”, “4->49”, “51->74”, “76->99”]
     */
    public List<String> findMissingRanges(int[] nums, int lo, int hi) {
        List<String> res = new ArrayList<>();
        long lower = (long)lo;
        long upper = (long)hi;
        for (int num : nums) {
            long justBelow = (long)num - 1;
            if (justBelow == lower) {
                res.add(String.valueOf(justBelow));
            } else if (justBelow > lower) {
                res.add(lower + "->" + justBelow);
            }
            if (num == Integer.MAX_VALUE) {
                return res;
            }
            lower = (long)num + 1;
        }
        if (lower == upper) {
            res.add(String.valueOf(upper));
        } else if (lower < upper) {
            res.add(lower + "->" + upper);
        }
        return res;
    }

    public List<String> findMissingRangesV1(int[] a, int lo, int hi) {
        List<String> res = new ArrayList<String>();

        // the next number we need to find
        int next = lo;

        for (int i = 0; i < a.length; i++) {
            // not within the range yet
            if (a[i] < next) {
                continue;
            }

            // continue to find the next one
            if (a[i] == next) {
                next++;
                continue;
            }

            // get the missing range string format
            res.add(getRange(next, a[i] - 1));

            // now we need to find the next number
            next = a[i] + 1;
        }

        // do a final check
        if (next <= hi) {
            res.add(getRange(next, hi));
        }

        return res;
    }

    private String getRange(int n1, int n2) {
        return (n1 == n2) ? String.valueOf(n1) : String.format("%d->%d", n1, n2);
    }

    public List<String> findMissingRangesV0(int[] nums, int start, int end) {
        List<String> result = new ArrayList<>();
        int len = nums.length;
        if (len == 0) {
            if (start == end) {
                result.add(Integer.toString(end)); // test case 2
            } else {
                result.add(start + "->" + end);
            }
            return result;
        } else if (len == 1) {
            int elem = nums[0];
            if (start == elem && end == elem) { // test case 3
                return result;
            } else if (start != elem && elem != end) {
                if (start < elem && elem < end) {
                    if (start + 1 == elem) {
                        result.add(Integer.toString(start));
                    } else {
                        result.add(start + "->" + (elem - 1));
                    }
                    if (elem + 1 == end) {
                        result.add(Integer.toString(end));
                    } else {
                        result.add((elem + 1) + "->" + end);
                    }
                } else { // elem < start || elem > end
                    if (start == end) {
                        result.add(Integer.toString(end));
                    } else {
                        result.add(start + "->" + end);
                    }
                }
            } else { // start == elem || end == elem
                if (start == elem) {
                    if (start + 1 == end) {
                        result.add(Integer.toString(end));
                    } else {
                        result.add((start + 1) + "->" + end);
                    }
                } else { // end == elem
                    if (start + 1 == end) {
                        result.add(Integer.toString(start));
                    } else {
                        result.add(start + "->" + (end - 1));
                    }
                }
            }
            return result;
        } else {
            Arrays.sort(nums);

            for (int i = 1; i < len; i++) {
                int pre = nums[i-1], cur = nums[i];
                if (pre + 1 == cur) { // consecutive
                    if (start >= pre) {
                        continue;
                    } else {
                        if (start + 1 == pre) { // test case 6
                            result.add(Integer.toString(start));
                        } else {
                            result.add(start + "->" + (pre - 1));
                        }
                    }
                } else { // not consecutive
                    int newStart = pre + 1, newEnd = cur -1;
                    if (newEnd < start || newStart > end) {
                        continue;
                    } else { // in the target range
                        if (newStart == newEnd) {
                            if (start < pre) {
                                if (i > 1 && start == nums[i - 2]) {

                                } else {
                                    if (start + 1 == pre) {
                                        result.add(Integer.toString(start));
                                    } else {
                                        result.add(start + "->" + (pre - 1));
                                    }
                                }
                            }
                            result.add(Integer.toString(newStart));
                        } else {
                            result.add(newStart + "->" + newEnd);
                        }
                    }
                }
            }

            if (nums[len-1] < end) {
                int newStart = nums[len-1] + 1, newEnd = end;
                if (newStart == newEnd) {
                    result.add(Integer.toString(newStart));
                } else {
                    result.add(newStart + "->" + newEnd);
                }
            }

            return result;
        }
    }

    // [],0,99 => ["0->99"]
    // [],1,1 => ["1"]
    // [-1],-1,-1 => []
    // [1],0,99 => ["0","2->99"]
    // [0, 1, 3, 50, 75],0,99 => [“2”, “4->49”, “51->74”, “76->99”]
    // [1,2],0,9 => ["0","3->9"]
    // [1,3],0,9 => ["0","2","4->9"]
}
