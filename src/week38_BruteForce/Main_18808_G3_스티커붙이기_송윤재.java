package week38_BruteForce;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_18808_G3_스티커붙이기_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, M, K, map[][];
	static Sticker[] stickers;
	
	static class Sticker {
		int r, c, paper[][];
		public Sticker(int r, int c, int[][] paper) {
			this.r = r;
			this.c = c;
			this.paper = paper;
		}
		public void rotate() {
			int[][] new_paper = new int[c][r];
			for(int i = 0; i < r; i++) {
				for(int j = 0; j < c; j++) {
					new_paper[j][r - i - 1] = paper[i][j];
				}
			}
			int temp = r;
			r = c;
			c = temp;
			paper = new_paper;
		}
	}
	
	static void init() {
		map = new int[N][M];
		stickers = new Sticker[K];
	}
		
	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		init();
		
		for(int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int paper[][] = new int[r][c];
			
			for(int i = 0; i < r; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < c; j++) {
					paper[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			stickers[k] = new Sticker(r, c, paper);
		}
	}

	static void solve() {
		/*
		 *  스티커를 순서대로 붙이기
		 *  map을 왼쪽 위부터 차례대로 해당 스티커의 모눈종이가 들어 갈 수 있는지 판단
		 *  인덱스 기준으로 모눈종이 전체가 들어갈 수 있는지 판단
		 */
		int result = 0;
		int num = 1;
		for(Sticker sticker : stickers) {
			int cnt = 0;
			int temp = 0;
			while(cnt++ < 4 && temp == 0) {
				temp = check(sticker.r, sticker.c, sticker.paper);
				sticker.rotate();
			}
			result += temp;
		}
		sb.append(result);
	}
	
	static int check(int r, int c, int paper[][]) {
		for(int i = 0; i < N - r + 1; i++) {
			for(int j = 0; j < M - c + 1; j++) {
				if(isValid(i, j, paper)) {
					return reflect(i, j , paper);
				}
			}
		}
		
		return 0;
	}
	
	static boolean isValid(int or, int oc, int paper[][]) {
		int r = paper.length;
		int c = paper[0].length;
		
		for(int i = 0; i < r; i++) {
			for(int j = 0; j < c; j++) {				
				if(map[or + i][oc + j] == 1 && paper[i][j] == 1) 
					return false;					
			}
		}
		
		return true;
	}
	
	static int reflect(int or, int oc, int paper[][]) {
		int count = 0;	
		int r = paper.length;
		int c = paper[0].length;
		
		for(int i = 0; i < r; i++) {
			for(int j = 0; j < c; j++) {
				if(paper[i][j] == 1) {
					map[or + i][oc + j] = paper[i][j];
					count++;
				}
			}
		}
		
		return count;
	}
	
	public static void main(String[] args) throws IOException {		
		input();
		solve();

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
