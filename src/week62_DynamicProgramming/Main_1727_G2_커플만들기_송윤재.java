package week62_DynamicProgramming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1727_G2_커플만들기_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int n, m, men[], women[], dp[][];
	
	static void init() {
		men = new int[n + 1];
		women = new int[m + 1];
		dp = new int[n + 1][m + 1];
	}
	
	static void input() throws IOException{
		 st = new StringTokenizer(br.readLine());
		 n = Integer.parseInt(st.nextToken());
		 m = Integer.parseInt(st.nextToken());
		 init();
		 st = new StringTokenizer(br.readLine());
		 for(int i = 1; i <= n; i++) {
			 men[i] = Integer.parseInt(st.nextToken());
		 }
		 st = new StringTokenizer(br.readLine());
		 for(int i = 1; i <= m; i++) {
			 women[i] = Integer.parseInt(st.nextToken());
		 }
	}
	
	static void solve() {
		Arrays.sort(men);
		Arrays.sort(women);
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= m; j++) {
				dp[i][j] = dp[i - 1][j - 1] + Math.abs(men[i] - women[j]);
				if(i > j) dp[i][j] = Math.min(dp[i][j], dp[i - 1][j]);
				else if(j > i) dp[i][j] = Math.min(dp[i][j], dp[i][j - 1]);
			}
		}
		sb.append(dp[n][m]);
	}
	
	public static void main(String[] args) throws IOException{
		input();
		solve();
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
