package week42_DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1398_G2_동전문제_신문영 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			int answer = 0;
			for (String reversedString : new StringBuilder(br.readLine()).reverse().toString().split("(?<=\\G..)")) {
				int target = Integer.parseInt(new StringBuilder(reversedString).reverse().toString());
				int[] dp = new int[target + 1];
				dp[0] = 0;
				for (int j = 1; j <= target; j++) {
					if (j < 10) {
						dp[j] = dp[j - 1] + 1;
					} else if (j >= 10 && j < 25) {
						dp[j] = Math.min(dp[j - 1] + 1, dp[j - 10] + 1);
					} else if (j >= 25) {
						dp[j] = Math.min(dp[j - 1] + 1, Math.min(dp[j - 10] + 1, dp[j - 25] + 1));
					}
				}
				answer += dp[target];
			}
			System.out.println(answer);
		}
	}
}