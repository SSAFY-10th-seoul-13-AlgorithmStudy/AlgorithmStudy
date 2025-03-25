package week62_DynamicProgramming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1749_G4_점수따먹기_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, M, sum[][], arr[][];
	
	static void init() {
		sum = new int[N + 1][M + 1];
		arr = new int[N + 1][M + 1];
	}
	
	static void input() throws IOException{
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		init();
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());				
			}
		}
	}
	
	static void solve() {
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= M; j++) {
				sum[i][j] = sum[i][j - 1] + sum[i - 1][j] - sum[i - 1][j - 1] + arr[i][j];
			}
		}
		
		int res = Integer.MIN_VALUE;
		
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= M; j++) {
				for(int sr = 0; sr < i; sr++) {
					for(int sc = 0; sc < j; sc++) {
						int temp = sum[i][j] - sum[sr][j] - sum[i][sc] + sum[sr][sc];
						res = Math.max(temp, res);
					}
				}
//				res = Math.max(res, arr[i][j]);
			}
		}
		sb.append(res);
	}
	
	public static void main(String[] args) throws IOException{
		input();
		solve();
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
