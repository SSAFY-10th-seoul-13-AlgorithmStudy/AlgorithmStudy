package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_2636_G4_치즈_강이규 {

    static int R, C;
    static int cheeseCnt = 0;
    static int[][] map; // 0 : 범위 밖, 1 : 빈 공간, 2 : 치즈
    static boolean[][] visited;
    static List<Pos> targets;
    // 방향 : 상하좌우
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        init();
        solve();
        System.out.println(sb);
    }

    private static void solve() {
        int hours = 0;
        int preCnt = 0;
        while (cheeseCnt > 0) {
            preCnt = cheeseCnt;
            // init
            visited = new boolean[R+1][C+1];
            List<Pos> willMelt = new ArrayList<>();

            // marking
            for (int i = 1; i <= R; i++) {
                for (int j = 1; j <= C; j++) {
                    if (map[i][j] == 1 && !visited[i][j]) {
                        targets = new ArrayList<>();
                        // melt
                        if (marking(i, j)) {
                            willMelt.addAll(targets);
                        }
                    }
                }
            }
            melt(willMelt);
            hours++;
        }
        sb.append(hours).append("\n").append(preCnt);
    }

    private static void melt(List<Pos> willMelt) {
        for (Pos p : willMelt) {
            map[p.r][p.c] = 1;
            cheeseCnt--;
        }
    }

    private static boolean marking(int sr, int sc) {
        boolean needMelt = false;
        Queue<Pos> q = new ArrayDeque<>();

        q.offer(new Pos(sr, sc));
        visited[sr][sc] = true;

        while (!q.isEmpty()) {
            Pos cur = q.poll();

            for (int d = 0; d < 4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];
                if (map[nr][nc] == 0) {
                    needMelt = true;
                    continue;
                }
                if (visited[nr][nc]) continue;
                Pos next = new Pos(nr, nc);
                visited[nr][nc] = true;
                if (map[nr][nc] == 2) targets.add(next);
                else q.offer(next);
            }
        }
        return needMelt;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[R+2][C+2];
        for (int i = 1; i <= R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= C; j++) {
                map[i][j] = 1 + Integer.parseInt(st.nextToken());
                cheeseCnt += map[i][j] != 2 ? 0 : 1;
            }
        }
    }

    static class Pos {
        int r, c;
        Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
