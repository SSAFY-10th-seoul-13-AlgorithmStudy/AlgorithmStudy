package week10_BackTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_15684_G3_사다리조작_김태수 {
	static int n, m, h;
    static boolean[][] ladderPos;
    static int ladderCnt;
    static boolean flag = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        ladderPos = new boolean[31][11];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            ladderPos[a - 1][b] = true;
        }

        for (int i = 0; i <= 3; i++) {
            ladderCnt = i;
            dfs(0, 0);
            if (flag) {
                System.out.println(ladderCnt);
                return;
            }
        }

        System.out.println(-1);
    }
    static boolean check() {
    	for (int i = 1; i <= n; i++) {
            int start = i;
            for (int j = 0; j < h; j++) {
                int height = j;
                if (ladderPos[height][start]) start++; 
                else if (start > 1 && ladderPos[height][start - 1]) start--;
            }
            if (start != i) return false;
        }
    	return true;
    }

    static void dfs(int y, int cnt) {
        if (ladderCnt == cnt) {
            if(check()) flag = true;
            return;
        }
        for (int i = y; i < h; i++) {
            for (int j = 1; j < n; j++) {
                if (!ladderPos[i][j - 1] && !ladderPos[i][j] && !ladderPos[i][j + 1]) {
                    ladderPos[i][j] = true; 
                    dfs(i, cnt + 1); 
                    ladderPos[i][j] = false; 
                }
            }
        }
    }
}
