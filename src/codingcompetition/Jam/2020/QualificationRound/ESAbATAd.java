package QualificationRound;

import java.util.*;

public class ESAbATAd {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String s = input.nextLine();
        String[] splited = s.split("\\s+");
        int tc = Integer.parseInt(splited[0]);
        int B = Integer.parseInt(splited[1]);

        for (int i = 1; i <= tc; i++) {
            solve(input, i, B);
        }
    }

    private static void solve(Scanner scanner, int tc, int bn) {
        String res = "";
        if (bn == 10) {
            res = solve10(scanner, bn);
        } else if (bn == 20) {
            res = solve20(scanner, bn);
        } else {
            res = solve30(scanner, bn);
        }
//        System.out.println("Case #" + tc + ": " + res);
    }

    private static String solve10(Scanner scanner, int bn) {
        int n = 4 * bn;

        char[] c = new char[bn];
        int i = 0;
        while (i < n) {
            System.out.println(i % bn);
            if (i >= n - bn) {
                c[i % bn] = scanner.nextLine().charAt(0);
            }
            i++;
        }

        String res = new String(c);
        System.out.println(res);
        return scanner.nextLine().equals("Y") ? res : "";
    }

    private static String solve20(Scanner scanner, int bn) {
        int n = 4 * bn;
        int d = 10;
        int e1 = n / 2, s1 = e1 - d;
        int e2 = n, s2 = e2 - d;

        char[] c = new char[bn];
        int i = 0;
        while (i < n) {
            System.out.println(i % bn);
            if (i >= s1 && i < e1) {
                c[i % d] = scanner.nextLine().charAt(0);
            } else if (i >= s2 && i < e2) {
                c[i % d + d] = scanner.nextLine().charAt(0);
            }
            i++;
        }

        String res = new String(c);
        System.out.println(res);
        return scanner.nextLine().equals("Y") ? res : "";
    }

    private static String solve30(Scanner scanner, int bn) {
        int n = 4 * bn;
        int d = 10;
        int e1 = n / 3, s1 = e1 - d;
        int e2 = n / 3 * 2, s2 = e2 - d;
        int e3 = n, s3 = e3 - d;

        char[] c = new char[bn];
        int i = 0;
        while (i < n) {
            System.out.println(i % bn);
            if (i >= s1 && i < e1) {
                c[i % d] = scanner.nextLine().charAt(0);
            } else if (i >= s2 && i < e2) {
                c[i % d + d] = scanner.nextLine().charAt(0);
            } else if (i >= s3 && i < e3) {
                c[i % d + d * 2] = scanner.nextLine().charAt(0);
            }
            i++;
        }

        String res = new String(c);
        System.out.println(res);
        return scanner.nextLine().equals("Y") ? res : "";
    }
}