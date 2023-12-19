package week07_DynamicProgramming;

import java.io.*;
import java.util.*;

public class Main_2629_G3_양팔저울_김아린 {
	static final int R = 40001;

	public static void main(String[] args) throws IOException {
		// 최대 가능한 경우의 수 : 2^30 (부분집합) => 10억
		// 각 추에 대해 왼쪽(-) / 오른쪽(+) / 사용하지 않는다로 나눠 생각하기
		// dp[i][j]를 i번째 추까지 이용해서 만들 수 있는가?로..
		// 그럼 만약 dp[i-1][j]번째가 true면? 무조건 dp[i][j]는 true
		// dp[i-1][j-k], dp[i-1][j+k] 얘네가 true면 dp[i-1][j]도 true

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int C = Integer.parseInt(br.readLine());

		boolean[][] check = new boolean[C + 1][R * 2];
		int[] num = new int[C + 1];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= C; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}

		check[0][0] = true;
		// DP
		for (int i = 1; i <= C; i++) {
			check[i][0] = true;

			for (int j = 0; j < R; j++) {
				check[i][j] = check[i - 1][j] || check[i - 1][Math.abs(j - num[i])] || check[i - 1][j + num[i]];
			}
		}

		int G = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < G; i++) {
			int cur = Integer.parseInt(st.nextToken());
			sb.append(check[C][cur] ? "Y " : "N ");
		}

		System.out.println(sb);
	}

}