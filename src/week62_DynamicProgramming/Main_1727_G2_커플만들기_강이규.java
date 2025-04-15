package BOJ;

import java.io.*;
import java.util.*;

public class Main_1727_G2_커플만들기_강이규 {
    static int N, M;
    static int[] men;
    static int[] women;
    static int[][] dp; // dp[i][j] = 1~i번째 남자&여자로 최대 커플 만들때 min 결과값

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(solve());
    }

    private static int solve() {
        for (int i = 1; i <= N; i++) {
            for(int j = 1; j <= M; j++) {
                // 기본값 : 뛰어넘지 않고 현재 남녀 매칭
                int min = dp[i-1][j-1] + Math.abs(men[i] - women[j]);

                // 남자가 많으면, 매칭하거나 남자 건너뛰거나
                if (i > j) min = Math.min(min, dp[i-1][j]);
                else if (i < j) min = Math.min(min, dp[i][j-1]);
                dp[i][j] = min;
            }
        }
        return dp[N][M];
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        men = new int[N+1];
        women = new int[M+1];
        dp = new int[N+1][M+1];

        // men
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            men[i] = Integer.parseInt(st.nextToken());
        }

        // women
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= M; i++) {
            women[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(men);
        Arrays.sort(women);
    }
}
