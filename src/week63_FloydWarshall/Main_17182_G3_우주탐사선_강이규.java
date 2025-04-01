package BOJ;

import java.io.*;
import java.util.*;

public class Main_17182_G3_우주탐사선_강이규 {
    static int N, K;
    static int[][] costs;
    static boolean[] visited;
    static int[] selected;
    static int minTotalCost;
    static final int INF = 10001;

    public static void main(String[] args) throws IOException {
        init();
        selected[0] = K;
        visited[K] = true;
        bt(K, 1);
        System.out.println(minTotalCost);
    }

    private static void bt(int cur, int depth) {
        if (depth == N) {
            int res = 0;
            for (int i = 0, end = N - 1; i < end; i++) {
                res += costs[selected[i]][selected[i+1]];
            }
            minTotalCost = Math.min(minTotalCost, res);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            selected[depth] = i;
            bt(i, depth + 1);
            visited[i] = false;
            selected[depth] = 0;
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        costs = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                costs[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 플로이드 와샬 -> 쓰고 나면, 방문했던 곳을 다시 가고... 를 다 고려한 최소 거리를 얻을 수 있다. -> 방문체크를 할 수 있게 된다.
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    costs[i][j] = Math.min(costs[i][j], costs[i][k] + costs[k][j]);
                }
            }
        }
        visited = new boolean[N];
        selected = new int[N];
        minTotalCost = INF;
    }
}
