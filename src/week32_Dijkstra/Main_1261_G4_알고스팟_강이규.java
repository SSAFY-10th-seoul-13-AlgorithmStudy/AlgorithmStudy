package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1261_G4_알고스팟_강이규 {

    static int N, M;
    static int[][] map;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};
    static int[][] dists;

    public static void main(String[] args) throws IOException {
        init();
        dijkstra();
        System.out.println(dists[N-1][M-1]);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        dists = new int[N][M];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dists[i], 10001);
        }

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }
    }

    private static void dijkstra() {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0, 0});
        dists[0][0] = 0;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int d = 0; d < 4; d++) {
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];
                if (!inRange(nr, nc)) continue;
                int newDist = dists[cur[0]][cur[1]] + map[nr][nc];
                if (newDist < dists[nr][nc]) {
                    dists[nr][nc] = newDist;
                    q.offer(new int[]{nr, nc});
                }
            }
        }
    }

    private static boolean inRange(int r, int c) {
        return (0 <= r && r < N) && (0 <= c && c < M);
    }
}
