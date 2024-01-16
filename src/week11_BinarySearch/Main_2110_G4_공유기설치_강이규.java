package week11_BinarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_2110_G4_공유기설치_강이규 {

    static int N, C;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(binSearch());
    }

    private static int binSearch() {
        if (C == 2) return arr[N-1] - arr[0];
        int res = 0;
        int left = 1; // 공유기간 거리 최소값
        int right = arr[N-1] - arr[0]; // 최대값

        while (left < right) {
            int mid = (left + right) / 2;
            // 현재 mid값으로 필요한 공유기 수를 세고, 남으면 left로, 부족하면(거리가 멀면) 기록하고 right로
            int cnt = 1;
            int cur = arr[0];
            for (int i = 0; i < N; i++) {
                if (arr[i] - cur >= mid) {
                    cnt++;
                    cur = arr[i];
                }
            }

            if (cnt >= C) {
                res = mid;
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return res;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = br.readLine().split(" ");
        N = Integer.parseInt(tmp[0]);
        C = Integer.parseInt(tmp[1]);

        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);
    }
}