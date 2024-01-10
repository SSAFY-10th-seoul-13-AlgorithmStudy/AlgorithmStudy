package week10_BackTracking;

import java.io.*;
import java.util.*;

public class Main_14712_G5_넴모넴모Easy_김아린 {
    static int N, M, ans;
    static boolean[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new boolean[N + 1][M + 1]; // 맵 초기화

        back(1, 1);

        System.out.println(ans);
    }

    private static void back(int x, int y) {
        if (y > M) {
            y = 1;
            x++;
        }

        if (x > N) {
            ans++;
            return;
        }

        // 안놓음
        back(x, y + 1);

        // 놓음
        if (!map[x - 1][y] || !map[x][y - 1] || !map[x - 1][y - 1]) {
            map[x][y] = true;
            back(x, y + 1);
            map[x][y] = false;
        }
    }
}