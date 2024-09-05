package week34_DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2169_G2_로봇조종하기_신문영 {
	static int N;
	static int M;
	static int[][] map;
	static int[][][] dp;
	static int[][] direction = {{0, 1}, {1, 0}, {0, -1}};
	static boolean[][] visited;
	static final int R_INF = -200000;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stringTokenizer.nextToken());
		M = Integer.parseInt(stringTokenizer.nextToken());
		
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			stringTokenizer = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
			}
		}
		
		visited = new boolean[N][M];
		dp = new int[N][M][3];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				dp[i][j][0] = dp[i][j][1] = dp[i][j][2] = -1;
			}
		}
		
		System.out.println(dfs(0, 0, 0));
	}
	
	static int dfs(int x, int y, int dir) {
		
		if (x == N - 1 && y == M - 1) return map[x][y];
		if (dp[x][y][dir] != -1) return dp[x][y][dir];

		visited[x][y] = true;
		
		int max = R_INF;
		for (int i = 0; i < 3; i++) {
			int nextX = x + direction[i][0];
			int nextY = y + direction[i][1];
			
			if (!(nextX >= 0 && nextY >= 0 && nextX < N && nextY < M)) continue;
			if (visited[nextX][nextY]) continue;
			
			max = Math.max(max, dfs(nextX, nextY, i) + map[x][y]);
		}
		
		visited[x][y] = false;
		
		return dp[x][y][dir] = max;
	}
}