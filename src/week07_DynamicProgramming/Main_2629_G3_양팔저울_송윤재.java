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

public class Main_2629_G3_양팔저울_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, M, weight[], marbles[];
	static boolean dp[][];
	
	static void input() throws IOException{
		N = Integer.parseInt(br.readLine());
		weight = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			weight[i] = Integer.parseInt(st.nextToken());
		}
		
		M = Integer.parseInt(br.readLine());
		marbles = new int[M];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++) {
			marbles[i] = Integer.parseInt(st.nextToken());
		}
	}
	
	static void solve() {
		dp = new boolean[N + 1][40001]; // 구슬의 무게는 4만까지
		recur(0, 0);
		
		for(int marble : marbles) {
			if(dp[N][marble]) sb.append("Y ");
			else sb.append("N ");
		}
	}
	
	/**
	 * 현재 idx의 추를 갖고 할 수 있는 행동은 총 3가지 
	 * 1. 추가 있는 쪽에 올린다.
	 * 2. 올리지 않는다.
	 * 3. 구슬이 있는 쪽에 올린다.
	 * 위 3가지 행동을 재귀호출을 통해하면서 dp를 갱신
	 */
	static void recur(int idx, int w) {
		if(dp[idx][w]) return;
		dp[idx][w] = true;
		if(idx == N) return;
		
		recur(idx + 1, w + weight[idx]);
		recur(idx + 1, w);
		recur(idx + 1, Math.abs(w - weight[idx]));
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
