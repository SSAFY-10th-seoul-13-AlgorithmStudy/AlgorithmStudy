package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_18430_G4_무기공학_강이규 {

    static int N, M;
    static int[][] map;
    static boolean[][] used;
    static int[][][] moves = {
            {{0, 1}, {1, 1}},
            {{0, 1}, {-1, 1}},
            {{1, 0}, {1, 1}},
            {{-1, 0}, {-1, 1}}
    };
    static int max = 0;


    public static void main(String[] args) throws IOException {
        init();
        recur(0, 0, 0);
        System.out.println(max);
    }

    private static void recur(int r, int c, int cur) {
        // next찾기 시 이중for문 대신 이걸로
        if (c == M) {
            c = 0;
            r++;
        }
        // 끝까지 다 선택했을 때
        if (r == N) {
            max = Math.max(max, cur);
            return;
        }

        if (!used[r][c]) {
            int v1 = map[r][c];
            for (int shape = 0; shape < 4; shape++) {
                int[][] move = moves[shape];
                int nr2 = r + move[0][0];
                int nc2 = c + move[0][1];
                int nr3 = r + move[1][0];
                int nc3 = c + move[1][1];

                if (!(isValid(nr2, nc2) && isValid(nr3, nc3)))
                    continue;

                int v2 = map[nr2][nc2]; // midVal
                int v3 = map[nr3][nc3];
                int plus = v1 + v2*2 + v3;

                used[r][c] = true;
                used[nr2][nc2] = true;
                used[nr3][nc3] = true;
                recur(r, c+1, cur+plus);
                used[r][c] = false;
                used[nr2][nc2] = false;
                used[nr3][nc3] = false;
            }
        }
        recur(r, c+1, cur);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        used = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

    }

    private static boolean isValid(int r, int c) {
        return (0 <= r && r < N) && (0 <= c && c < M)
                && !used[r][c];
    }
}
