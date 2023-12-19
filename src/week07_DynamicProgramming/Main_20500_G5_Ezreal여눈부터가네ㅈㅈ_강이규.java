package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_20500_G5_Ezreal여눈부터가네ㅈㅈ_강이규 {
    /*
    - dp[i][j] : 자리수가 i이고, 15로 나눈 나머지가 j인 수의 개수
    - new J : (현재 j * 10 + (1 or 5)) % 15;
    - dp[i+1][newJ] += dp[i][j] 후 MOD 연산
     */

    static int N;
    static final int MOD = 1_000_000_007;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        init();
        dp();
        System.out.println(dp[N][0]);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        dp = new int[N+1][15];
    }

    private static void dp() {
        // dp[1]
        dp[1][1] = 1;
        dp[1][5] = 1;

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < 15; j++) {
                int tmp = j * 10;
                // 1 붙일 때
                int newRest1 = (tmp + 1) % 15;
                dp[i+1][newRest1] += dp[i][j];
                dp[i+1][newRest1] %= MOD;

                // 5 붙일 때
                int newRest5 = (tmp + 5) % 15;
                dp[i+1][newRest5] += dp[i][j];
                dp[i+1][newRest5] %= MOD;
            }
        }
    }
}
