package week27_DynamicProgramming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_1937_G3_욕심쟁이판다_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, forest[][], dp[][];
	static int dr[] = {1, -1, 0, 0};
	static int dc[] = {0, 0, 1, -1};
	
	static void init() {
		forest = new int[N][N];
		dp = new int[N][N];
	}
	
	static void input() throws IOException {
		N = Integer.parseInt(br.readLine());
		init();
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				forest[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}

	static void solve() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				dfs(i, j);
			}
		}
		
		int max = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				max = Math.max(max, dp[i][j]);
			}
		}
		sb.append(max + 1);
    }
	
	/*
	 * dp[i][j] = 4방향 중 나보다 낮고 가장 큰 놈 + 1
	 */	
	static int dfs(int r, int c) {
		if(dp[r][c] != 0) return dp[r][c];
		for(int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
			if(forest[nr][nc] < forest[r][c])
				dp[r][c] = Math.max(dp[r][c], dfs(nr, nc) + 1);
		}
		return dp[r][c];
	}
	
	/*
	 * 백트래킹 실패
	 */
//	static void dfs(int r, int c, int depth) {
//		dp[r][c] = depth;
//		
//		for(int i = 0; i < 4; i++) {
//			int nr = r + dr[i];
//			int nc = c + dc[i];
//			
//			if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
//			if(dp[nr][nc] > depth) continue;
//			
//			if(forest[nr][nc] > forest[r][c])
//				dfs(nr, nc, depth + 1);
//		}
//	}
	
	public static void main(String[] args) throws IOException {		
		input();
		solve();

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
