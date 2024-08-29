package week37_BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14658_G3_하늘에서별똥별이빗발친다_신문영 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] stars = new int[K][2];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			stars[i][0] = Integer.parseInt(st.nextToken());
			stars[i][1] = Integer.parseInt(st.nextToken());
		}
		
		int answer = 0;
		for (int i = 0; i < K; i++) {
			for (int j = 0; j < K; j++) {
				int currentX = stars[i][0];
				int currentY = stars[j][1];
				int cnt = 0;
				
				for (int k = 0; k < K; k++) {
					if (currentX <= stars[k][0] && stars[k][0] <= currentX + L && currentY <= stars[k][1] && stars[k][1] <= currentY + L) {
						cnt++;
					}
				}
				
				answer = Math.max(answer, cnt);
			}
		}
		
		System.out.println(K - answer);
	}
}
