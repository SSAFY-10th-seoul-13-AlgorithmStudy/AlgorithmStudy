package week11_BinarySearch;

import java.util.Arrays;
import java.util.Scanner;

public class Main_2110_G4_공유기설치_김아린 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n, c, st, router, start, end, mid, ans = 0;
        n = scanner.nextInt();
        c = scanner.nextInt();
        int[] pos = new int[n];

        for (int i = 0; i < n; i++) {
            pos[i] = scanner.nextInt();
        }

        Arrays.sort(pos);
        start = 1;                             // 최소 거리
        end = pos[n - 1] - pos[0];             // 최대 거리

        while (start <= end) {
            router = 1;
            mid = (start + end) / 2;
            st = pos[0];

            for (int i = 1; i < n; i++) {
                if (pos[i] - st >= mid) {
                    router++;
                    st = pos[i];
                }
            }

            if (router >= c) {
                ans = Math.max(ans, mid);
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        System.out.println(ans);
    }
}