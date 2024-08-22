package week36_DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_16991_G1_외판원순회3_신문영 {
	static int N;
	static double[][] dp;
	static double[][] lengthMap;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stringTokenizer.nextToken());

		int[][] cities = new int[N][2];
		for (int i = 0; i < N; i++) {
			stringTokenizer = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(stringTokenizer.nextToken());
			int y = Integer.parseInt(stringTokenizer.nextToken());
			cities[i][0] = x;
			cities[i][1] = y;
		}
		
		dp = new double[N][1 << N];
		lengthMap = new double[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int diffX = cities[i][0] - cities[j][0];
				int diffY = cities[i][1] - cities[j][1];
				lengthMap[i][j] = Math.sqrt(diffX * diffX + diffY * diffY);
			}
			
			Arrays.fill(dp[i], Double.MAX_VALUE);
		}
		
		
		System.out.println(tps(0, 1));
	}
	
	static double tps(int current, int visited) {
		if (visited == ((1 << N) - 1)) {
			return lengthMap[current][0];
		}
		
		if (dp[current][visited] != Double.MAX_VALUE) {
			return dp[current][visited];
		}
		
		
		for (int next = 0; next < N; next++) {
			int nextBit = 1 << next; 
			
			if ((visited & nextBit) != 0) continue;
	
			dp[current][visited] = Math.min(dp[current][visited], tps(next, visited | nextBit) + lengthMap[current][next]);
		}
		
		return dp[current][visited];
	}
}
