package week07_DynamicProgramming;

import java.io.*;
import java.util.*;

public class Main_17404_G4_RGB거리2_김아린 {

	static int[][] map, min;
	static int[] idx;
	static int sum;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		map = new int[N][3];
		StringTokenizer st = null;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		min = new int[N][3];
		int ans = Integer.MAX_VALUE;
		
		for (int a = 0; a < 3; a++) { // 첫 번째 집의 색을 결정
			for (int i = 0; i < 3; i++) { // 아니면 큰 값(1001)을 넣어서 해당 색을 선택하지 않도록 함
				min[0][i] = i == a ? map[0][i] : 1001;
			}

			for (int i = 1; i < N; i++) {
				min[i][0] = Math.min(min[i - 1][1], min[i - 1][2]) + map[i][0];
				min[i][1] = Math.min(min[i - 1][2], min[i - 1][0]) + map[i][1];
				min[i][2] = Math.min(min[i - 1][0], min[i - 1][1]) + map[i][2];
			}

			for (int i = 0; i < 3; i++) { // 마지막 집의 색을 결정
				if (i != a) // 마지막 집의 색이 첫 번째 집의 색과 다른 경우만 고려
					ans = Math.min(min[N - 1][i], ans);
			}
		}

		System.out.println(ans);
	}
}
