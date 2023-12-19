package week07_DynamicProgramming;

import java.io.BufferedReader;
import java.io.InputStreamReader;

//https://nahwasa.com/entry/%EC%9E%90%EB%B0%94-%EB%B0%B1%EC%A4%80-20500-Ezreal-%EC%97%AC%EB%88%88%EB%B6%80%ED%84%B0-%EA%B0%80%EB%84%A4-%E3%85%88%E3%85%88java

public class Main_20500_G5_Ezreal여눈부터가네ㅈㅈ_김아린 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1 << 16);
	private static final int MOD = 1000000007;

	public static void main(String[] args) throws Exception {
		int n = Integer.parseInt(br.readLine());

		long[][] dp = new long[n + 1][15];
		dp[1][1] = dp[1][5] = 1; // 초기값

		for (int i = 2; i <= n; i++) {
			for (int bf = 0; bf <= 14; bf++) {
				int tmp = bf * 10 + 1; // 나머지가 bf일 때 뒤에 1을 추가한 경우
				tmp %= 15; // 15로 나눈 나머지를 구함
				dp[i][tmp] += dp[i - 1][bf]; // 이전 단계에서 나머지가 bf인 경우의 수를 더함
				dp[i][tmp] %= MOD; // MOD로 나눈 나머지를 구함

				tmp = bf * 10 + 5; // 나머지가 bf일 때 뒤에 5를 추가한 경우
				tmp %= 15; // 15로 나눈 나머지를 구함
				dp[i][tmp] += dp[i - 1][bf]; // 이전 단계에서 나머지가 bf인 경우의 수를 더함
				dp[i][tmp] %= MOD; // MOD로 나눈 나머지를 구함
			}
		}

		System.out.println(dp[n][0]); // 길이가 n이고, 나머지가 0인 경우의 수를 출력
	}
}
