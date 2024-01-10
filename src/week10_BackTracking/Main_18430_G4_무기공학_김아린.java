package week10_BackTracking;

import java.util.*;
import java.io.*;

public class Main_18430_G4_무기공학_김아린 {
    static int N, M, ans;
    static int[][] map;
    static boolean[][] visited;
    static int[][][] dir = {{ {0, -1}, {1, 0}},
                            {{-1, 0}, {0, -1}},
                            {{-1, 0}, {0, 1}},
                            {{0, 1}, {1, 0}}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        back(0, 0, 0);

        System.out.println(ans);
    }

    public static void back(int x, int y, int sum) {
        ans = Math.max(ans, sum);

        if (y == M) {
            y = 0;
            x++;
        }

        if (x == N) return;

        if (!visited[x][y]) {
            for (int c = 0; c < 4; c++) { //부메랑 4개
                int nx1 = x + dir[c][0][0];
                int ny1 = y + dir[c][0][1];
                int nx2 = x + dir[c][1][0];
                int ny2 = y + dir[c][1][1];

                if (nx1 >= 0 && nx2 >= 0 && nx1 < N && nx2 < N && ny1 >= 0 && ny2 >= 0 && ny1 < M && ny2 < M) {
                    if (visited[nx1][ny1] || visited[nx2][ny2]) continue;
                    visited[nx1][ny1] = visited[nx2][ny2] = visited[x][y] = true;

                    back(x, y + 1, sum + (map[x][y] * 2) + map[nx1][ny1] + map[nx2][ny2]);

                    visited[x][y] = visited[nx1][ny1] = visited[nx2][ny2] = false;
                }
            }
        }

        back(x, y + 1, sum);
    }
}