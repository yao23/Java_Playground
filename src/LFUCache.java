/**
 * Created by liyao on 7/15/17.
 */

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class LFUCache {
    private int capacity;
    private Map<Integer, Data> map;
    private PriorityQueue<Data> minHeap;

    public LFUCache(int capacity) {
        map = new HashMap<>();
        minHeap = new PriorityQueue<>();
        this.capacity = capacity;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            Data data = map.get(key);
            Data newData = new Data(key, data.frequency + 1);
            minHeap.remove(data);
            minHeap.offer(newData);
            data.frequency += 1;
            map.put(key, data);
            return data.value;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Data data = map.get(key);
            data.frequency += 1;
            Data newData = new Data(key, data.value);
            minHeap.remove(data);
            minHeap.add(newData);
        } else {
            if (map.size() == capacity) {
                Data lfuData = minHeap.poll();
                map.remove(lfuData.key);
            }
            Data data = new Data(key, value);
            map.put(key, data);
            minHeap.offer(data);
        }
    }

    class Data {
        private int key;
        private int value;
        private int frequency;

        public Data(int key, int value) {
            this.key = key;
            this.value = value;
            frequency = 0;
        }
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */