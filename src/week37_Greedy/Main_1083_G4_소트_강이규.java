package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1083_G4_소트_강이규 {

    static int N, S;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        int s = S;
        for (int i = 0; i < N; i++) {
            int possibleCnt = 0;
            int max = arr[i];
            int maxIdx = i;
            for (int j = i+1; j < N; j++) {
                if (++possibleCnt > s) break; // 교환 가능한 범위까지만 탐색
                // max값 저장
                if (arr[j] > max) { // 값이 같으면, 더 앞에껄로 교환하는게 이득이다.
                    max = arr[j];
                    maxIdx = j;
                }
            }
            // max값을 i로 이동
            move(maxIdx, i);
            s -= maxIdx - i;
            if (s == 0) break;
        }
        printArr();
    }

    private static void printArr() {
        StringBuilder sb = new StringBuilder();
        for (int i : arr) {
            sb.append(i).append(" ");
        }
        System.out.println(sb);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        S = Integer.parseInt(br.readLine());
    }

    private static void move(int fromIdx, int toIdx) { // 항상 fromIdx > toIdx
        for (int i = fromIdx; i > toIdx; i--) {
            swap(i, i - 1);
        }
    }

    private static void swap(int idx1, int idx2) {
        int tmp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = tmp;
    }
}
