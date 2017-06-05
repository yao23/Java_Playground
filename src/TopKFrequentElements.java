/**
 * Created by liyao on 6/4/17.
 */
import java.util.*;

public class TopKFrequentElements {
    public static List<Integer> topKFrequent(int[] nums, int k) {
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

//        PriorityQueue<Map.Entry<Integer, Integer>> maxHeap =
//                new PriorityQueue<Map.Entry<Integer, Integer>>((a,b)->(b.getValue()-a.getValue()));
        PriorityQueue<Map.Entry<Integer, Integer>> maxHeap = new PriorityQueue<Map.Entry<Integer, Integer>>(k, new NumOccrComparator());
        for(Map.Entry numEntry : numOccrHashMap.entrySet()) {
            System.out.println(numEntry.getKey() + " " + numEntry.getValue());
            maxHeap.add(numEntry);
        }

        List<Integer> reversedList = new ArrayList<>();
        List<Integer> resultList = new ArrayList<>();


        System.out.println("max heap size: " + maxHeap.size());
        while (!maxHeap.isEmpty()) {
            Map.Entry<Integer, Integer> e = maxHeap.poll();
            System.out.println(e.getKey() + " " + e.getValue());
            reversedList.add(e.getKey());
        }
/*
        Iterator it = maxHeap.iterator(); //

        while (it.hasNext()){

            System.out.println ( "Value: "+ it.next());
            Map.Entry<Integer, Integer> numEntry = (Map.Entry<Integer, Integer>) it.next();
            reversedList.add(numEntry.getKey());
        }
*/
        for (int i = 0; i < k; i++) {
            resultList.add(reversedList.get(i));
        }

        return resultList;
    }
/*
    public List<Integer> topKFrequentV1(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int n: nums){
            map.put(n, map.getOrDefault(n,0)+1);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> maxHeap =
                new PriorityQueue<>((a,b)->(b.getValue()-a.getValue()));
        for(Map.Entry<Integer,Integer> entry: map.entrySet()){
            maxHeap.add(entry);
        }

        List<Integer> res = new ArrayList<>();
        while(res.size()<k){
            Map.Entry<Integer, Integer> entry = maxHeap.poll();
            res.add(entry.getKey());
        }
        return res;
    }
*/
    public static void main(String[] args) {
        int [] input = new int[] {1,1,1,2,2,3,4,5,6,6,6,6};

        List<Integer> output = topKFrequent(input, 2);

        System.out.println("output: ");
        for (Integer element : output) {
            System.out.print(element + " ");
        }
    }

    // [1,1,1,2,2,3]
    // [1,1,1,2,2,3,4,5,6,6,6,6]


    // beats 3.47%
}
