package week57_BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1079_G2_마피아_신문영 {
	static int N;
	static int answer = 0;
	static int eunjin;
	static int[] guilty;
	static int[][] R;
	static boolean[] isDead;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		guilty = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < guilty.length; i++) {
			guilty[i] = Integer.parseInt(st.nextToken());
		}
		R = new int[N][N];
		for (int i = 0; i < guilty.length; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < guilty.length; j++) {
				R[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		eunjin = Integer.parseInt(br.readLine());
		isDead = new boolean[N];
		dfs(N, 0);
		System.out.println(answer);
	}
	
	static void dfs(int player, int night) {
		if (!isDead[eunjin]) {
			answer = Math.max(answer, night);
		}
		
		if (player % 2 == 0) {
			for (int i = 0; i < N; i++) {
				if (i != eunjin && !isDead[i]) {
					
					isDead[i] = true;
					for (int j = 0; j < N; j++) {
						guilty[j] += R[i][j];
					}
					
					dfs(player - 1, night + 1);
					
					isDead[i] = false;
					for (int j = 0; j < N; j++) {
						guilty[j] -= R[i][j];
					}
				}
			}
		} else {
			int maxGuiltyPlayer = -1;
			int maxGuilty = Integer.MIN_VALUE;
			for (int i = 0; i < N; i++) {
				if (!isDead[i] && guilty[i] > maxGuilty) {
					maxGuilty = guilty[i];
					maxGuiltyPlayer = i;
				}
			}
			
			if (maxGuiltyPlayer == eunjin) return;
			
			isDead[maxGuiltyPlayer] = true;
			
			dfs(player - 1, night);
			
			isDead[maxGuiltyPlayer] = false;
		}
	}
}
