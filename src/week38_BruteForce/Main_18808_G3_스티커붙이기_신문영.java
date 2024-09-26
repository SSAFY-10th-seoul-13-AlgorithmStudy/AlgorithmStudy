package week38_BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_18808_G3_스티커붙이기_신문영 {
	static int N;
	static int M;
	static int[][] map;

    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			int[][] sticker = new int[r][c];
			for (int j = 0; j < r; j++) {
				st = new StringTokenizer(br.readLine());
				for (int j2 = 0; j2 < c; j2++) {
					sticker[j][j2] = Integer.parseInt(st.nextToken());
				}
			}

			int rotation = 0;
			rotationLoop:
			while (rotation <= 3) {
		    	for (int x = 0; x < N; x++) {
					for (int y = 0; y < M; y++) {
						if (isPutable(x, y, sticker)) break rotationLoop;
					}
				}
		    	
				sticker = rotate(sticker);
				rotation++;
			}
		}
		
		int answer = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				answer += map[i][j];
			}
		}
		
		System.out.println(answer);
    }
    
    public static boolean isPutable(int x, int y, int[][] sticker) {
    	int r = sticker.length;
    	int c = sticker[0].length;
    	
		for (int i = x; i < x + r; i++) {
			for (int j = y; j < y + c; j++) {
				if (i >= N || j >= M) return false;
				if (map[i][j] == 1 && sticker[i - x][j - y] == 1) return false;
			}
		}
		
		for (int i = x; i < x + r; i++) {
			for (int j = y; j < y + c; j++) {
				map[i][j] |= sticker[i - x][j - y];
			}
		}
		
    	return true;
    }
    
    public static int[][] rotate(int[][] sticker) {
    	int r = sticker.length;
    	int c = sticker[0].length;
    	int[][] rotated = new int[c][r];
    	
    	for (int i = 0; i < c; i++) {
			for (int j = 0; j < r; j++) {
				rotated[i][j] = sticker[r - j - 1][i];
			}
		}
    	
    	return rotated;
    }
}