package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public abstract class Main_13460_G1_구슬탈출2_강이규 {

    static class Pos {
        int r, c;
        Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static class Status {
        int rr, rc, br, bc, cnt;
        public Status(int rr, int rc, int br, int bc, int cnt) {
            this.rr = rr;
            this.rc = rc;
            this.br = br;
            this.bc = bc;
            this.cnt = cnt;
        }
    }

    static int N, M;
    static char[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static Pos redPos;
    static Pos bluePos;

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(bfs());
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                char c = line.charAt(j);
                map[i][j] = c;
                if (c == 'R')
                    redPos = new Pos(i,j);
                else if (c == 'B')
                    bluePos = new Pos(i, j);
            }
        }
    }

    private static int bfs() {
        Queue<Status> q = new ArrayDeque<>();
        q.offer(new Status(redPos.r, redPos.c, bluePos.r, bluePos.c, 0));
        while (!q.isEmpty()) {
            Status cur = q.poll();
            for (int d = 0; d < 4; d++) {
                setStatus(cur);
                boolean redFall = move(d, 0);
                int nr = redPos.r + dr[d];
                int nc = redPos.c + dc[d];
                if (!redFall && map[nr][nc] == 'B') { // 진행방향 기준 앞에 Blue, 뒤에 Red가 있고, 사이에 구멍이 없는 경우
                    if (move(d, 1)) {
                        continue;
                    }
                    redFall = move(d, 0);
                } else {
                    if (move(d, 1)) {
                        continue;
                    }
                }
                if (redFall) {
                    return cur.cnt + 1;
                }
                if (cur.cnt < 9) {
                    q.offer(new Status(redPos.r, redPos.c, bluePos.r, bluePos.c, cur.cnt+1));
                }
            }
        }
        return -1;
    }

    private static void setStatus(Status s) {
        map[redPos.r][redPos.c] = '.';
        map[bluePos.r][bluePos.c] = '.';
        redPos.r = s.rr;
        redPos.c = s.rc;
        bluePos.r = s.br;
        bluePos.c = s.bc;
        map[redPos.r][redPos.c] = 'R';
        map[bluePos.r][bluePos.c] = 'B';
    }

    // true = 구멍에 빠진 경우
    private static boolean move(int d, int type) {

        Pos target = type == 0 ? redPos : bluePos;
        int r = target.r;
        int c = target.c;
        int nr = r + dr[d];
        int nc = c + dc[d];
        boolean fall = false;

        while (movable(nr, nc)) {
            // 이동
            r = nr;
            c = nc;
            // 구멍인지 체크
            if (isHole(r, c)) {
                map[target.r][target.c] = '.';
                return true;
            }
            nr = r + dr[d];
            nc = c + dc[d];
        }
        map[target.r][target.c] = '.';
        target.r = r;
        target.c = c;
        map[target.r][target.c] = type == 0 ? 'R' : 'B';
        return fall;
    }

    private static boolean isHole(int r, int c) {
        return map[r][c] == 'O';
    }

    private static boolean movable(int r, int c) {
        char cur = map[r][c];
        return cur == '.' || cur == 'O';
    }
}
