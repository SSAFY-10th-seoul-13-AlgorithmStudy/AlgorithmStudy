package BOJ;

import java.io.*;
import java.util.*;

public class Main_16432_G4_떡장수와호랑이_강이규 {
    static int N;
    static int[][] riceCakes;
    static boolean[][] visited; // 해당 칸에서 N일까지 도달할 수 있는지 여부 (false시에 쓰인다)
    static int[] result;

    public static void main(String[] args) throws IOException {
        init();
        if (dfs(0, 0) == true) {
            print();
        } else {
            System.out.println(-1);
        }
    }

    private static void print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(result[i]).append("\n");
        }
        System.out.print(sb);
    }

    private static boolean dfs(int depth, int curIdx) {
        if (depth == N) {
            return true;
        }
        if (visited[depth][curIdx]) return false;

        int cur = riceCakes[depth][curIdx];
        visited[depth][curIdx] = true;

        int nextDepth = depth + 1;
        for(int i = 0; i < 9; i++) {
            int next = riceCakes[nextDepth][i];
            if (next == 0) break;
            if (next == cur) continue;
            if (dfs(nextDepth, i) == true) {
                result[nextDepth] = next;
                return true;
            }
            // else : continue
        }
        return false;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        riceCakes = new int[N+1][9];
        visited = new boolean[N+1][9];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());

            for (int j = 0; j < m; j++) {
                riceCakes[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        result = new int[N+1];
    }
}
