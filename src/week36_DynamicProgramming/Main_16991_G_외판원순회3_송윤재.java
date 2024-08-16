package week36_DynamicProgramming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_16991_G_외판원순회3_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N;
	static double city[][], dp[][], dist[][];
	
	static void init() {
		city = new double[N][2];
		dp = new double[N][(1 << N) - 1];
		dist = new double[N][N];
	}
		
	static void input() throws IOException {
		N = Integer.parseInt(br.readLine());
		init();
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			city[i][0] = Double.parseDouble(st.nextToken());
			city[i][1] = Double.parseDouble(st.nextToken());
		}
	}

	static void solve() {
		sb.append(dfs(0, 1));	
	}	
	
	static double dfs(int cur, int visit) {
		if(visit == (1 << N) - 1) { // 모든 노드 방문
			return getDistance(cur, 0);
		}
		
		if(dp[cur][visit] != 0) return dp[cur][visit];
		dp[cur][visit] = 1_000_000;
		
		for(int i = 0; i < N; i++) {
			if((visit & (1 << i)) == 0) {
				dp[cur][visit] = Math.min(dfs(i, visit | (1 << i)) + getDistance(cur, i), dp[cur][visit]);
			}
		}
		
		return dp[cur][visit];
	}
	
	static double getDistance(int a, int b) {
		return Math.sqrt(Math.pow(city[a][0] - city[b][0], 2) + Math.pow(city[a][1] - city[b][1], 2));
	}
	
	
	public static void main(String[] args) throws IOException {		
		input();
		solve();

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
