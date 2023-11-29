package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1451_G4_직사각형으로나누기_강이규 {

    static int N, M;
    static int[][] sums;

    public static void main(String[] args) throws IOException {
        init();

        long max = 0;
        // case 1
        for (int c1 = 1; c1 <= M-2; c1++) {
            for (int c2 = c1+1; c2 <= M-1; c2++) {
                long rect1 = calSum(1,1,N,c1);
                long rect2 = calSum(1, c1+1, N, c2);
                long rect3 = calSum(1, c2+1, N, M);

                max = Math.max(max, rect1 * rect2 * rect3);
            }
        }

        // case 2
        for (int r1 = 1; r1 <= N-2; r1++) {
            for (int r2 = r1+1; r2 <= N-1; r2++) {
                long rect1 = calSum(1, 1, r1, M);
                long rect2 = calSum(r1+1, 1, r2, M);
                long rect3 = calSum(r2+1, 1, N, M);

                max = Math.max(max, rect1 * rect2 * rect3);
            }
        }

        for (int c = 1; c <= M-1; c++) {
            for (int r = 1; r <= N-1; r++) {
                // case 3
                long rect1 = calSum(1, 1, N, c);
                long rect2 = calSum(1, c+1, r, M);
                long rect3 = calSum(r+1, c+1, N, M);

                max = Math.max(max, rect1 * rect2 * rect3);

                // case 4
                rect1 = calSum(1, 1, r, c);
                rect2 = calSum(r+1, 1, N, c);
                rect3 = calSum(1, c+1, N, M);

                max = Math.max(max, rect1 * rect2 * rect3);

                // case 5
                rect1 = calSum(1, 1, r, M);
                rect2 = calSum(r+1, 1, N, c);
                rect3 = calSum(r+1, c+1, N, M);

                max = Math.max(max, rect1 * rect2 * rect3);

                // case 6
                rect1 = calSum(1, 1, r, c);
                rect2 = calSum(1, c+1, r, M);
                rect3 = calSum(r+1, 1, N, M);

                max = Math.max(max, rect1 * rect2 * rect3);
            }
        }
        System.out.println(max);
    }

    private static int calSum(int sr, int sc, int er, int ec) { // 1-based
        return sums[er][ec] - sums[sr-1][ec] - sums[er][sc-1] + sums[sr-1][sc-1];
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        sums = new int[N+1][M+1];

        for (int i = 1; i <= N; i++) {
            String line = br.readLine();
            for (int j = 1; j <= M; j++) {
                sums[i][j] = sums[i][j-1] + line.charAt(j-1) - '0'; // 가로 sum
            }
        }

        // 세로 sum
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                sums[i][j] += sums[i-1][j];
            }
        }
    }
}