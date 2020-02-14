package RoundA;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Contention {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int testCase = input.nextInt();
        for (int i = 1; i <= testCase; i++) {
            System.out.println(String.format("Case #%d: %s", i, solve(input)));
        }
    }

    private static String solve(Scanner scanner) {
        int N = scanner.nextInt();
        int Q = scanner.nextInt();
        PriorityQueue<Request> queue = new PriorityQueue<>(Q, new RequestComparator());

        for (int i = 0; i < Q; i++) {
            int L = scanner.nextInt();
            int R = scanner.nextInt();
            Request request = new Request(L, R);
            queue.add(request);
        }
        System.out.println("N, Q: " + N + ", " + Q);
        int[] bookedSeats = new int[N+1];
        int min = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            Request r = queue.poll(); System.out.println("L, R: " + r.getLen() + ", " + r.getR());
            int leftSeatsNum = getLeftSeatsNum(r, bookedSeats);
            min = Math.min(min, leftSeatsNum);
        }

        return String.valueOf(min);
    }

    private static class Request {
        private int l;
        private int r;

        public Request(int l, int r) {
            this.l = l;
            this.r = r;
        }

        public int getLen() {
            return r - l;
        }

        public int getL() {
            return l;
        }

        public int getR() {
            return r;
        }
    }

    private static class RequestComparator implements Comparator<Request> {
        public int compare(Request r1, Request r2) {
            int r1Len = r1.getLen();
            int r2Len = r2.getLen();
            if (r1Len > r2Len) {
                return 1;
            } else if (r1Len < r2Len) {
                return -1;
            } else {
                int r1L = r1.getL();
                int r2L = r2.getL();
                if (r1L > r2L) {
                    return 1;
                } else if (r1L < r2L) {
                    return -1;
                } else {
                    return 0;
                }
            }
        }
    }

    private static int getLeftSeatsNum(Request r, int[] bookedSeats) {
        int res = 0, left = r.getL(), right = r.getR();
        for (int i = left; i <= right; i++) {
            if (bookedSeats[i] == 0) {
                bookedSeats[i] = 1;
                res++;
            }
        }
        return res;
    }
}
