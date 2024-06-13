package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_1937_G3_욕심쟁이판다_강이규 {

    static int N;
    static int[][] map; // 0으로 패딩 주기 -> movable() 연산 줄어든다
    static int[][] dp; // dp[i][j] = 해당 칸 포함, 이후로 방문할 수 있는 최대 칸 수
    // 방향 : 상 좌 하 우
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(solve());
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N+2][N+2];
        dp = new int[N+2][N+2];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static int solve() {
        int res = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                res = Math.max(res, dfs(i, j));
            }
        }
        Map<Integer, Integer> map;
        return res;
    }

    private static int dfs(int r, int c) {
        if (dp[r][c] != 0)
            return dp[r][c];

        int nexts = 0;
        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if (!movable(r, c, nr, nc))
                continue;
            nexts = Math.max(nexts, dfs(nr, nc));
        }
        return dp[r][c] = nexts + 1;
    }

    private static boolean movable(int r, int c, int nr, int nc) {
//        return (0 <= nr && nr < N) && (0 <= nc && nc < N)
//                && map[nr][nc] > map[r][c];
        return map[nr][nc] > map[r][c];
    }
}
