package week26_Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14719_G5_빗물_신문영 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int h = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		int[][] blocks = new int[h][w];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < w; i++) {
			int blockCount = Integer.parseInt(st.nextToken());
			for (int j = h - 1; j >= h - blockCount; j--) {
				blocks[j][i] = 1; 
			}
		}
		
		int sum = 0;
		for (int j = h - 1; j >= 0; j--) {
			boolean isBlocked = false;
			boolean isZeroExist = false;
			int blockedIndex = 0;
			for (int i = 0; i < w; i++) {
				if (blocks[j][i] == 1) {
					if (!isBlocked) {
						isBlocked = true;
					} else {
						if (isZeroExist) {
							sum += (i - blockedIndex) - 1;
							isZeroExist = false;
						} 
					}
					blockedIndex = i;
				} else {
					if (isBlocked) isZeroExist = true;
				}
			}
		}
		
		System.out.println(sum);
	}
}