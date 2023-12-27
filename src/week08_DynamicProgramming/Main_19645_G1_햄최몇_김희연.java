package week08_DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_19645_G1_«‹√÷∏Ó_±Ë»Òø¨ {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		int[] a = new int[N+1];
		int sum = 0;
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++){
			a[i] = Integer.parseInt(st.nextToken());
			sum += a[i];
		}

		boolean[][] dp = new boolean[sum+1][sum+1];
		dp[0][0] = true;
		for(int i=1; i<=N; i++){
			for(int x=sum; x>=0; x--){
				for(int y=sum; y>=0; y--){
					if(x + y <= sum) {
						if (x - a[i] >= 0)
							dp[x][y] |= dp[x - a[i]][y];
						if (y - a[i] >= 0)
							dp[x][y] |= dp[x][y - a[i]];
					}
				}
			}
		}

		int answer = 0;
		for(int i=0; i<=sum; i++){
			for(int j=0; j<=i; j++){
				int last = sum - i - j;
				if(dp[i][j] && (j >= last)){
					answer = Math.max(answer, last);
				}
			}
		}

		System.out.println(answer);
	}
}