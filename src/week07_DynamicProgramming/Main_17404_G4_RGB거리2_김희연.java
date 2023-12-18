package week07_DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17404_G4_RGB거리2_김희연 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		int[][] cost = new int[N+1][3];
		int[][] dp = new int[N+1][3];

		for(int i=1; i<=N; i++){
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<3; j++){
				cost[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int min = Integer.MAX_VALUE;

		for(int k=0; k<3; k++){
			for(int i=0; i<3; i++){
				if(k == i) dp[1][i] = cost[1][i];
				else dp[1][i] = 1000 * 1000;
			}
			for(int i=2; i<=N; i++){
				dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + cost[i][0];
				dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + cost[i][1];
				dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + cost[i][2];
			}

			for(int i=0; i<3; i++){
				if(i != k)
					min = Math.min(min, dp[N][i]);
			}
		}

		System.out.println(min);
	}
}
