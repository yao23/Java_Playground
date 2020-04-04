import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int testCase = Integer.parseInt(input.nextLine());
        for (int i = 1; i <= testCase; i++) {
            solve(input, i);
        }
    }

    private static void solve(Scanner scanner, int tc) {
        int n = Integer.parseInt(scanner.nextLine());
        char[] c = new char[n];
        String res = "";
        PriorityQueue<Interval> queue = new PriorityQueue<>(n, new RequestComparator());

        for (int i = 0; i < n; i++) {
            String s = scanner.nextLine();
            String[] splited = s.split("\\s+");
            int start = Integer.parseInt(splited[0]);
            int end = Integer.parseInt(splited[1]);
            Interval interval = new Interval(start, end, i);
            queue.add(interval);
        }

        List<Interval> listC = new ArrayList<>();
        List<Interval> listJ = new ArrayList<>();
        char curChar = 'C';
        Interval pre = null;
        while (!queue.isEmpty()) {
            Interval cur = queue.poll();
            if (pre == null) {
                c[cur.getIdx()] = curChar;
            } else {
                if (isOverlap(pre, cur)) {
                    char flipCurChar = flipCurChar(curChar);
                    Interval lastSamePersonInterval = getLastSamePersonInterval(listC, listJ, flipCurChar);
                    if (isOverlap(lastSamePersonInterval, cur)) {
                        res = "IMPOSSIBLE";
                        break;
                    } else {
                        c[cur.getIdx()] = flipCurChar;
                        curChar = flipCurChar;
                    }
                } else {
                    c[cur.getIdx()] = curChar;
                }
            }
            pre = cur;
            addToList(listC, listJ, curChar, cur);
        }

        if (res.equals("IMPOSSIBLE")) {
            System.out.println("Case #" + tc +": " + res);
        } else {
            System.out.println("Case #" + tc +": " + new String(c));
        }
    }

    private static void addToList(List<Interval> listC, List<Interval> listJ, char c, Interval cur) {
        if (c == 'C') {
            listC.add(cur);
        } else {
            listJ.add(cur);
        }
    }

    private static Interval getLastSamePersonInterval(List<Interval> listC, List<Interval> listJ, char c) {
        if (c == 'C') {
            if (listC.size() > 0) {
                return listC.get(listC.size() - 1);
            } else {
                return null;
            }
        } else {
            if (listJ.size() > 0) {
                return listJ.get(listJ.size() - 1);
            } else {
                return null;
            }
        }
    }

    private static boolean isOverlap(Interval i1, Interval i2) {
        return i1 != null && i1.getE() > i2.getS();
    }

    private static char flipCurChar(char c) {
        if (c == 'C') {
            return 'J';
        } else {
            return 'C';
        }
    }

    private static class Interval {
        private int s;
        private int e;
        private int i;

        public Interval(int s, int e, int i) {
            this.s = s;
            this.e = e;
            this.i = i;
        }

        public int getS() {
            return s;
        }

        public int getE() {
            return e;
        }

        public int getIdx() {
            return i;
        }
    }

    private static class RequestComparator implements Comparator<Interval> {
        public int compare(Interval i1, Interval i2) {
            int s1 = i1.getS();
            int s2 = i2.getS();
            int e1 = i1.getE();
            int e2 = i2.getE();
            if (s1 == s2) {
                return e1 - e2;
            } else {
                return s1 - s2;
            }
        }
    }
}

// 4
// 3
// 360 480
// 420 540
// 600 660
// 3
// 0 1440
// 1 3
// 2 4
// 5
// 99 150
// 1 100
// 100 301
// 2 5
// 150 250
// 2
// 0 720
// 720 1440

// Case #1: CJC
// Case #2: IMPOSSIBLE
// Case #3: JCCJJ
// Case #4: CC