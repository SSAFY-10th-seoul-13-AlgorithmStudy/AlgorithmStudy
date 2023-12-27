package week08_DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_16565_G2_NÆ÷Ä¿_±èÈñ¿¬ {
	private static final int MOD = 10007;
	private static int[][] combi = new int[53][53];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		for (int i = 0; i <= 52; i++) combi[i][0] = 1;
		for (int i = 1; i <= 52; i++) {
			for (int j = 1; j <= 52; j++) {
				combi[i][j] = (combi[i - 1][j] + combi[i - 1][j - 1]) % MOD;
			}
		}

		int ans = 0;
		for (int i = 1; i <= 13; i++) {
			if(N - 4 * i < 0)
				break;

			if (i % 2 == 1) {
				ans = (ans + combi[52 - 4 * i][N - 4 * i] * combi[13][i]) % MOD;
			} else {
				ans = (ans - (combi[52 - 4 * i][N - 4 * i] * combi[13][i]) % MOD + MOD) % MOD;
			}
		}
		System.out.println(ans);
	}
}