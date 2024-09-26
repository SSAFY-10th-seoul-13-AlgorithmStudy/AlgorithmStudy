package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_18808_G3_스티커붙이기_강이규 {

    static int N, M, K;
    static boolean[][] filled;
    static Sticker[] stickers;
    static int filledCnt = 0;

    public static void main(String[] args) throws IOException {
//        filledCnt = 0;
        init();
        solve();
//            print();
        System.out.println(filledCnt);
//        while (true) {
//        }
    }
//
//    private static void print() {
//        for (boolean[] row : filled) {
//            for (boolean col : row) {
//                System.out.print((col ? 1 : 0) + " ");
//            }
//            System.out.println();
//        }
//    }

    private static void solve() {
        StickerLoop: for (Sticker s : stickers) {
            for (int rotateCnt = 0; rotateCnt < 4; rotateCnt++) {
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < M; j++) {
                        if (i + s.r > N || j + s.c > M) continue;
                        boolean added = checkAndAdd(i, j, s);
                        if (added) continue StickerLoop;
                    }
                }
                s.rotate();
            }
        }
    }

    private static boolean checkAndAdd(int sr, int sc, Sticker s) {
        List<int[]> willBeFilled = new ArrayList<>(s.filledCells);
        // check
        for (int i = sr, er = sr + s.r; i < er; i++) {
            for (int j = sc, ec = sc + s.c; j < ec; j++) {
                boolean mapFilled = filled[i][j];
                boolean stickerFilled = s.filled[i - sr][j - sc];
                if (!stickerFilled) continue;
                // 붙일 수 없을 때
                if (mapFilled) return false;
                // 있으면, 리스트에 추가
                willBeFilled.add(new int[]{i, j});
            }
        }
        // add
        for (int[] pos : willBeFilled) {
            filled[pos[0]][pos[1]] = true;
        }
        // update cnt
        filledCnt += s.filledCells;
        return true;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        filled = new boolean[N][M];
        stickers = new Sticker[K];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            boolean[][] originShape = new boolean[r][c];
            int filledCells = 0;

            for (int row = 0; row < r; row++) {
                String line = br.readLine();
                for (int col = 0; col < c; col++) {
                    originShape[row][col] = line.charAt(col * 2) == '1';
                    if (originShape[row][col]) filledCells++;
                }
            }
            stickers[i] = new Sticker(r, c, originShape, filledCells);
        }
    }


    static class Sticker {
        int r, c;
        int filledCells;
        boolean[][] filled;

        Sticker(int r, int c, boolean[][] originShape, int filledCells) {
            this.r = r;
            this.c = c;
            this.filledCells = filledCells;
            filled = originShape;
        }

        private void rotate() {
            int r = filled.length;
            int c = filled[0].length;
            boolean[][] newShape = new boolean[c][r];
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    newShape[j][r - 1 - i] = filled[i][j];
                }
            }
            filled = newShape;
            this.r = c;
            this.c = r;
        }
    }
}
