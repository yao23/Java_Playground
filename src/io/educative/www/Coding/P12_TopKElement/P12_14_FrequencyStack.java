package io.educative.www.Coding.P12_TopKElement;

import java.util.*;

public class P12_14_FrequencyStack {
    int sequenceNumber = 0;
    PriorityQueue<Element> maxHeap = new PriorityQueue<Element>(new ElementComparator());
    Map<Integer, Integer> frequencyMap = new HashMap<>();

    /**
     * Time complexity
     * The time complexity of push() and pop() is O(logN) where ‘N’ is the current number of elements in the heap.
     *
     * Space complexity
     * We will need O(N) space for the heap and the map, so the overall space complexity of the algorithm is O(N).
     *
     * @param num
     */
    public void push(int num) {
        frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        maxHeap.offer(new Element(num, frequencyMap.get(num), sequenceNumber++));
    }

    public int pop() {
        int num = maxHeap.poll().number;

        // decrement the frequency or remove if this is the last number
        if (frequencyMap.get(num) > 1)
            frequencyMap.put(num, frequencyMap.get(num) - 1);
        else
            frequencyMap.remove(num);

        return num;
    }

    public static void main(String[] args) {
        P12_14_FrequencyStack frequencyStack = new P12_14_FrequencyStack();
        frequencyStack.push(1);
        frequencyStack.push(2);
        frequencyStack.push(3);
        frequencyStack.push(2);
        frequencyStack.push(1);
        frequencyStack.push(2);
        frequencyStack.push(5);
        System.out.println(frequencyStack.pop());
        System.out.println(frequencyStack.pop());
        System.out.println(frequencyStack.pop());
    }
}

class Element {
    int number;
    int frequency;
    int sequenceNumber;

    public Element(int number, int frequency, int sequenceNumber) {
        this.number = number;
        this.frequency = frequency;
        this.sequenceNumber = sequenceNumber;
    }
}

class ElementComparator implements Comparator<Element> {
    public int compare(Element e1, Element e2) {
        if (e1.frequency != e2.frequency)
            return e2.frequency - e1.frequency;
        // if both elements have same frequency, return the one that was pushed later
        return e2.sequenceNumber - e1.sequenceNumber;
    }
}