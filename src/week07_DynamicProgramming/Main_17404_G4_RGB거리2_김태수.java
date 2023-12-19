package week07_DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_17404_G4_RGB거리2_김태수 {
	static public int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		int[][] colors = new int[N+1][3];
		int minVal = Integer.MAX_VALUE;
		StringTokenizer st;
		for(int i = 1 ; i <= N;i++ ) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 3;j++) {
				colors[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0; i < 3;i++) {
			int[][] dp = new int[N+1][3];
			for(int[] row: dp) Arrays.fill(row, 1001);
			dp[1][i] = colors[1][i];
			for(int j = 2; j <= N;j++) {
				dp[j][0] = Math.min(dp[j-1][1], dp[j-1][2]) + colors[j][0];
				dp[j][1] = Math.min(dp[j-1][0], dp[j-1][2]) + colors[j][1];
				dp[j][2] = Math.min(dp[j-1][1], dp[j-1][0]) + colors[j][2];
			}
			for(int j = 0 ; j< 3;j++) {
				if (i == j) continue;
				minVal = Math.min(minVal, dp[N][j]);
			}
		}
		System.out.println(minVal);
	}
	
	
}
