package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17404_G4_RGB거리2_강이규 {

    static int N;
    static int[][] costs; // [집][컬러] / 컬러 = 0 : R, 1 : G, 2 : B
    static int[][][] dp; // [집][컬러][시작 집 컬러]

    public static void main(String[] args) throws IOException {
        init();
        solve();
        printRes();
    }

    private static void printRes() {
        int res = Integer.MAX_VALUE;
        // N과 1 컬러 연속인지 체크
        for (int j = 0; j < 3; j++) {
            for (int k = 0; k < 3; k++) {
                if (j == k) continue;
                res = Math.min(res, dp[N][j][k]);
            }
        }
        System.out.println(res);
    }

    private static void solve() {
        // dp[3] ~
        for (int i = 3; i <= N; i++) { // 집
            for (int j = 0; j < 3; j++) { // 색
                for (int k = 0; k < 3; k++) { // 시작 집 색
                    int min = 1_000_001;
                    for (int pre = 0; pre < 3; pre++) { // 이전 집 색
                        if (j == pre) continue;
                        min = Math.min(min, dp[i-1][pre][k] + costs[i][j]);
                    }
                    dp[i][j][k] = min;
                }
            }
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        // 1-based
        costs = new int[N+1][3];
        dp = new int[N+1][3][3];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                costs[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // dp[1]
        for (int j = 0; j < 3; j++) {
            for (int k = 0; k < 3; k++) {
                dp[1][j][k] = j != k ? 1001 : costs[1][j]; // 단일 cost 최대값 + 1 -> 불가능한 경우이므로, 선택될 일 없도록
            }
        }
        // dp[2]
        for (int j = 0; j < 3; j++) {
            for (int k = 0; k < 3; k++) {
                int min = 1_000_001;
                if (j != k) {
                    for (int pre = 0; pre < 3; pre++) {
                        if (j != pre) min = Math.min(dp[1][pre][k] + costs[2][j], min);
                    }
                }
                else min = 2001;
                dp[2][j][k] = min;
            }
        }
    }
}
