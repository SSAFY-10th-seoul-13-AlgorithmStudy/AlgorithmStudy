package week08_DynamicProgramming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_19645_G1_햄최몇_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, A[], sum;
	static boolean dp[][];

	static void init() {
		A = new int[N];
		dp = new boolean[2501][2501];
		// dp[x][y] : 선배 중 한명이 x 효용을 가질 때 다른 선배가 y 효용을 가질 수 있는지
	}

	static void input() throws IOException {
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		init();
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
			sum += A[i];
		}
	}

	static void solve() {
		int result = 0;
		dp[0][0] = true;
		for (int i = 0; i < N; i++) {
			for (int x = sum; x >= 0; x--) {
				for (int y = sum - x; y >= 0; y--) {
					if (A[i] <= x) {
						dp[x][y] |= dp[x - A[i]][y];
					}
					if (A[i] <= y) {
						dp[x][y] |= dp[x][y - A[i]];
					}
				}
			}
		}
		for (int i = 0; i <= sum; i++) {
			for (int j = 0; j <= i; j++) {
				int last = sum - i - j;
				if (dp[i][j] && (j >= last)) {
					// dp[i][j]가 true 일 때 last는 무조건 만들 수 있고 선배보다 효용이 크면 안됨
					result = Math.max(result, last);
				}
			}
		}
		sb.append(result);
	}

	public static void main(String[] args) throws IOException {
		input();
		solve();

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
