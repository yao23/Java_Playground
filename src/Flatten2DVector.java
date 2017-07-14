import java.util.List;

/**
 * Created by liyao on 7/13/17.
 */
public class Flatten2DVector {
    private int outerPtr;
    private int innerPtr;

    private int outerPtrMax;

    private List<List<Integer>> arr;

    public Flatten2DVector(List<List<Integer>> arr) {
        this.outerPtr = 0;
        this.innerPtr = 0;
        this.outerPtrMax = arr.size();
        this.arr = arr;
    }

    public int next() {
        if (hasNext()) {
            int innerLen = arr.get(outerPtr).size();
            int result = arr.get(outerPtr).get(innerPtr);
            if (innerPtr < innerLen - 1) {
                innerPtr++;
            } else {
                if (outerPtr < outerPtrMax - 1) {
                    outerPtr++;
                    innerPtr = 0;
                }
            }
            return result;
        } else {
            return -1; // invalid number
        }
    }

    public boolean hasNext() {
        if (outerPtr < outerPtrMax) {
            if (outerPtr == outerPtrMax - 1) {
                if (innerPtr == arr.get(outerPtr).size() - 1) {
                    return false;
                } else {
                    return true;
                }
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
}
