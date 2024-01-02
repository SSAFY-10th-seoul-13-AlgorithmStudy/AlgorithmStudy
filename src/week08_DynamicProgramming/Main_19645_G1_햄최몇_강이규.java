package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_19645_G1_햄최몇_강이규 {

    static int N;
    static int[] value;
    static boolean[][] dp;
    static int sum;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        int res = 0;
        // dp테이블 기록
        for (int i = 0; i < N; i++) {
            for (int x = sum; x >= 0; x--) {
                for (int y = sum-x; y >= 0; y--) {
                    // dp[x][y]가, 가능한 조합에서 햄버거 하나만 더 추가한 경우일 때
                    int v = value[i];
                    if (v <= x) dp[x][y] |= dp[x-v][y];
                    if (v <= y) dp[x][y] |= dp[x][y-v];
                }
            }
        }
        // 막내 효용 찾기
        for (int i = 0; i <= sum; i++) {
            for (int j = 0; j <= i; j++) { // 선배 둘 중 작은 쪽(편의상 정해놓는 것)
                int cur = sum - i - j;
                if (dp[i][j] && cur <= j)
                    res = Math.max(res, cur);
            }
        }
        System.out.println(res);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        value = new int[N];
        dp = new boolean[50*N+1][50*N+1];
        dp[0][0] = true;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            value[i] = Integer.parseInt(st.nextToken());
            sum += value[i];
        }
    }
}
