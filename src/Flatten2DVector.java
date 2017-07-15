/**
 * Created by liyao on 7/13/17.
 */
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class Flatten2DVector { // use 2 iterators to access outer and inner list
    Iterator<List<Integer>> outer;
    Iterator<Integer> inner;

    public Flatten2DVector(List<List<Integer>> arr) {
        outer = arr.iterator();
    }

    public int next() {
        hasNext();
        return inner.next();
    }

    public boolean hasNext() {
        while ((inner == null || !inner.hasNext()) && outer.hasNext()) { // has next in list
            inner = outer.next().iterator();
        }

        return (inner != null && inner.hasNext());
    }
}

public class Flatten2DVectorV2 { // use a 1D list to hold all elements
    private List<Integer> list;
    private int index;
    private int size;

    public Flatten2DVectorV2(List<List<Integer>> arr) {
        index = 0;
        size = 0;
        for (List<Integer> l : arr) {
            list.addAll(l);
            size += l.size();
        }
    }

    public int next() {
        if (hasNext()) {
            int result = list.get(index);
            index++;
            return result;
        } else {
            return Integer.MIN_VALUE;
        }
    }

    public boolean hasNext() {
        return (index < size - 1);
    }
}

class Flatten2DVectorV1 {
    List<List<Integer>> arr;
    List<Integer> cur;

    public Flatten2DVectorV1(List<List<Integer>> arr) {
        this.arr = arr;
        if (arr.size() > 0) {
            cur = this.arr.get(0);
        } else {
            cur = new ArrayList<>();
        }
    }

    public int next() {
        if (hasNext()) {
            if (cur.iterator().hasNext()) { // cur inner list has next element
                return cur.iterator().next();
            } else { // cur inner list has no next element
                cur = arr.iterator().next();
                return cur.iterator().next();
            }
        } else {
            return Integer.MIN_VALUE; // no next value
        }
    }

    public boolean hasNext() {
        if (!cur.iterator().hasNext() && !arr.iterator().hasNext()) { // last element in last list
            return false;
        } else {
            return true;
        }
    }
}

class Flatten2DVectorV0 {
    private int outerPtr;
    private int innerPtr;

    private int outerPtrMax;

    private List<List<Integer>> arr;

    public Flatten2DVectorV0(List<List<Integer>> arr) {
        this.outerPtr = 0;
        this.innerPtr = 0;
        this.outerPtrMax = arr.size();
        this.arr = arr;

        getOuterList();
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
                    getOuterList();
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

    private void getOuterList() {
        while (arr.get(outerPtr).size() == 0 && outerPtr < outerPtrMax - 1) {
            outerPtr++;
        }
    }
}
