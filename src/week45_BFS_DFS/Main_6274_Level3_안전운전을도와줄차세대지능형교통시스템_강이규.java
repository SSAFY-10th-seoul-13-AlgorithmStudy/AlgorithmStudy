package softeer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_6274_Level3_안전운전을도와줄차세대지능형교통시스템_강이규 {

    static class Signal {
        int inD;
        int[] outD;

        Signal(int inD, int... outD) {
            this.inD = inD;
            this.outD = outD;
        }
    }

    static class Crossway {
        Signal[] sigs = new Signal[4];

        Crossway(int[] sigIdxs) {
            for (int i = 0; i < 4; i++) {
                sigs[i] = signals[sigIdxs[i]];
            }
        }

        boolean canPass(int t, int inD) {
            return sigs[t % 4].inD == inD;
        }
    }

    private static void initSignals() {
        int u = 0, l = 1, d = 2, r = 3;
        signals = new Signal[13]; // 1-based
        // init
        signals[1] = new Signal(r, u, r, d);
        signals[2] = new Signal(u, l, u, r);
        signals[3] = new Signal(l, u, l, d);
        signals[4] = new Signal(d, l, d, r);

        signals[5] = new Signal(r, u, r);
        signals[6] = new Signal(u, l, u);
        signals[7] = new Signal(l, l, d);
        signals[8] = new Signal(d, d, r);

        signals[9] = new Signal(r, r, d);
        signals[10] = new Signal(u, u, r);
        signals[11] = new Signal(l, l, u);
        signals[12] = new Signal(d, l, d);
    }

    static int N, T;
    static Crossway[][] map;
    static boolean[][] visited;
    static Signal[] signals;
    // 방향 : 상 좌 하 우
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(bfs());
    }

    private static int bfs() {
        if (!map[0][0].canPass(0, 0)) {
            return 1;
        }

        Queue<int[]> q = new ArrayDeque<>(); // node = [r, c, d]
        // visit start
        q.offer(new int[]{0, 0, 0});
        visited[0][0] = true;

        int visitCnt = 1;
        int time = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                int[] cur = q.poll();
                Signal s = map[cur[0]][cur[1]].sigs[time % 4];

                if (!map[cur[0]][cur[1]].canPass(time, cur[2])) continue;
                for (int d : s.outD) {
                    int nr = cur[0] + dr[d];
                    int nc = cur[1] + dc[d];

                    if (!inRange(nr, nc)) continue;
                    if (!visited[nr][nc]) {
                        visitCnt++;
                    }

                    visited[nr][nc] = true;
                    q.offer(new int[]{nr, nc, d});
                }
            }
            if (++time == T) break;
        }
        return visitCnt;
    }

    private static boolean inRange(int r, int c) {
        return (0 <= r && r < N) && (0 <= c && c < N);
    }

    private static void init() throws IOException {
        initSignals();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new Crossway[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                int[] sigIdxs = new int[4];
                for (int k = 0; k < 4; k++) {
                    sigIdxs[k] = Integer.parseInt(st.nextToken());
                }
                map[i][j] = new Crossway(sigIdxs);
            }
        }
    }
}
