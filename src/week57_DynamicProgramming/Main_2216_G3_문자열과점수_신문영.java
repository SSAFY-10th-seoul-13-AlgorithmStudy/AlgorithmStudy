package week57_DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2216_G3_문자열과점수_신문영 {
	static int A;
	static int B;
	static int C;
	static String X;
	static String Y;
	static int[][] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		X = br.readLine();
		Y = br.readLine();
		dp = new int[3001][3001];
		
		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[i].length; j++) {
				dp[i][j] = Integer.MIN_VALUE;
			}
		}
		
		System.out.println(dfs(0, 0));
	}
	
	static int dfs(int x, int y) {
		if (x == X.length()) {
			return B * (Y.length() - y);
		}
		
		if (y == Y.length()) {
			return B * (X.length() - x);
		}
		
		if (dp[x][y] != Integer.MIN_VALUE) {
			return dp[x][y];
		}
		
		if (X.charAt(x) == Y.charAt(y)) {
			dp[x][y] = Math.max(dp[x][y], A + dfs(x + 1, y + 1));
		} else {
			dp[x][y] = Math.max(dp[x][y], C + dfs(x + 1, y + 1));
		}
		
		dp[x][y] = Math.max(dp[x][y], B + dfs(x + 1, y));
		dp[x][y] = Math.max(dp[x][y], B + dfs(x, y + 1));
		return dp[x][y];
	}
}
