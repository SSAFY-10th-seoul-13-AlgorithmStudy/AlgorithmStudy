package week04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2571_G3_색종이3_김태수 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		int[][] map = new int[101][101];
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int i = 0; i< N;i++) {
			st = new StringTokenizer(br.readLine());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			for(int x = R ; x < R+10;x++) {
				for(int y = C; y < C+10;y++) {
					map[x][y] = 1;
				}
			}
		}
		
		for(int i = 1; i <= 100;i++) {
			for(int j = 1; j <= 100;j++) {
				if(map[i][j] != 0 && map[i+1][j] != 0) map[i+1][j] = map[i][j] +1;
			}
		}
//		for(int i = 0; i < 101;i++ ) {
//			System.out.println(Arrays.toString(map[i]));
//		}
	
		int result = 0;
		int height;
		for(int i = 1; i < 101;i++) {
			for(int j =1; j<101;j++) {
				height=100;
				for(int k = j; k < 101;k++) {
					height = Math.min(height, map[i][k]);
					if (height == 0) break;
					result = Math.max(result,height * (k - j + 1));
				}
			}
		}
		System.out.println(result);
	}
}	
