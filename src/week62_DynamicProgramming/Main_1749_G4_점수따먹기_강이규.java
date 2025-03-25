package BOJ;

import java.io.*;
import java.util.*;

public class Main_1749_G4_점수따먹기_강이규 {
    static int N, M;
    static int[][] prefixsum;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() { // 시간복잡도 주의! sum(1~200) * sum(1~200) 이다.
        int result = Integer.MIN_VALUE;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                for (int subI = 1; subI <= i; subI++) {
                    for (int subJ = 1; subJ <= j; subJ++) {
                        result = Math.max(result, prefixsum[i][j] - prefixsum[subI-1][j] - prefixsum[i][subJ-1] + prefixsum[subI-1][subJ-1]);
                    }
                }
            }
        }
        System.out.println(result);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        prefixsum = new int[N+1][M+1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                int cur = Integer.parseInt(st.nextToken());
                prefixsum[i][j] = prefixsum[i-1][j] + prefixsum[i][j-1] - prefixsum[i-1][j-1] + cur;
            }
        }
    }
}
