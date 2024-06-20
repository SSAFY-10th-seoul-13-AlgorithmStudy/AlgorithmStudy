package week27_DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1937_G3_욕심쟁이판다_신문영 {
	static int[][] dp;
	static int[][] bamboo;
	static int[][] direction = 
			{
				{0, 1},
				{0, -1},
				{1, 0},
				{-1, 0},
			};
	static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		dp = new int[n][n];
		bamboo = new int[n][n];
		for (int i = 0; i < bamboo.length; i++) {
			StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
			for (int j = 0; j < bamboo.length; j++) {
				bamboo[i][j] = Integer.parseInt(stringTokenizer.nextToken());
			}
		}
		
		for (int i = 0; i < bamboo.length; i++) {
			for (int j = 0; j < bamboo.length; j++) {
				dfs(i, j, 1);
			}
		}
		
		int answer = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				answer = Math.max(answer, dp[i][j]);
			}
		}
		
		System.out.println(answer);
	}
	
	static int dfs(int i, int j, int move) {
		int answer = move;
		
		for (int k = 0; k < direction.length; k++) {
			int nextI = i + direction[k][0];
			int nextJ = j + direction[k][1];
			if (nextI >= 0 && nextJ >= 0 && nextI < dp.length && nextJ < dp.length && bamboo[nextI][nextJ] > bamboo[i][j]) {
				if (dp[nextI][nextJ] == 0) {
					answer = Math.max(answer, dfs(nextI, nextJ, move + 1));
				} else {
					answer = Math.max(answer, move + dp[nextI][nextJ]);
				}
			}
		}
		
		dp[i][j] = answer - move + 1;
		return answer;
	}
}