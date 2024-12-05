package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16197_G4_두동전_강이규 {

    static int N, M;
    static char[][] map;
    // 방향 : 상 좌 하 우
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};
    static final int MOVE_LIMIT = 10;
    static int srA, scA, srB, scB;
    static int minMoves;

    public static void main(String[] args) throws IOException {
        init();
        dfs(0, srA, scA, srB, scB);
        System.out.println(minMoves != Integer.MAX_VALUE ? minMoves : -1);
    }

    static void dfs(int depth, int rA, int cA, int rB, int cB) {
        if (depth >= Math.min(minMoves, MOVE_LIMIT)) return;

        int newDepth = depth + 1;

        for (int d = 0; d < 4; d++) {
            int nrA = rA + dr[d];
            int ncA = cA + dc[d];
            int nrB = rB + dr[d];
            int ncB = cB + dc[d];

            if (!inRange(nrA, ncA) && !inRange(nrB, ncB)) continue;
            // 종료조건을 만족한 경우
            if (!inRange(nrA, ncA) || !inRange(nrB, ncB)) {
                minMoves = newDepth; // 메소드 초반의 depth조건으로 인해, 올 수 있는 최대값은 MOVE_LIMIT이다.
                return;
            }
            // 둘 다 범위 안
            if (map[nrA][ncA] == '#') {
                nrA -= dr[d];
                ncA -= dc[d];
            }
            if (map[nrB][ncB] == '#') {
                nrB -= dr[d];
                ncB -= dc[d];
            }
            dfs(newDepth, nrA, ncA, nrB, ncB);
        }
    }

    static boolean inRange(int r, int c) {
        return (0 <= r && r < N) && (0 <= c && c < M);
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        srA = -1;
        scA = -1;
        srB = -1;
        scB = -1;
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                char c = line.charAt(j);
                map[i][j] = c;
                if (c != 'o') continue;
                if (srA == -1) {
                    srA = i;
                    scA = j;
                } else {
                    srB = i;
                    scB = j;
                }
            }
        }
        minMoves = Integer.MAX_VALUE;
    }
}
