package week30_DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_7579_G3_앱_신문영 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] app = new int[N + 1];
		int[] memory = new int[N + 1];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			app[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			memory[i] = Integer.parseInt(st.nextToken());
		}
		
		int answer = Integer.MAX_VALUE;
		int[][] dp = new int[N + 1][10001];
		
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < 10001; j++) {
				if (j - memory[i] >= 0) {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - memory[i]] + app[i]);
				} else {
					dp[i][j] = dp[i - 1][j];
				}
				
				if (dp[i][j] >= M) answer = Math.min(answer, j);
			}
		}
		
		System.out.println(answer);
	}

}
