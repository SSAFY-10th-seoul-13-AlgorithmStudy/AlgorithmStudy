package week10_BackTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_15684_G3_사다리조작_강이규 {
    static int N, M, H;
    static boolean[][] ladder;

    public static void main(String[] args) throws IOException {
        init();

        for (int i = 0; i <= 3; i++) {
            recur(0, i);
        }
        System.out.println(-1);
    }

    private static boolean check() {
        for (int i = 1; i <= N; i++) {
            int col = i;
            for (int row = 1; row <= H; row++) {
                if (ladder[row][col]) col++;
                else if (ladder[row][col-1]) col--;
            }
            if (col != i) return false;
        }
        return true;
    }

    private static void recur(int curD, int maxD) {
        if (curD == maxD) {
            if (check()) {
                System.out.println(curD);
                System.exit(0);
            }
            return;
        }

        for (int j = 1; j < N; j++) {
            for (int i = 1; i <= H; i++) {
                if (ladder[i][j] || ladder[i][j-1] || ladder[i][j+1]) continue;
                ladder[i][j] = true;
                recur(curD+1, maxD);
                ladder[i][j] = false;
            }
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        ladder = new boolean[H+1][N+1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            ladder[r][c] = true;
        }
    }
}