package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2482_G3_색상환_강이규 {

    static final int MOD = 1_000_000_003;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        int[][] dp = new int[n+1][k+1]; // dp[n][k] = n개의 색상 중, k개를 선택하는 경우의 수

        for (int i = 1; i <= n; i++) {
            dp[i][1] = i;
            int end = Math.min(i / 2, k);
            for (int j = 2; j <= end; j++) {
                dp[i][j] = (dp[i-1][j] + dp[i-2][j-1]) % MOD;
            }
        }
        System.out.println(dp[n][k]);
    }
}
