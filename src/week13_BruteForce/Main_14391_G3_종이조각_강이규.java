package week13_BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14391_G3_종이조각_강이규 {
    /*
    1. 현재 도형에서 4방을 돌면서,
    2. 한 변(1xN or Mx1)을 선택
    3. 선택한 변의 숫자 sum에 더하고, 남은 도형으로 재귀
    반복
     */
    static int N, M;
    static char[][] map;
    static int max = 0;

    public static void main(String[] args) throws IOException {
        init();
        recur(0, 0, N-1, M-1, 0);
        System.out.println(max);
    }

    private static void recur(int sr, int sc, int er, int ec, int sum) {
        // return 로직
        if (sr > er || sc > ec) {
            max = Math.max(max, sum);
            return;
        }
        // 선택 & 계산 로직
        for (int i = 0; i < 4; i++) {
            String cur = "";
            if (i == 0) {
                for (int j = sc; j <= ec; j++) {
                    cur += map[sr][j];
                }
                recur(sr+1, sc, er, ec, sum + Integer.parseInt(cur));
            } else if (i == 1) {
                for (int j = sr; j <= er; j++) {
                    cur += map[j][ec];
                }
                recur(sr, sc, er, ec-1, sum + Integer.parseInt(cur));
            } else if (i == 2) {
                for (int j = sc; j <= ec; j++) {
                    cur += map[er][j];
                }
                recur(sr, sc, er-1, ec, sum + Integer.parseInt(cur));
            } else {
                for (int j = sr; j <= er; j++) {
                    cur += map[j][sc];
                }
                recur(sr, sc+1, er, ec, sum + Integer.parseInt(cur));
            }
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            String row = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = row.charAt(j);
            }
        }
    }
}
