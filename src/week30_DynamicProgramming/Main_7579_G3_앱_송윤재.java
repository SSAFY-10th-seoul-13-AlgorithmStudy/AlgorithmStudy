package week30_DynamicProgramming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_7579_G3_앱_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, M, m[], c[], dp[][], max_cost;
	
	static void init() {
		m = new int[N + 1];
		c = new int[N + 1];
		dp = new int[N + 1][100 * N + 1];
		
		/**
		 * dp[i][j] : 물건이 i번 일 때 j 비용으로 만들 수 있는 최대 무게
		 */		
	}
		
	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		init();
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			m[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			c[i] = Integer.parseInt(st.nextToken());
			max_cost += c[i];
		}
	}

	static void solve() {
		
		for(int i = 1; i <= N; i++) {
			for(int cost = 0; cost <= max_cost; cost++) {
				if(cost < c[i]) dp[i][cost] = dp[i - 1][cost];
				else
					dp[i][cost] = Math.max(dp[i - 1][cost], dp[i - 1][cost - c[i]] + m[i]); // i번 물건 넣을지 안넣을지				
			}
		}
		
		for(int cost = 0; cost <= max_cost; cost++) {
			if(dp[N][cost] >= M) {
				sb.append(cost);
				return;
			}
		}
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
