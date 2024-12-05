package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_16920_G2_확장게임_강이규 {

    static class Pos {
        int r, c;
        char v;

        public Pos(int r, int c, char v) {
            this.r = r;
            this.c = c;
            this.v = v;
        }
    }

    static int N, M, P;
    static Pos[][] map;
    // 방향 : 상 좌 하 우
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};

    // player 속성들
    static long[] speeds;
    static Queue<Pos>[] queues;

    public static void main(String[] args) throws IOException {
        init();
        solve();
        print();
    }

    static void print() {
        int[] numCastles = new int[P+1];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int pIdx = map[i][j].v - '0';
                if (pIdx < 1 || P < pIdx) continue;
                numCastles[pIdx]++;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= P; i++) {
            sb.append(numCastles[i]).append(" ");
        }
        System.out.println(sb);
    }

    static void solve() {
        while (true) {
            boolean anyoneBuilt = false;
            for (int p = 1; p <= P; p++) {
                anyoneBuilt = bfs(p) || anyoneBuilt; // 주의!! bfs()를 뒤에 두면, 생략될 수 있다.
            }
            if (!anyoneBuilt) break;
        }
    }

    static boolean bfs(int pIdx) {
        boolean expanded = false;
        Queue<Pos> q = queues[pIdx];
        int size = q.size(), dist = 0;
        long maxDist = speeds[pIdx];

        while (dist++ < maxDist) {
            while (size-- > 0) {
                Pos cur = q.poll();

                for (int d = 0; d < 4; d++) {
                    int nr = cur.r + dr[d];
                    int nc = cur.c + dc[d];

                    if (!inRange(nr, nc)) continue;
                    if (map[nr][nc].v != '.') continue;
                    Pos next = map[nr][nc];
                    q.offer(next);
                    // 모든 시작지점을 동시에 돌면, map에 바로 마킹해도 상관없다.
                    next.v = (char) (pIdx + '0');
                }
            }
            size = q.size();
            if (size == 0) { // 모든 플레이어가 확장할 곳이 없는 경우에도, 최대 10^9 * 9만큼 반복될 수 있다.
                return expanded;
            }
            expanded = true;
        }
        return expanded;
    }

    static boolean inRange(int r, int c) {
        return (0 <= r && r < N) && (0 <= c && c < M);
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        speeds = new long[P+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= P; i++) {
            speeds[i] = Integer.parseInt(st.nextToken());
        }
        queues = new ArrayDeque[P+1];
        for (int i = 1; i <= P; i++) {
            queues[i] = new ArrayDeque<>();
        }

        map = new Pos[N][M];

        char one = '1';
        char nine = '9';
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                char c = line.charAt(j);
                map[i][j] = new Pos(i, j, c);
                if (one <= c && c <= nine) {
                    int pIdx = c - '0';
                    queues[pIdx].add(map[i][j]);
                }
            }
        }
    }
}
