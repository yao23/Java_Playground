package PracticeRound;

import java.util.Scanner;

public class NumberGuessing {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int testNum = Integer.parseInt(input.nextLine());

        for (int i = 0; i < testNum; i++) {
            String str = input.nextLine();
            int N = Integer.parseInt(input.nextLine());
            int trialNum = 0;

            String[] splited = str.split("\\s+");
            int low = Integer.parseInt(splited[0]); // A
            int high = Integer.parseInt(splited[1]); // B
            int mid = getMid(low, high);
            System.out.println(mid);

            while (trialNum < N && input.hasNext()) {
                String result = input.nextLine();
                if (result.equals("CORRECT")) {
                    break;
                } else if (result.equals("TOO_SMALL")) {
                    if (low == mid) { // low = mid = 9, high = 10 => A = 0, B = 10, guessNumber = 10
                        System.out.println(high);
                    } else {
                        low = mid;
                        mid = getMid(low, high);
                        System.out.println(mid);
                    }
                } else if (result.equals("TOO_BIG")) {
                    high = mid;
                    mid = getMid(low, high);
                    System.out.println(mid);
                } else if (result.equals("WRONG_ANSWER")) {
                    break;
                }
                trialNum++;
            }
        }
    }

    private static int getMid(int low, int high) {
        return (high - low) / 2 + low;
    }
}
