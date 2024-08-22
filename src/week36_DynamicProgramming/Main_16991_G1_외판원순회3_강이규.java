package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_16991_G1_외판원순회3_강이규 {

    static int N;
    static int[][] city;
    static double[][] adjMatrix;
    static double[][] dp;
    static final double INF = 100_000;
    static int maxFlag;
    static int start;

    public static void main(String[] args) throws IOException {
        init();
        double min = INF;
        for (int i = 0; i < N; i++) {
            start = i;
            min = Math.min(min, dfs(i, 1<<i));
        }
        System.out.println(min);
//        System.out.println(dfs(0, 1));
    }

    private static double dfs(int cur, int visited) {
        // 종료 조건 : 다 한번씩 방문했을 때
        if (visited == maxFlag) {
            return adjMatrix[cur][start];
        }
        // 중복 visited 방지
        if (dp[cur][visited] != INF) {
            return dp[cur][visited];
        }
        // 첫 방문이라면, 아직 방문하지 않은 도시들을 돌아보는 모든 경우의 수를 탐색하고, 최소 거리인 경우를 현재값으로
        for (int i = 0; i < N; i++) {
            if ((visited & 1<<i) != 0) continue;
//            if (adjMatrix[cur][i] == 0) continue;
            dp[cur][visited] = Math.min(dp[cur][visited], dfs(i, visited | 1<<i) + adjMatrix[cur][i]);
        }
        return dp[cur][visited];
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        city = new int[N][2];
        adjMatrix = new double[N][N];
        dp = new double[N][1<<N]; // N개의 비트로 표현 가능한 경우의 수만큼은 넘어야.
        maxFlag = (1 << N) - 1;

        // 0번째 : 입력만
        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        city[0][0] = x;
        city[0][1] = y;

        // 입력 & 거리 계산
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            city[i][0] = x;
            city[i][1] = y;
            for (int j = 0; j < i; j++) {
                double xGapPow = Math.pow(city[i][0] - city[j][0], 2);
                double yGapPow = Math.pow(city[i][1] - city[j][1], 2);
                adjMatrix[i][j] = adjMatrix[j][i] = Math.sqrt(xGapPow + yGapPow);
            }
        }
        // dp배열 방문표시를 위한 마킹
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], INF);
        }
    }
}
