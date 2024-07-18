package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_16973_G4_직사각형탈출_강이규 {

    static int N, M, H, W;
    static int sr, sc, er, ec;
    static int[][] map;
    static boolean[][] visited; // visited는 왼쪽 위 칸을 기준으로 판단한다.
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(bfs());
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // map
        map = new int[N + 1][M + 1];
        visited = new boolean[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // etc
        st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        sr = Integer.parseInt(st.nextToken());
        sc = Integer.parseInt(st.nextToken());
        er = Integer.parseInt(st.nextToken());
        ec = Integer.parseInt(st.nextToken());
    }

    private static int bfs() {
        Queue<int[]> q = new ArrayDeque<>();
        // 시작점
        q.offer(new int[]{sr, sc, 0});
        visited[sr][sc] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int nTime = cur[2] + 1;
            int r = cur[0], c = cur[1];

            for (int d = 0; d < 4; d++) {
                if (!valid(r, c, d)) {
                    continue;
                }
                int nr = r + dr[d];
                int nc = c + dc[d];
                if (nr == er && nc == ec) return nTime;
                q.offer(new int[]{nr, nc, nTime});
                visited[nr][nc] = true;
            }
        }
        return -1;
    }

    private static boolean valid(int r, int c, int d) {
        // 왼쪽 위 점 체크
        int nr = r + dr[d];
        int nc = c + dc[d];
        if (!isValid(nr, nc)) return false;
        if (visited[nr][nc]) return false;
        // 상하 이동이라면, width대로 체크
        if (d % 2 == 0) { // 0 : 상, 2 : 하
            int newR = r + dr[d] + (d == 0 ? 0 : H - 1); // 상이면 바로 윗줄, 하면 바로 아랫줄 탐색
            if (!(0 < newR && newR <= N)) return false; // 새로운 행만 범위체크
            // 진행방향 기준 제일 앞 행 돌면서, 벽인지 체크
            int newC = c;
//            int w = W - 1;
            int w = W; // 총 줄 수만큼 반복해야 함!!
            while (w-- > 0) {
                if (map[newR][newC] != 0) return false;
                newC++;
            }
        } else { // 좌우
            int newC = c + dc[d] + (d == 1 ? 0 : W - 1);
            if (!(0 < newC && newC <= M)) return false;
            int newR = r;
//            int h = H - 1;
            int h = H; // 총 줄 수만큼 반복해야 함!!
            while (h-- > 0) {
                if (map[newR][newC] != 0) return false;
                newR++;
            }
        }
        // 방문 안 했고, 모두 벽 없으면 이동 가능
        return true;
    }

    private static boolean isValid(int r, int c) {
        return inRange(r, c) && map[r][c] != 1;
    }

    private static boolean inRange(int r, int c) {
        return (0 < r && r <= N) && (0 < c && c < M);
    }
}
