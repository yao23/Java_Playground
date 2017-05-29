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
System.out.println(sqrtStart + ", " + sqrtEnd);
        boolean startIsSquare = isSquare(sqrtStart, start);
        boolean endIsSquare = isSquare(sqrtEnd, end);
System.out.println(startIsSquare + ", " + endIsSquare);
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

    public static void main(String[] args) {
        System.out.println("Hello World!");

        test();

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

    }
}
