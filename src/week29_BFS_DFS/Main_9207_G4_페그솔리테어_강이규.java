package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_9207_G4_페그솔리테어_강이규 {

    static final int R = 5;
    static final int C = 9;
    static char[][] map;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};
    static int minTime;
    static int minCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            // init
            map = new char[R][C];
            minTime = Integer.MAX_VALUE;
            minCnt = Integer.MAX_VALUE;
            int sCnt = 0;

            for (int i = 0; i < R; i++) {
                String line = br.readLine();
                for (int j = 0; j < C; j++) {
                    map[i][j] = line.charAt(j);
                    sCnt += map[i][j] == 'o' ? 1 : 0;
                }
            }
            // dfs
            dfs(sCnt, 0);
            sb.append(minCnt).append(" ").append(minTime).append("\n");
            // 빈 줄 제거
            if (T != 0) br.readLine();
        }
        System.out.println(sb);
    }

    private static void dfs(int cnt, int time) {
        if (cnt < minCnt) {
            minCnt = cnt;
            minTime = time;
        } else if (cnt == minCnt && time < minTime) {
            minTime = time;
        }
        if (cnt == 0) return;

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                char cur = map[r][c];
                if (cur != 'o') continue;
                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    int nr2 = nr + dr[d];
                    int nc2 = nc + dc[d];
                    if (!isValid(nr2, nc2)) continue;
                    if (map[nr][nc] != 'o') continue;
                    // 이동
                    map[r][c] = '.';
                    map[nr2][nc2] = 'o';
                    map[nr][nc] = '.';
                    cnt--;
                    // 재귀
                    dfs(cnt, time + 1);
                    // 복구
                    cnt++;
                    map[nr][nc] = 'o';
                    map[nr2][nc2] = '.';
                    map[r][c] = 'o';
                }
            }
        }
    }

    private static boolean isValid(int r, int c) {
        return (0 <= r && r < R) && (0 <= c && c < C)
                && map[r][c] == '.';
    }


}
