package week08_DynamicProgramming;

import java.io.*;
import java.util.*;

public class Main_18427_G4_함께블록쌓기_김아린 {
	static final int MOD = 10007;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		
		//1,2,3..번 학생의 1,2,3...번 블럭을 쓰는지 / 안쓰는지로 나눠서 생각하기
		int[][] dp = new int[51][1001];
		
		ArrayList<Integer>[] list = new ArrayList[51];
		for(int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			while(st.hasMoreTokens()) {
				list[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		
		//초기화를 잘하자.....
		for(int i = 0; i <= N; i++)
			dp[i][0] = 1;
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= H; j++) {
				for (int tmp : list[i]) {
					if (j >= tmp) {
						//쌓은 경우
						dp[i][j] += dp[i - 1][j - tmp];
					}
				}
				//안 쌓은 경우 
				dp[i][j] += dp[i - 1][j];
				dp[i][j] %= MOD;
			}
		}
		System.out.println(dp[N][H]%MOD);
 	}
}
