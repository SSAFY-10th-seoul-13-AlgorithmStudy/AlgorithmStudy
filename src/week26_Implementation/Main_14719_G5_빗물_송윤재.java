package week26_Implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_14719_G5_빗물_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int H, W, arr[];
	
	static void init() {
		arr = new int[W];
	}
	
	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		
		init();
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < W; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}		
	}

	static void solve() {
		boolean world[][] = new boolean[H][W];
		int rain = 0;
		
		for(int i = 0; i < W; i++) {
			int height = arr[i];
			for(int j = 0; j < height; j++) {
				world[j][i] = true;
			}
		} // 2차원 세계 true false로 만듦
		
		for(int i = 0; i < H; i++) {
			boolean flag = false;
			int temp = 0;
			for(int j = 0; j < W; j++) { // 왼쪽부터 돌면서
				if(!flag) {
					if(world[i][j]) // 처음 블록을 발견하면 앞으로 빗물이 쌓일 수 있음
						flag = true;
				}
				else {
					if(world[i][j]) { // 다음 블록을 발견하면 현재까지 쌓인 빗물 flush 
						rain += temp;
						temp = 0;
					} else // 빗물 쌓기
						temp++;
				}
				// 다음 블록 발견 못하면 빗물이 쌓이지 못하는 형태
			}
		}
		
		sb.append(rain);
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
