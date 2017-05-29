public class Main {
    private static void test() {
        System.out.println("test");
    }

    private static boolean isSquare(int x, int target) {
        return (x * x == target);
    }

    private static int getNumSquares(int start, int end) {
        int sqrtStart = (int) (Math.sqrt(start)); // round down
        int sqrtEnd = (int) (Math.sqrt(end)); // round down

        boolean startIsSquare = isSquare(sqrtStart, start);
        boolean endIsSquare = isSquare(sqrtEnd, end);

        if (startIsSquare && endIsSquare) {
            return (sqrtEnd - sqrtStart + 1);
        } else if (!startIsSquare && !endIsSquare) {
            return (sqrtEnd - sqrtStart);
        } else {
            sqrtStart = startIsSquare ? sqrtStart : (sqrtStart + 1);

            return (sqrtEnd - sqrtStart + 1);
        }
    }

    private static int solution(int A, int B) {
        // write your code in Java SE 8

        if (A > B) { // corner case when start is larger than end, then switch
            int tmp = B;
            B = A;
            A = tmp;
        }

        // check start number
        int start = (A < 0) ? 0 : A; // whole square starts from zero to positive numbers
        int end = B;

        return getNumSquares(start, end);
    }

    private static int solution2(int[] A) {
        int n = A.length;
        int result = 0;
        for (int i = 0; i < n - 1; i++) {
            if (A[i] == A[i + 1])
                result = result + 1;
        }
        System.out.println("tmp result: " + result);
        int r = -1;
        for (int i = 0; i < n; i++) {
            int count = 0;
            if (i > 0) {
                if (A[i - 1] != A[i])
                    count = count + 1;
                else
                    count = count - 1;
            }
            if (i < n - 1) {
                if (A[i + 1] != A[i])
                    count = count + 1;
                else
                    count = count - 1;
            }
            System.out.println("index: " + i + ", count: " + count);
            r = Math.max(r, count);
        }
        System.out.println("tmp r: " + r);
        return result + (r >= 0 ? r : -1);
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");

        test();
/*
        // get number of squares
        int left = 4;
        int right = 17;

        System.out.println("num of squares in [" + left + ", " + right + "]: " + solution(left, right));

        left = -10000;
        right = 10000;

        System.out.println("num of squares in [" + left + ", " + right + "]: " + solution(left, right));

        left = -10;
        right = 27;

        System.out.println("num of squares in [" + left + ", " + right + "]: " +  solution(left, right));

        left = 100;
        right = -100;

        System.out.println("num of squares in [" + left + ", " + right + "]: " +  solution(left, right));

        left = 5;
        right = 8;

        System.out.println("num of squares in [" + left + ", " + right + "]: " +  solution(left, right));

        left = 2;
        right = 9;

        System.out.println("num of squares in [" + left + ", " + right + "]: " +  solution(left, right));

        left = 2;
        right = 10;

        System.out.println("num of squares in [" + left + ", " + right + "]: " +  solution(left, right));

        left = 9;
        right = 10;

        System.out.println("num of squares in [" + left + ", " + right + "]: " +  solution(left, right));

        left = 4;
        right = 10;

        System.out.println("num of squares in [" + left + ", " + right + "]: " +  solution(left, right));

        left = 5;
        right = 10;

        System.out.println("num of squares in [" + left + ", " + right + "]: " +  solution(left, right));
*/
        System.out.println("max num of consecutive pairs: " + solution2(new int[]{1, 1, 0, 1, 0, 0}));

        System.out.println("max num of consecutive pairs: " + solution2(new int[]{1, 1, 1, 1}));

        System.out.println("max num of consecutive pairs: " + solution2(new int[]{1, 1, 0, 0}));

        System.out.println("max num of consecutive pairs: " + solution2(new int[]{1, 1, 0, 0, 0}));

        System.out.println("max num of consecutive pairs: " + solution2(new int[]{1, 0, 1, 0}));

        System.out.println("max num of consecutive pairs: " + solution2(new int[]{1, 1, 0, 1}));
    }
}
