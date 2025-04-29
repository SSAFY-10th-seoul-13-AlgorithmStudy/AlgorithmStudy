package week63_FloydWarshall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17182_G3_우주탐사선_신문영 {
	static int N;
	static int K;
	static int answer = Integer.MAX_VALUE;
	static int[][] distance;
	static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        distance = new int[N][N];
        for (int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				distance[i][j] = Integer.parseInt(st.nextToken());
			}
		}
        
        dp = new int[N][1 << N];
        for (int i = 0; i < N; i++) {
			for (int j = 0; j < 1 << N; j++) {
				dp[i][j] = Integer.MAX_VALUE;
			}
		}
        
        dp[K][1 << K] = 0;
        backtracking(K, 1 << K, 0);
        
        System.out.println(answer);
    }
    
    static void backtracking(int current, int visited, int totalDistance) {
    	if (visited == (1 << N) - 1) {
    		answer = Math.min(answer, totalDistance);
    		return;
    	}
    	
    	for (int i = 0; i < N; i++) {
			if (i == current) continue;
			
			if (dp[i][visited | 1 << i] <= totalDistance + distance[current][i]) continue;
			
			dp[i][visited | 1 << i] = totalDistance + distance[current][i];
			
			backtracking(i, visited | 1 << i, dp[i][visited | 1 << i]);
		}
    }
}
