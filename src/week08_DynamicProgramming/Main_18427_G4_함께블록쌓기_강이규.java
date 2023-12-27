package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_18427_G4_함께블록쌓기_강이규 {
    /*
    dp[i][j] = i번째 사람까지 봤을 때, H를 만들 수 있는 경우의 수
    = 1) i번째 사람이 블록을 선택하지 않았을 때 경우의 수
    + 2) 선택했을 때의 경우의 수
      1) dp[i-1][j]
      2) sum(dp[i-1][j-k] for k
     */

    static int N, M, H;
    static final int MOD = 10007;
    static List<Integer>[] blocks;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        init();
        dp();
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        blocks = new ArrayList[N+1];
        dp = new int[N+1][H+1];
        for (int i = 1; i <= N; i++) {
            blocks[i] = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                blocks[i].add(Integer.parseInt(st.nextToken()));
            }
        }
    }

    private static void dp() {
        for (int i = 0; i <= N; i++) { // 0까지 포함하는 이유 : i == 1 && j == k 인 경우를 쉽게 카운트하기 위함
            dp[i][0] = 1;
        }
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= H; j++) {
                dp[i][j] = dp[i-1][j]; // i번째 사람이 고르지 않았을 때
                // k높이의 블록을 고를 때
                for (int k : blocks[i]) {
                    if (j < k) continue;
                    dp[i][j] += dp[i-1][j-k];
                    dp[i][j] %= MOD;
                }
            }
        }
        System.out.println(dp[N][H]);
    }
}
