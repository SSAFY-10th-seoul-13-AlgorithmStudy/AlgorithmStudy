package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2169_G2_로봇조종하기_강이규 {

    static int N, M;
    static int[][] map;
    static int[][] dp;
    static int[][] temp;

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(dp());
    }

    private static int dp() {
        // 먼저 채울부분 채우기. 공통으로 맨 윗 행, leftToRight 왼쪽 열, rightToLeft 오른쪽 열
        // 맨 윗 행
        dp[0][0] = map[0][0];
        for (int i = 1; i < M; i++) {
            dp[0][i] = temp[0][i] = dp[0][i-1] + map[0][i];
        }
        // 나머지 행
        for (int i = 1; i < N; i++) {
            // left to right
            for (int j = 0; j < M; j++) {
                if (j > 0) dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]) + map[i][j];
                else dp[i][j] = dp[i-1][j] + map[i][j];
            }
            // right to left
            for (int j = M - 1; j >= 0; j--) {
                if (j < M - 1) temp[i][j] = Math.max(dp[i-1][j], temp[i][j+1]) + map[i][j];
                else temp[i][j] = dp[i-1][j] + map[i][j];
            }
            // update max
            for (int j = 0; j < M; j++) {
                dp[i][j] = Math.max(dp[i][j], temp[i][j]);
            }
        }
        return dp[N-1][M-1];
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        dp = new int[N][M];
        temp = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
