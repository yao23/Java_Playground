package PracticeRound;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class KickstartAlarm {
    private static int modNum = 1000000007;
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int testNum = Integer.parseInt(input.nextLine());

        for (int i = 1; i <= testNum; i++) {
            String str = input.nextLine();
            String[] params = str.split("\\s+");
            int N = Integer.parseInt(params[0]), K = Integer.parseInt(params[1]);
            int x1 = Integer.parseInt(params[2]), y1 = Integer.parseInt(params[3]);
            int P1 = Integer.parseInt(params[4]) + Integer.parseInt(params[5]);
            int P2 = Integer.parseInt(params[6]) + Integer.parseInt(params[7]);
            int F = Integer.parseInt(params[8]);
            int[] arr = getArray(N, x1, y1, P1, P2, F);
            List<List<Integer>> subsets = new ArrayList<>();
            getSubsets(0, arr, new ArrayList<>(), subsets);
            System.out.println("Case #" + i + ": " + getPowerSum(K, subsets));
        }
    }

    private static int[] getArray(int N, int x1, int y1, int P1, int P2, int F) {
        int[] arr = new int[N];
        arr[0] = (x1 + y1) % F;
        for (int i = 1; i < N; i++) {
            arr[i] = ((P1 % F) * (arr[i - 1] % F) + P2 % F) %F;
        }
        return arr;
    }

    private static void getSubsets(int depth, int[] arr, List<Integer> tmp, List<List<Integer>> result) {
        if (tmp.size() > 0) {
            result.add(new ArrayList<>(tmp));
        }
        int len = arr.length;
        if (depth == len) {
            return;
        } else {
            for (int i = depth; i < len; i++) {
                tmp.add(arr[i]);
                getSubsets(i + 1, arr, tmp, result);
                tmp.remove(tmp.size() - 1);
            }
        }
    }

    private static int getPowerSum(int K, List<List<Integer>> subsets) {
        int sum = 0;

        for (List<Integer> subset : subsets) {
            sum += getPower(K, subset);
        }

        return sum % modNum;
    }

    private static int getPower(int K, List<Integer> subset) {
        int sum = 0;
        for (int i = 1; i <= subset.size(); i++) {
            int num = subset.get(i - 1);
            int Ai = getAi(i, K, num);
            sum += Ai;
        }
        return sum % modNum;
    }

    private static int getAi(int i, int K, int Ai) {
        if (i == 1) {
            return Ai * K;
        } else {
            return (int)(Ai * i * (Math.pow(i, K) - 1) / (i - 1)) % modNum;
        }

    }
}
