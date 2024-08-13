package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_15559_G2_내선물을받아줘_강이규 {

    static int N, M;
    static char[][] map;
    static int[][] visited;
    static int res = 0;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        int dfsCnt = 1; // 방문표시 + 이전 dfs 방문/현재 dfs 방문 구별 기능
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j] == 0) {
                    dfs(i, j,dfsCnt++);
                }
            }
        }
        System.out.println(res);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        visited = new int[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);
            }
        }
    }

    private static void dfs(int r, int c, int dfsCnt) {
        visited[r][c] = dfsCnt; // 방문처리
        // nr, nc 계산
        int nr = r, nc = c;
        char cmd = map[r][c];
        if (cmd == 'S' || cmd == 'N') {
            nr += cmd == 'S' ? 1 : -1;
        } else {
            nc += cmd == 'E' ? 1 : -1;
        }
        // 방문 안 한 칸일때
        if (visited[nr][nc] == 0) {
            dfs(nr, nc, dfsCnt);
        }
        // 현재 dfs에서 방문한 칸일 때
        else if (visited[nr][nc] == dfsCnt) { // 사이클 생김. res++
            res++;
        }
        // 이전 dfs에서 방문한 칸을 방문하면, 선물 하나로 같이 처리할 수 있다.
    }
}
