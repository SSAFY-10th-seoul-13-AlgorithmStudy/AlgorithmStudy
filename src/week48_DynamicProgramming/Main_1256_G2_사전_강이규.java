package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1256_G2_사전_강이규 {
    static int N, M, K;
    static int[][] dp;
    static final int INF = 1_000_000_000;
    static StringBuilder ansBuilder;

    public static void main(String[] args) throws IOException {
        init();
        findAndPrintStr(K);
    }

    private static void initDp() {
        dp = new int[101][101];
        for (int i = 1; i <= 100; i++) {
            dp[i][0] = dp[0][i] = 1;
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                dp[i][j] = Math.min(dp[i-1][j] + dp[i][j-1], INF);
            }
        }
    }

    private static void findAndPrintStr(int k) {
        int loopCnt = N + M;
        int aCnt = N, zCnt = M;

        if (k > dp[aCnt][zCnt]) {
            System.out.println(-1);
            return;
        }
        while (loopCnt-- > 0) {
            if (aCnt < 1) {
                ansBuilder.append('z');
                zCnt--;
                continue;
            }
            if (zCnt < 1) {
                ansBuilder.append('a');
                aCnt--;
                continue;
            }
            int numOfStartWithA = dp[aCnt-1][zCnt];
            if (k <= numOfStartWithA) {
                ansBuilder.append('a');
                aCnt--;
            } else {
                ansBuilder.append('z');
                k -= numOfStartWithA;
                zCnt--;
            }
        }
        System.out.println(ansBuilder);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        ansBuilder = new StringBuilder();
        initDp();
    }
}
