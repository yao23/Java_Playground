/**
 * Created by liyao on 7/15/17.
 */

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class LFUCache {

    private int index;
    private int capacity;
    private Map<Integer, Data> map;
    private PriorityQueue<Data> minHeap;

    public LFUCache(int capacity) {
        if (capacity == 0) {
            return;
        } else {
            map = new HashMap<>(); // System.out.println("finish init map");
            minHeap = new PriorityQueue<>(capacity); // System.out.println("finish init minHeap");
            this.capacity = capacity; // System.out.println("finish init");
            this.index = 0;
        }
    }

    public int get(int key) { // System.out.println("get " + key);
        if (capacity == 0) {
            return -1;
        }

        // printMap();
        // printHeap();

        // System.out.print("map: ");
        // System.out.println(map);
        if (map.containsKey(key)) { // System.out.println("[GET] key existed " + key);
            index++;
            Data data = map.get(key);
            Data newData = new Data(key, data.value, data.frequency + 1, index);
            minHeap.remove(data);
            minHeap.offer(newData);
            data.frequency += 1;
            data.index = index;

            // System.out.println("after get " + key + ", value: " + data.value);
            // printMap();
            // printHeap();
            return data.value;
        } else { // System.out.println("[GET] key not existed");
            return -1;
        }
    }

    public void put(int key, int value) { //System.out.println("put " + key + ", " + value);
        if (capacity == 0) {
            return;
        }

        // printMap();
        // printHeap();

        index++;
        if (map.containsKey(key)) { // System.out.println("[PUT] key existed");
            Data data = map.get(key);
            minHeap.remove(data); // remove old data from heap first (test case 4)

            if (data.value == value) {
                data.frequency += 1;
            } else {
                data.frequency += 1;
                data.value = value;
            }

            Data newData = new Data(key, data.value, data.frequency, index);
            minHeap.add(newData);
        } else { // System.out.println("[PUT] key not existed");
            if (map.size() == capacity) {
                Data lfuData = minHeap.poll();
                map.remove(lfuData.key);
            }
            Data data = new Data(key, value, 0, index);
            map.put(key, data);
            minHeap.offer(data);
        }
        // System.out.println("after put " + key + ", " + value);
        // printMap();
        // printHeap();
    }

    private void printMap() {
        System.out.println("start print map: ");
        for (Map.Entry<Integer, Data> entry : map.entrySet()) {
            int key = entry.getKey();
            Data data = entry.getValue();
            System.out.println(key + ": " + data.value + ", " + data.frequency + ", " + data.index);
        }
        System.out.println("print map is done");
    }

    private void printHeap() {
        System.out.println("start print heap: ");
        for (Data data: minHeap.toArray(new Data[minHeap.size()])) {
            System.out.println(data.key + ": " + data.value + ", " + data.frequency + ", " + data.index);
        }
        System.out.println("print heap is done");
    }

    class Data implements Comparable<Data> {
        private int key;
        private int value;
        private int frequency;
        private int index;

        public Data(int key, int value, int frequency, int index) {
            this.key = key;
            this.value = value;
            this.frequency = frequency;
            this.index = index;
        }

        public int compareTo(Data that) {
            if (this.frequency < that.frequency) {
                return -1;
            } else if (this.frequency == that.frequency) {
                if (this.index < that.index) {
                    return -1;
                } else if (this.index > that.index) {
                    return 1;
                } else {
                    return 0;
                }
            } else {
                return 1;
            }
        }

        @Override
        public boolean equals(Object object) {
            if (object instanceof Data) {
                Data that = (Data)object;
                if(this.key == that.key & this.value == that.value){
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }

        @Override
        public int hashCode(){
            return index;
        }
    }

    // ["LFUCache","put","put","get","put","get","get","put","get","get","get"], [[2],[1,1],[2,2],[1],[3,3],[2],[3],[4,4],[1],[3],[4]] => [null,null,null,1,null,-1,3,null,-1,3,4]
    // ["LFUCache","put","get"], [[0],[0,0],[0]] => [null,null,-1]
    // ["LFUCache","put","put","get","put","get","get","put","get","get","get"],[[2],[1,1],[2,2],[1],[3,3],[2],[3],[4,4],[1],[3],[4]] => [null,null,null,2,1,2,null,null,-1,2,1,4]
    // ["LFUCache","put","put","put","put","get"],[[2],[3,1],[2,1],[2,2],[4,4],[2]] => [null,null,null,null,null,2]
    // ["LFUCache","put","put","put","put","put","get","put","get","get","put","get","put","put","put","get","put","get","get","get","get","put","put","get","get","get","put","put","get","put","get","put","get","get","get","put","put","put","get","put","get","get","put","put","get","put","put","put","put","get","put","put","get","put","put","get","put","put","put","put","put","get","put","put","get","put","get","get","get","put","get","get","put","put","put","put","get","put","put","put","put","get","get","get","put","put","put","get","put","put","put","get","put","put","put","get","get","get","put","put","put","put","get","put","put","put","put","put","put","put"],[[10],[10,13],[3,17],[6,11],[10,5],[9,10],[13],[2,19],[2],[3],[5,25],[8],[9,22],[5,5],[1,30],[11],[9,12],[7],[5],[8],[9],[4,30],[9,3],[9],[10],[10],[6,14],[3,1],[3],[10,11],[8],[2,14],[1],[5],[4],[11,4],[12,24],[5,18],[13],[7,23],[8],[12],[3,27],[2,12],[5],[2,9],[13,4],[8,18],[1,7],[6],[9,29],[8,21],[5],[6,30],[1,12],[10],[4,15],[7,22],[11,26],[8,17],[9,29],[5],[3,4],[11,30],[12],[4,29],[3],[9],[6],[3,4],[1],[10],[3,29],[10,28],[1,20],[11,13],[3],[3,12],[3,8],[10,9],[3,26],[8],[7],[5],[13,17],[2,27],[11,15],[12],[9,19],[2,15],[3,16],[1],[12,17],[9,1],[6,19],[4],[5],[5],[8,1],[11,7],[5,2],[9,28],[1],[2,2],[7,4],[4,22],[7,24],[9,26],[13,28],[11,26]] => [null,null,null,null,null,null,-1,null,19,17,null,-1,null,null,null,-1,null,-1,5,-1,12,null,null,3,5,5,null,null,1,null,-1,null,30,5,30,null,null,null,-1,null,-1,24,null,null,18,null,null,null,null,14,null,null,18,null,null,11,null,null,null,null,null,18,null,null,-1,null,4,29,30,null,12,11,null,null,null,null,29,null,null,null,null,17,-1,18,null,null,null,-1,null,null,null,20,null,null,null,29,18,18,null,null,null,null,20,null,null,null,null,null,null,null]
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */