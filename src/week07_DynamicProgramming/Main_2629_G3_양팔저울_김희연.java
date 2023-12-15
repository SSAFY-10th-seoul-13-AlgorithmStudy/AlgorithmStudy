package week07_DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2629_G3_양팔저울_김희연 {
	static int N, M;
	static int[] arr;
	static boolean[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		arr = new int[N+1];
		dp = new boolean[31][15001];

		st = new StringTokenizer(br.readLine());

		for(int i=1; i<=N; i++){
			arr[i] = Integer.parseInt(st.nextToken());
		}

		dp(0, 0);

		M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<M; i++){
			int x = Integer.parseInt(st.nextToken());
			if(x > 15000) sb.append("N ");
			else {
				sb.append((dp[N][x]) ? "Y " : "N ");
			}
		}
		System.out.println(sb);
	}

	public static void dp(int idx, int weight){
		if(dp[idx][weight])
			return;
		dp[idx][weight] = true;
		if(idx==N)
			return;

		dp(idx+1, weight);
		dp(idx+1, weight+arr[idx+1]);
		dp(idx+1, Math.abs(weight-arr[idx+1]));
	}
}