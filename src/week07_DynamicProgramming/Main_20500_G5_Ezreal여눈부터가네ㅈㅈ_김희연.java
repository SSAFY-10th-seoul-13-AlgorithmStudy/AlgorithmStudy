package week07_DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_20500_G5_Ezreal여눈부터가네ㅈㅈ_김희연 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long MOD = 1000000007L;
		int N = Integer.parseInt(br.readLine());

		if(N == 1){
			System.out.println("0");
			return;
		}

		long[][] dp = new long[3][N+1];
		dp[0][2] = dp[1][2] = 1;

		for(int i=3; i<=N; i++){
			dp[0][i] = (dp[1][i-1] + dp[2][i-1]) % MOD;
			dp[1][i] = (dp[0][i-1] + dp[2][i-1]) % MOD;
			dp[2][i] = (dp[0][i-1] + dp[1][i-1]) % MOD;
		}

		System.out.println(dp[0][N]);
	}
}
