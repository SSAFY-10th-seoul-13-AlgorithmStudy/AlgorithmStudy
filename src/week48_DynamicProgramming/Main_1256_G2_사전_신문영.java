package week48_DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1256_G2_사전_신문영 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // a
		int M = Integer.parseInt(st.nextToken()); // z
		int K = Integer.parseInt(st.nextToken());
		
		int[][] dp = new int[101][101];
		
		for (int i = 1; i <= 100; i++) {
			dp[i][0] = dp[0][i] = 1;
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				dp[i][j] = Math.min(dp[i - 1][j] + dp[i][j - 1], 1_000_000_000);
			}
		}
		
		if (dp[N][M] < K) {
			System.out.println(-1);
			return;
		}
		
		StringBuilder stringBuilder = new StringBuilder();
		int aCount = N;
		int zCount = M;
		int len = N + M;
		for (int i = 0; i < len; i++) {
			if (aCount == 0) {
				stringBuilder.append("z");
				zCount--;
				continue;
			} else if (zCount == 0) {
				stringBuilder.append("a");
				aCount--;
				continue;
			}
			
			int as = dp[aCount - 1][zCount];
			
			if (K <= as) {
				stringBuilder.append("a");
				aCount--;
			} else {
				K -= as;
				stringBuilder.append("z");
				zCount--;
			}
		}
		
		System.out.println(stringBuilder);
	}

}
