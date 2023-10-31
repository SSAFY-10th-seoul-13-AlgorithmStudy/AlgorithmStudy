package week01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2482_G3_색상환_김태수 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		
		int[][] dp = new int[N+1][K+1];
		
		if(K > N / 2) {
			System.out.println("0");
			return;
		}
		
		//i개의 색에서 1가지를 고르는 경우는 i개
		for(int i = 1 ; i<=N;i++) dp[i][1] = i;
		
		for(int i = 4; i <= N;i++) {
			for(int j = 2; j <= K;j++) {
				//i까지에서 j개를 뽑는 경우는 
				//1) i번을 안뽑는경우 -> i-1번을 뽑지 않고 j개를 뽑는 경우 -> dp[i-1][j]
				//2) i번을 뽑는 경우 -> i-2번을 뽑고 j-1개를 선택한 경우 -> dp[i-2][j-1]
				dp[i][j] = (dp[i-1][j] + dp[i-2][j-1]) % 1_000_000_003;
			}
		}
		System.out.println(dp[N][K]);
	}
}
