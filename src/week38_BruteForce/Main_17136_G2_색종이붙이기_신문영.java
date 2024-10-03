package week38_BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17136_G2_색종이붙이기_신문영 {
	static int answer = Integer.MAX_VALUE;
	static int[][] map = new int[10][10];
	static int[] papers = {5, 5, 5, 5, 5};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 10; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 10; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0, 0, 0);
		System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
	}
	
	public static void dfs(int x, int y, int paperCount) {
		if (x == 9 && y > 9) {
			answer = Math.min(answer, paperCount);
			return;
		}
		
		if (y > 9) {
			dfs(x + 1, 0, paperCount);
			return;
		}
		
		if (map[x][y] == 1) {
			for (int i = 4; i >= 0; i--) {
				if(papers[i] > 0) {
					if (x + i < 10 && y + i < 10) {
						boolean isOneFilled = true;
						for (int j = x; j <= x + i; j++) {
							for (int j2 = y; j2 <= y + i; j2++) {
								isOneFilled &= (map[j][j2] == 1 ? true : false);
							}
						}
						
						if (isOneFilled) {
							
							for (int j = x; j <= x + i; j++) {
								for (int j2 = y; j2 <= y + i; j2++) {
									map[j][j2] = 0;
								}
							}
							
							papers[i]--;
							
							dfs(x, y + 1, paperCount + 1);
							
							for (int j = x; j <= x + i; j++) {
								for (int j2 = y; j2 <= y + i; j2++) {
									map[j][j2] = 1;
								}
							}
							
							papers[i]++;
						}
					}
				}
			}
		} else {
			dfs(x, y + 1, paperCount);
		}
	}
}