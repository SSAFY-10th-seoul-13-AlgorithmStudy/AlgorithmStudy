package week08_DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2011_G5_암호코드_김태수 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String st = br.readLine();
		int MOD = 1_000_000;
		int N = st.length();
		int[] code = new int[N+1];
		for(int i = 1;i<=N;i++) {
			code[i] = st.charAt(i-1) - '0';
		}
		
		long[] dp = new long[N+1];
		dp[0] =1;
		if(code[1] > 0) dp[1] = 1;
		for(int i = 2; i <=N;i++) {
			if(code[i]>0 && code[i] <= 9) {
				dp[i] += (dp[i-1]);
				dp[i] %= MOD;
			}
			if(code[i-1] == 0) continue;
			long codelet = code[i-1] * 10 + code[i];
			if(codelet >= 10 && codelet <= 26) {
				dp[i] += (dp[i-2]);
				dp[i] %= MOD;
			} 
		}
		System.out.println(dp[N]);
 	}
}
