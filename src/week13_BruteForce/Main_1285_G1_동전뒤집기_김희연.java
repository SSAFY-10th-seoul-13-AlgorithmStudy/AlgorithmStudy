package week13_BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1285_G1_동전뒤집기_김희연 {
	static char[][] coin;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		coin = new char[n][n];

		for(int i=0; i<n; i++){
			String str = br.readLine();
			for(int j=0; j<n; j++){
				coin[i][j] = str.charAt(j);
			}
		}

		int answer = Integer.MAX_VALUE;

		for(int i=0; i<(1<<n); i++){
			int sum = 0;
			for(int j=0; j<n; j++){
				int count = 0;
				for(int k=0; k<n; k++){
					char now = coin[k][j];

					if((i & (1<<k)) != 0){
						now = reverse(k, j);
					}

					if(now == 'T')
						count++;
				}
				sum += Math.min(count, n-count);
			}
			answer = Math.min(answer, sum);
		}

		System.out.println(answer);
	}

	public static char reverse(int y, int x){
		if(coin[y][x] == 'T')
			return 'H';
		else
			return 'T';
	}
}