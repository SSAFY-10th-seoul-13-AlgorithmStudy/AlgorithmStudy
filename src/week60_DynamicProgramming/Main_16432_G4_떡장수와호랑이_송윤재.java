package week60_DynamicProgramming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_16432_G4_떡장수와호랑이_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N;
	static List<Integer>[] rice_cakes;
	static ArrayDeque<Integer> deque;
	static boolean visited[][], dp[][], flag;
	
	static void init() {
		rice_cakes = new List[N + 1];
		for(int i = 1; i <= N; i++) {
			rice_cakes[i] = new ArrayList<Integer>();
		}
		deque = new ArrayDeque<Integer>();
		visited = new boolean[10][N + 1];
		dp = new boolean[10][N + 1];
	}
	
	static void input() throws IOException{
		N = Integer.parseInt(br.readLine());
		init();
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken());
			for(int j = 0; j < m ; j++) {
				rice_cakes[i].add(Integer.parseInt(st.nextToken()));
			}
		}
	}
	
	static void solve() {
		if(!dfs(0, 1))
			sb.append(-1);
	}
	
	static boolean dfs(int last, int depth) {
		if(flag) 
			return true;

		if(depth == N + 1) {
			for(int i : deque) {
				sb.append(i).append("\n");
			}
			flag = true;
			return true;
		}
		
		if(visited[last][depth])
			return dp[last][depth];
		
		visited[last][depth] = true;
		
		for(int cur : rice_cakes[depth]) {
			if(cur == last) continue;
			deque.offer(cur);
			if(dfs(cur, depth + 1))
				dp[last][depth] = true;
			deque.pollLast();
		}
		
		return dp[last][depth];
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
