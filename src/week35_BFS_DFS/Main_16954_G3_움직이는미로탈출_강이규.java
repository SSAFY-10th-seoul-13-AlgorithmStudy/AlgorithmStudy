package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main_16954_G3_움직이는미로탈출_강이규 {

    static final int N = 8;
    static char[][] map;
    // 방향 : 상, 우상, 우, 우하, 하, 좌하, 좌, 좌상, 제자리
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1, 0};
    static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1, 0};
    static List<Pair> walls = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        init();
        dfs(N-1, 0, 0);
        System.out.println(0);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new char[8][8];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                char c = line.charAt(j);
                map[i][j] = c;
                if (c != '.') walls.add(new Pair(i, j));
            }
        }
    }

    private static void dfs(int r, int c, int cnt) {
        if ((r == 0 && c == N - 1) || cnt == 8) { // 넘어가면 갈 수 있는 것. 벽 다 없으므로
            System.out.println(1);
            System.exit(0);
        }

        for (int d = 0; d < 9; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if (!isValid(nr, nc)) continue;
            fall();
            dfs(nr, nc, cnt + 1);
            recoverFall();
        }
    }

    private static void recoverFall() {
        // 빈칸으로 바꾸고 이동
        for (Pair wall : walls) {
            if (wall.r < N) map[wall.r][wall.c] = '.';
        }
        // 벽 마킹
        for (Pair wall : walls) {
            if (--wall.r < N) map[wall.r][wall.c] = '#';
        }
    }

    private static void fall() {
        // 빈칸으로 바꾸고 이동
        for (Pair wall : walls) {
            if (wall.r < N) map[wall.r][wall.c] = '.';
        }
        // 벽 마킹
        for (Pair wall : walls) {
            if (++wall.r < N) map[wall.r][wall.c] = '#';
        }
    }

    private static boolean isValid(int r, int c) {
        boolean movable = (0 <= r && r < N) && (0 <= c & c < N) && map[r][c] != '#'; // 범위 안이고, 빈칸이면
        if (movable && r != 0 && map[r-1][c] == '#') return false; // 벽이 떨어질 칸이면 이동 X
        return movable;
    }

    static class Pair {
        int r, c;
        Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
