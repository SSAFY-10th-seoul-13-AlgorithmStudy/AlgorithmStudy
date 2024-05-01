package programmers.kakao;

import java.util.*;
import java.io.*;

class Solution_72413_Level3_택시합승요금_강이규 {

    static int N, S, A, B;
    static int[][] costs;

    public int solution(int n, int s, int a, int b, int[][] fares) {
        init(n, s, a, b, fares);
        fw();
        return findMin();
    }

    private void init(int n, int s, int a, int b, int[][] fares) {
        N = n;
        S = s;
        A = a;
        B = b;
        costs = new int[N+1][N+1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(costs[i], Integer.MAX_VALUE);
            costs[i][i] = 0;
        }

        for (int i = 0, end = fares.length; i < end; i++) {
            int[] cur = fares[i];
            int x = cur[0];
            int y = cur[1];
            int cost = cur[2];
            costs[x][y] = costs[y][x] = cost;
        }
    }

    private void fw() {
        for (int k = 1; k <= N; k++) { // 중간 노드
            for (int i = 1; i <= N; i++) {
                if (i == k)
                    continue;
                for (int j = i+1; j <= N; j++) {
                    if (j == k || j == i)
                        continue;
                    long newCost = ((long)costs[i][k]) + costs[k][j];
                    // 비교 후 업데이트
                    if (costs[i][j] > newCost) {
                        costs[i][j] = (int) newCost;
                        costs[j][i] = (int) newCost;
                    }
                }
            }
        }
    }

    private int findMin() {
        int min = Integer.MAX_VALUE;

        // 중간노드 탐색
        for (int i = 1; i <= N; i++) {
            // if (i == S || i == A || i == B)
            //     continue;
            min = Math.min(min, costs[S][i] + costs[i][A] + costs[i][B]);
        }
        // // s -> a -> b
        // min = Math.min(min, costs[S][A] + costs[A][B]);
        // // s -> b -> a
        // min = Math.min(min, costs[S][B] + costs[B][A]);
        // // 처음부터 따로
        // min = Math.min(min, costs[S][A] + costs[S][B]);

        return (int) min;
    }
}