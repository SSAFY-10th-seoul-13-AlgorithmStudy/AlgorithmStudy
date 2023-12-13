package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main_1799_G1_비숍_강이규 {

    /*
    풀이 :
    black, white 서로 간에는 닿지 못하므로, 각각 나눠서 하게 되면
    재귀 최대가 100*100 -> 50*50 * 2 로 바뀐다.
     */
    static int N;
    static int bMax;
    static int wMax;
    static int[][] map;
    static List<int[]> black;
    static List<int[]> white;
    static int bSize;
    static int wSize;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        black = new ArrayList<>();
        white = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = line.charAt(2 * j) - '0';
                if (map[i][j] == 1) {
                    if ((i+j) % 2 != 0) white.add(new int[]{i, j});
                    else black.add(new int[]{i, j});
                }
            }
        }
        bSize = black.size();
        wSize = white.size();

        bDfs(0, 0);
        wDfs(0, 0);
        System.out.println(bMax + wMax);
    }

    private static void bDfs(int depth, int start) {
        if (start == bSize) {
            bMax = Math.max(bMax, depth);
            return;
        }

        if (depth + (bSize - start) < bMax) return;

        int[] next = black.get(start);
        if (isValid(next)) {
            int nr = next[0];
            int nc = next[1];
            map[nr][nc] = 2;
            bDfs(depth+1, start+1);
            map[nr][nc] = 0;
        }
        bDfs(depth, start+1);

    }

    private static void wDfs(int depth, int start) {
        if (start == wSize) {
            wMax = Math.max(wMax, depth);
            return;
        }

        if (depth + (wSize - start) < wMax) return;

        int[] next = white.get(start);
        if (isValid(next)) {
            int nr = next[0];
            int nc = next[1];
            map[nr][nc] = 2;
            wDfs(depth+1, start+1);
            map[nr][nc] = 0;
        }
        wDfs(depth, start + 1);
    }


    private static boolean isValid(int[] cur) {
        int dr[] = {-1, -1, 1, 1};
        int dc[] = {-1, 1, 1, -1};
        for(int i = 0; i < 4; i++) {
            int nr = cur[0] + dr[i];
            int nc = cur[1] + dc[i];
            while(nr >= 0 && nr < N && nc >= 0 && nc < N) {
                if(map[nr][nc] == 2) return false;
                nr += dr[i];
                nc += dc[i];
            }
        }
        return true;
    }
}
