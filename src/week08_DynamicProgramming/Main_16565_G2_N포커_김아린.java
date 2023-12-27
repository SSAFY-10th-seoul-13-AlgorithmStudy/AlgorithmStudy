package week08_DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_16565_G2_N포커_김아린 {
	static final int MOD = 10_007;
	static int comb[][] = new int[53][53];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 조합과 배제의 원리..?
		// https://restudycafe.tistory.com/487
		// 포카드를 뽑는 경우의 수는 1세트 이상 뽑는 경우의 수 - 2세트 이상 뽑는 경우의 수 + 3세트 이상 뽑는 경우의 수

		// 초기화
		for (int i = 0; i <= 52; i++)
			comb[i][0] = 1;

		// comb[i][j]는 i개 중에서 j개를 선택하는 조합의 수
		// nCr = n-1Cr + n-1Cr-1 
		for (int i = 1; i <= 52; i++)
			for (int j = 1; j <= 52; j++)
				comb[i][j] = (comb[i - 1][j] + comb[i - 1][j - 1]) % MOD;

		int N = Integer.parseInt(br.readLine());
		int ans = 0;

		// 플러시가 나오는 경우의 수 계산
		// 무늬 별로 13장 중에서 5장 이상을 선택하는 경우의 수..... 즉 13C5 + 13C6 + 13C7 + ... + 13C13
		// 근데 2번 이상 나오는 경우도 포함되니까, 홀수 번째 + , 짝수 번째 - 이면 플러시가 2번 이상 나오는 경우를 제외 ㄱㄴ
		for (int i = 1; i <= 13 && N - 4 * i >= 0; i++) {
			if (i % 2 == 1) {
				// comb[52 - 4 * i]
				// 전체 52장의 카드 중에서 특정 무늬의 카드를 제외한 나머지 카드들 중에서 (N - 4 * i)장의 카드를 선택하는 조합의 수
				// 여기서 '4 * i'는 특정 무늬의 카드 i장을 선택한다는 것
				// 즉, 플러시가 아닌 카드들 중에서 카드를 선택하는 경우의 수
				// comb[13][i]
				// 특정 무늬의 카드 13장 중에서 i장의 카드를 선택하는 조합의 수
				ans = (ans + comb[52 - 4 * i][N - 4 * i] * comb[13][i]) % MOD;
			} else
				ans = (ans - (comb[52 - 4 * i][N - 4 * i] * comb[13][i]) % MOD + MOD) % MOD;
		}

		System.out.println(ans);
	}
}
