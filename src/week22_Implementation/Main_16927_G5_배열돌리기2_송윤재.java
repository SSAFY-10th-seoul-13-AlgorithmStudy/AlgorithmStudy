package week22_Implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main_16927_G5_배열돌리기2_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, M, R, arr[][];
	
	static void init() {
		arr = new int[N][M];
	}
	
	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		init();
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}

	static void solve() {
		int end_idx = Math.min(N, M) / 2;
		int idx = 0;
		while(idx < end_idx) {
			int sub_arr[][] = new int[N - 2 * idx][M - 2 * idx];
			
			for(int i = idx, end_i = N - idx; i < end_i; i++) {			
				for(int j = idx, end_j = M - idx; j < end_j; j++) {
					sub_arr[i - idx][j - idx] = arr[i][j];
				}
			}
			rotate(sub_arr);
			
			for(int i = idx, end_i = N - idx; i < end_i; i++) {			
				for(int j = idx, end_j = M - idx; j < end_j; j++) {
					arr[i][j] = sub_arr[i - idx][j - idx];
				}
			}
			
			idx++;
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				sb.append(arr[i][j]).append(" ");
			}
			sb.append("\n");
		}
	}
	
	static void rotate(int[][] sub_arr) {
		int height = sub_arr.length;
		int width = sub_arr[0].length;
		int r = R % (2 * (height + width - 2));
		
	    for (int k = 0; k < r; k++) {
	        int tmp = sub_arr[0][0];
	        for (int i = 0; i < width - 1; i++) sub_arr[0][i] = sub_arr[0][i + 1];
	        for (int i = 0; i < height - 1; i++) sub_arr[i][width - 1] = sub_arr[i + 1][width - 1];
	        for (int i = width - 1; i > 0; i--) sub_arr[height - 1][i] = sub_arr[height - 1][i - 1];
	        for (int i = height - 1; i > 1; i--) sub_arr[i][0] = sub_arr[i - 1][0];
	        sub_arr[1][0] = tmp;
	    }    	    
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