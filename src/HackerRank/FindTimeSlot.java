import java.io.*;
import java.util.*;
// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.fasterxml.jackson.core.JsonProcessingException;

// Main class should be named 'Solution' and should not be public.
// Examples: FindRoom(from timestamp, to timestamp) Room

// all rooms are known and available 
// 2 meetings: [1, 5][6, 10] => O(k), 0, 1, 2, .. n-1 => O(n^2) => o(nlogn) 
// input: [3, 7] => bad
// input: [11, 15] => ok, id: 3
// input: [5, 6] => ok, id: 4
// (1, 5)
// (6, 10)
// return available roomId
// throw exception if can't find a room 
// int[] res = 
// ArryList<TimeSlot>
// TimeSlot (from, to, id) 
// ArrrayList<int[]>, 1 array with 2 elements (from, to), index as id

// how to avoid sorting, which takes o(nlogn)
// hashMap => quick check

// Uber

class FindTimeSlot {
    static int[] rooms = new int[10]; // [1, 5] => elem 0, [6, 10] => elem 1
    
    class TimeSlot {
        int from;
        int to;
        int id;
        public TimeSlot(int from, int to, int id) {
            this.from = from;
            this.to = to;
            this.id = id;
        }
    }
    
    public static int findRoom(int from, int to) {
        List<TimeSlot> list = new ArrayList<>(); // sorted by from stamp
        if (list.size() == rooms.length) {
            throw Exception("No room found");
        }
        for (int i = 0; i < list.size(); i++) { 
            // if overlap, then no room found
            TimeSlot last = list.get(i);
            TimeSlot cur = null;
            if (i + 1 < list.size()) {
                cur = list.get(i + 1);
            }
            if (last.to > from && (cur != null && to > cur.from)) { // [1, 5][6, 10] => input: [3, 7] => bad
                throw Exception("No room found");
            }
            // othewise, keep looping
        }
        
        int id = 0;
        for (int i = 0; i < rooms.length; i++) {
            if (rooms[i] > 0) { 
                id = i;
                rooms[i] = from; // put from timstap as value in rooms array 
            }
        }
        
        TimeSlot tmp = new TimeSlot(from, to, id);
        list.add(tmp);
        
        return id;
    }
    
    public static void main(String[] args) {
        System.out.println("Hello, World");
    }
}
