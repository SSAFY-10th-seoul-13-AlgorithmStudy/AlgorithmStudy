package week05;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_15681_G5_트리와쿼리_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, R, Q, query[], dp[];
	static boolean visited[];
	static ArrayList<Integer> list[];
	
	static void init() {
		query = new int[Q];
		dp = new int[N + 1];
		visited = new boolean[N + 1];
		list = new ArrayList[N + 1];
		for(int i = 1, end = N + 1; i < end; i++) {
			list[i] = new ArrayList<Integer>();
		}
	}
	
	static void input() throws IOException{
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		init();
		
		for(int i = 0, end = N - 1; i < end; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			list[u].add(v);
			list[v].add(u);
		}
		
		for(int i = 0; i < Q; i++) {
			query[i] = Integer.parseInt(br.readLine());
		}
	}	
	
	static void solve() {
		makeDP(R);
		for(int i = 0; i < Q; i++) {
			sb.append(dp[query[i]]).append("\n");
		}
	}
	
	static int makeDP(int cur) {
		visited[cur] = true;
		int temp = 1;
		for(int next : list[cur]) {
			if(visited[next]) continue;
			temp += makeDP(next);
		}
		return dp[cur] = temp;
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
