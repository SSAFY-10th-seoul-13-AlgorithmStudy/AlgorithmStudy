package week07_DynamicProgramming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_17404_G4_RGB거리2_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int dp[][], N, cost[][];
	
	static void init() {
		dp = new int[N + 1][3];
		cost = new int[N + 1][3];
	}
	
	static void input() throws IOException{
		N = Integer.parseInt(br.readLine());
		init();
		for(int i = 1, size = N + 1; i < size; i++) {
			st = new StringTokenizer(br.readLine());
			cost[i][0] = Integer.parseInt(st.nextToken());
			cost[i][1] = Integer.parseInt(st.nextToken());
			cost[i][2] = Integer.parseInt(st.nextToken());
		}
	}
	
	static void solve() {
		int min = Integer.MAX_VALUE;
		
		for(int k = 0; k < 3; k++) { // 첫 집 선택
			Arrays.fill(dp[1], 1001); // 정한 집 빼고 선택 못하도록 최대값 넣어놓기
			for(int j = 0; j < 3; j++) {
				if(k == j) dp[1][j] = cost[1][j];
			}
			
			for (int i = 2, size = N + 1; i < size; i++) { // 중간 집 처리
				dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + cost[i][0];
				dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + cost[i][1];
				dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + cost[i][2];
			}
			
			for(int j = 0; j < 3; j++) { // 마지막집 처리
				if(k == j) continue;
				min = Math.min(min, dp[N][j]);
			}
		}
		
		sb.append(min);
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
