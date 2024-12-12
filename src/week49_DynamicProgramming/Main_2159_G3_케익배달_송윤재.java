package week49_DynamicProgramming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_2159_G3_케익배달_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static long dp[][];
	static int cur[], next[];
	static final long INF = 20_000_000_000L;
	static int dr[] = {0, -1, 1, 0, 0};
	static int dc[] = {0, 0, 0, -1, 1};
	
	static void init() {
		dp = new long[100_001][5];
		cur = new int[2];
		next = new int[2];
	}
	
	static void input_solve() throws IOException {
		int N = Integer.parseInt(br.readLine());
		
		init();
		
		st = new StringTokenizer(br.readLine());
		cur[0] = Integer.parseInt(st.nextToken());
		cur[1] = Integer.parseInt(st.nextToken()); // 초기 레스토랑 위치

		st = new StringTokenizer(br.readLine());
		next[0] = Integer.parseInt(st.nextToken());
		next[1] = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < 5; i++) {
			dp[1][i] = distance(cur, new int[] {next[0] + dr[i], next[1] + dc[i]});
		}			
		
		cur[0] = next[0];
		cur[1] = next[1];
		
		for(int i = 2; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			next[0] = Integer.parseInt(st.nextToken());
			next[1] = Integer.parseInt(st.nextToken());
			
			for(int j = 0; j < 5; j++) {
				dp[i][j] = dp[i - 1][0] + distance(cur, new int[] {next[0] + dr[j], next[1] + dc[j]});  
				for(int k = 1; k < 5; k++) {
					dp[i][j] = Math.min(dp[i][j], 
										dp[i - 1][k] + distance(new int[] {cur[0] + dr[k], cur[1] + dc[k]}, 
																new int[] {next[0] + dr[j], next[1] + dc[j]}));
				}
			}

			cur[0] = next[0];
			cur[1] = next[1];
		}
		long result = INF;
		for(int i = 0; i < 5; i++) {
			result = Math.min(result, dp[N][i]);
		}
		sb.append(result);
	}
	
	static long distance(int[] from, int[] to) {
		if(to[0] < 1 || to[0] > 100000 || to[1] < 1 || to[1] > 100000)
			return INF;
		else
			return Math.abs(from[0] - to[0]) + Math.abs(from[1] - to[1]);
	}

	public static void main(String[] args) throws IOException {
		input_solve();

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
