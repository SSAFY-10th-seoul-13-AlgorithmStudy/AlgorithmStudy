package week21_Greedy;

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

public class Main_1068_G2_트리_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, D;
	static List<Integer>[] graph;
	static boolean isDeleted[];
	
	static void init() {
		graph = new List[N];
		for(int i = 0; i < N; i++) {
			graph[i] = new ArrayList<>();
		}
		isDeleted = new boolean[N];
	}
	
	static void input() throws IOException {
		N = Integer.parseInt(br.readLine());
		init();
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			int input = Integer.parseInt(st.nextToken());
			if(input != -1) graph[input].add(i);
		}
		D = Integer.parseInt(br.readLine());
	}

	static void solve() {
		dfs(D);
		int cnt = 0;
		for(int i = 0; i < N; i++) {
			if(!isDeleted[i]) {
				 boolean flag = false;				 
				 for(int j : graph[i]) {
					 if(!isDeleted[j]) flag = true;
				 }
				 if(!flag) cnt++;
			}
		}
		sb.append(cnt);
	}	
	
	static void dfs(int start) {
		isDeleted[start] = true;
		for(int next : graph[start]) {
			dfs(next);
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
