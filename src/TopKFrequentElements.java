/**
 * Created by liyao on 6/4/17.
 */
import java.util.*;

public class TopKFrequentElements {
    public List<Integer> topKFrequent(int[] nums, int k) {
        class NumOccrComparator implements Comparator<Map.Entry> {
            @Override
            public int compare(Map.Entry e1, Map.Entry e2) {
                int e1Val = (Integer) e1.getValue();
                int e2Val = (Integer) e2.getValue();
                if (e2Val > e1Val) {
                    return 1;
                } else if (e2Val < e1Val) {
                    return -1;
                } else {
                    return 0;
                }
//                return e2.getValue() - e1.getValue();
            }
        }

        HashMap<Integer, Integer> numOccrHashMap = new HashMap<Integer, Integer>();

        for (int num : nums) {
            if (numOccrHashMap.containsKey(num)) {
                numOccrHashMap.put(num, (numOccrHashMap.get(num)+1));
            } else {
                numOccrHashMap.put(num, 1);
            }
        }

        PriorityQueue<Map.Entry<Integer, Integer>> maxHeap =
                new PriorityQueue<>((a,b)->(b.getValue()-a.getValue()));
//        PriorityQueue<Map.Entry<Integer, Integer>> maxHeap = new PriorityQueue<Map.Entry<Integer, Integer>>((a,b)->(b.getValue()-a.getValue()));
        for(Map.Entry numEntry : numOccrHashMap.entrySet()){
            System.out.println(numEntry.getKey() + " " + numEntry.getValue());
            maxHeap.add(numEntry);
        }

        List<Integer> reversedList = new ArrayList<Integer>();
        List<Integer> resultList = new ArrayList<Integer>();

        Iterator it = maxHeap.iterator();

        while (it.hasNext()){

            System.out.println ( "Value: "+ it.next());
            Map.Entry<Integer, Integer> numEntry = (Map.Entry<Integer, Integer>) it.next();
            reversedList.add(numEntry.getKey());
        }

        for (int i = 0; i < k; i++) {
            resultList.add(reversedList.get(i));
        }

        return resultList;
    }

    // [1,1,1,2,2,3]
    // [1,1,1,2,2,3,4,5,6,6,6,6]
}
