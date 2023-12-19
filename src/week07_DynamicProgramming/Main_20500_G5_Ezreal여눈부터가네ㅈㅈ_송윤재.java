package week07_DynamicProgramming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_20500_G5_Ezreal여눈부터가네ㅈㅈ_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int dp[][], N;
	static final int MOD = 1_000_000_007;
	
	static void init() {
		dp = new int[3][N + 1];
	}
	
	static void input() throws IOException{
		N = Integer.parseInt(br.readLine());
		init();
	}
	
	static void solve() {
		if(N == 1) {
			sb.append(0);
			return;
		}
		
		dp[0][2] = dp[1][2] = 1;

		/**
		 * 배수 판정법에 의해 15의 배수는 3의 배수이면서 5의 배수
		 * => 자리수의 총 합이 3의 배수이면서 맨 뒷자리가 5 또는 0으로 끝나는 수
		 * => 맨 뒷자리 5는 고정하고 3으로 나눈 나머지에 따라 5를 추가하거나 1을 추가하여 자리수를 늘려가면 됨 
		 */
		for (int i = 3, size = N + 1; i < size; i++) {
			dp[0][i] = (dp[1][i - 1] + dp[2][i - 1]) % MOD;
			dp[1][i] = (dp[0][i - 1] + dp[2][i - 1]) % MOD;
			dp[2][i] = (dp[0][i - 1] + dp[1][i - 1]) % MOD;
		}
		
		sb.append(dp[0][N]);
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
