package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2159_G3_케익배달_강이규 {

    static class Pair {
        int r, c;

        public Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int N, sr, sc;
    static Pair[] positions;
    static long[][] dp;
    // 방향 : 상 좌 하 우 중앙
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        init();
        long min = Long.MAX_VALUE;
        for (int d = 0; d < 4; d++) {
            min = Math.min(dfs(1, d) + Math.abs(positions[1].r + dr[d] - positions[0].r)
                    + Math.abs(positions[1].c + dc[d] - positions[0].c), min);
        }
        System.out.println(min);
    }

    private static long dfs(int cIdx, int dIdx) {
        if (cIdx == N) return 0;
        if (dp[dIdx][cIdx] != 0) return dp[dIdx][cIdx];

        Pair curC = positions[cIdx];
        int cr = curC.r + dr[dIdx];
        int cc = curC.c + dc[dIdx];

        long min = Long.MAX_VALUE;
        int nextCIdx = cIdx + 1;
        Pair next = positions[nextCIdx];

        for (int d = 0; d < 4; d++) {
            int nr = next.r + dr[d];
            int nc = next.c + dc[d];
            int edge = Math.abs(nr - cr) + Math.abs(nc - cc);
            min = Math.min(dfs(nextCIdx, d) + edge, min);
        }
        return dp[dIdx][cIdx] = min;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        positions = new Pair[N + 1]; // 빵집 포함
        dp = new long[4][N + 1];

        for (int i = 0; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            positions[i] = new Pair(r, c);
        }
    }
}
