/**
 * Created by liyao on 6/4/17.
 */
import java.util.*;

public class TopKFrequentElements {
    public List<Integer> topKFrequent(int[] nums, int k) {
        class Entry implements Comparable<Entry> {
            private int key;
            private int value;

            Entry(int k, int v) {
                this.key = k;
                this.value = v;
            }

            public int getValue() {
                return value;
            }

            @Override
            public int compareTo(Entry other) {
                return this.getValue().compareTo(other.getValue());
            }
        }

        class NumOccrComparator implements Comparator {
            @Override
            public int compare(Map.Entry e1, Map.Entry e2) {
                return e2.getValue() - e1.getValue();
            }
        }

        ArrayList<Entry> numEntries = new ArrayList<Entry>();
        HashMap<Integer, Integer> numOccrHashMap = new HashMap<Integer, Integer>();

        for (int num : nums) {
            if (numOccrHashMap.containsKey(num)) {
                numOccrHashMap.put(num, (numOccrHashMap.get(num)+1));
            } else {
                numOccrHashMap.put(num, 1);
            }
        }

        PriorityQueue<Map.Entry> maxHeap = new PriorityQueue<Map.Entry>(k, new NumOccrComparator());
        for(Map.Entry numEntry : numOccrHashMap.entrySet()){
            System.out.println(numEntry.getKey() + " " + numEntry.getValue());
            maxHeap.add(numEntry);
        }

        List<Integer> reversedList = new ArrayList<Integer>();
        List<Integer> resultList = new ArrayList<Integer>();

        Iterator it = maxHeap.iterator();

        while (it.hasNext()){

            System.out.println ( "Value: "+ it.next());

            reversedList.add(it.next());
        }

        for (int i = reversedList.size()-1; i >= 0; i--) {
            resultList.add(reversedList.get(i));
        }

        return resultList;
    }
}
