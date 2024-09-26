package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_17136_G2_색종이붙이기_강이규 {

    static final int N = 10;
    static int[] paperCnt = {5,5,5,5,5};
    static boolean[][] shouldFill = new boolean[N][N];
    static int maxPaperCnt = -1;

    public static void main(String[] args) throws IOException {
        init();
        dfs(0, 0);
        System.out.println((maxPaperCnt != -1) ? 25 - maxPaperCnt : maxPaperCnt);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                shouldFill[i][j] = line.charAt(j * 2) != '0';
            }
        }
    }

    private static void dfs(int r, int c) {
        if (c >= N) {
            dfs(r+1, 0);
            return;
        }
        if (r >= N) {
            updateMax();
            return;
        }

        if (!shouldFill[r][c]) dfs(r, c+1);
        else {
            for (int i = 0; i < 5; i++) {
                if (paperCnt[i] == 0) continue; // 채울 수 없는 경우면 updateMax() 호출지점까지 못 간다.
                int paperSize = 5 - i;
                if (r + paperSize - 1 >= N || c + paperSize - 1 >= N) continue;
                // check and add
                boolean added = addPaper(r, c, paperSize);
                if (!added) continue;
                paperCnt[i]--;
                // recur
                dfs(r, c + paperSize);
                // remove
                removePaper(r, c, paperSize);
                paperCnt[i]++;
            }
        }
    }

    private static void updateMax() {
        maxPaperCnt = Math.max(maxPaperCnt,
                paperCnt[0] + paperCnt[1] + paperCnt[2] + paperCnt[3] + paperCnt[4]);
    }

    private static boolean addPaper(int sr, int sc, int size) {
        // check
        for (int i = sr, er = sr + size; i < er; i++) {
            for (int j = sc, ec = sc + size; j < ec; j++) {
                if (!shouldFill[i][j]) return false;
            }
        }
        // fill
        for (int i = sr, er = sr + size; i < er; i++) {
            for (int j = sc, ec = sc + size; j < ec; j++) {
                shouldFill[i][j] = false;
            }
        }
        return true;
    }

    private static void removePaper(int sr, int sc, int size) {
        for (int i = sr, er = sr + size; i < er; i++) {
            for (int j = sc, ec = sc + size; j < ec; j++) {
                shouldFill[i][j] = true;
            }
        }
    }
}
