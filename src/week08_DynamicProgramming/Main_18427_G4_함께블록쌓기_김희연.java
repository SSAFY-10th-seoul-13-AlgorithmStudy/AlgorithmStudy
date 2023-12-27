package week08_DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_18427_G4_ÇÔ²²ºí·Ï½×±â_±èÈñ¿¬ {

	private static final int MOD = 10007;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());

		int[][] dp = new int[N+1][1001];
		List<Integer>[] list = new ArrayList[N+1];

		for(int i=1; i<=N; i++){
			list[i] = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			while(st.hasMoreTokens()){
				list[i].add(Integer.parseInt(st.nextToken()));
			}
		}

		for(int i=0; i<=N; i++){
			dp[i][0] = 1;
		}

		for(int i=1; i<=N; i++){
			for(int j=1; j<=H; j++){
				dp[i][j] = dp[i-1][j];
				for(Integer l : list[i]){
					if(j >= l){
						dp[i][j] += dp[i-1][j-l];
						dp[i][j] %= MOD;
					}
				}
			}
		}

		System.out.println(dp[N][H]);
	}
}