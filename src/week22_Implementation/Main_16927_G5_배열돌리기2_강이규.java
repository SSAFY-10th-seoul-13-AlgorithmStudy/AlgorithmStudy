package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_16927_G5_배열돌리기2_강이규 {

    static int N, M, R;
    static int[][] input;
    static int[][] result;

    public static void main(String[] args) throws IOException {
        init();
        rotate();
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        input = new int[N][M];
        result = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                input[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static void rotate() {
        int L = Math.min(N, M) / 2;
        for (int l = 0; l < L; l++) {
            rotate(l);
        }
        print();
    }

    private static void print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(result[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void rotate(int layer) {
        int[] elems = insertElems(layer);
        putElems(elems, layer);

    }

    private static int[] insertElems(int layer) {
        // 담기(시계방향으로)
        int arrLen = (M - 2*layer) * 2 + (N - 2*(layer+1)) * 2;
        int[] elems = new int[arrLen];
        int elemIdx = 0;
        // 윗변
        for (int c = layer, end = M - layer; c < end; c++) {
            elems[elemIdx++] = input[layer][c];
        }
        // 우변
        int col = M - layer - 1;
        for (int r = layer+1, end = N - layer - 1; r < end; r++) {
            elems[elemIdx++] = input[r][col];
        }
        // 아랫변
        int row = N - layer - 1;
        for (int c = M - layer - 1; c >= layer; c--) {
            elems[elemIdx++] = input[row][c];
        }
        // 좌변
        for (int r = N - layer - 2; r > layer; r--) {
            elems[elemIdx++] = input[r][layer];
        }
        return elems;
    }

    private static void putElems(int[] elems, int layer) {
        int newIdx = (R % elems.length);
        int len = elems.length;
        // 윗변
        for (int c = layer, end = M - layer; c < end; c++) {
            result[layer][c] = elems[newIdx++];
            if (newIdx == len) newIdx = 0;
        }
        // 우변
        int col = M - layer - 1;
        for (int r = layer+1, end = N - layer - 1; r < end; r++) {
            result[r][col] = elems[newIdx++];
            if (newIdx == len) newIdx = 0;
        }
        // 아랫변
        int row = N - layer - 1;
        for (int c = M - layer - 1; c >= layer; c--) {
            result[row][c] = elems[newIdx++];
            if (newIdx == len) newIdx = 0;
        }
        // 좌변
        for (int r = N - layer - 2; r > layer; r--) {
            result[r][layer] = elems[newIdx++];
            if (newIdx == len) newIdx = 0;
        }
    }

}
