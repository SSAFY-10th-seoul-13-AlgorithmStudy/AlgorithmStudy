package week07_DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2629_G3_양팔저울_김태수 {
	static int N;
	static int[] weight;
	static boolean[][] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		weight = new int[N];
		dp = new boolean[N+1][40_001];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N;i++) weight[i] = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		dp(0,0);
		StringBuilder sb = new StringBuilder();
		while(T-- >0) {
			int test = Integer.parseInt(st.nextToken());
			if(dp[N][test]) sb.append("Y");
			else sb.append("N");
			sb.append(" ");
		}
		System.out.println(sb);
	}
	public static void dp(int idx, int current) {
		if(dp[idx][current]) return;
		dp[idx][current] = true;
		if(idx == N)return;
		dp(idx+1, current + weight[idx]);
		dp(idx+1,current);
		dp(idx+1, Math.abs(current - weight[idx]));
	}
}
