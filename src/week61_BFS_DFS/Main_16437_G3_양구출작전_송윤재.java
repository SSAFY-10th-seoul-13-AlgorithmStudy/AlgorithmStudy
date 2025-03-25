package week61_BFS_DFS;

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

public class Main_16437_G3_양구출작전_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N;
	static long island[], ans;
	static List<Integer> graph[];
	
	static void init() {
		island = new long[N + 1];
		graph = new List[N + 1];
		for(int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<Integer>();
		}
		
	}
	
	static void input() throws IOException{
		N = Integer.parseInt(br.readLine());
		init();
		for(int i = 2; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			String t = st.nextToken();
			int a = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			graph[p].add(i); // 단방향이면 충분(올라올 필요가 없기 때문에)
			if(t.equals("W"))
				island[i] = -a;
			else
				island[i] = a;
		}
	}
	
	static void solve() {
		sb.append(dfs(1));
	}
	
	static long dfs(int cur) {
		long cur_sheep = island[cur];
		
		for(int next : graph[cur]) {
			cur_sheep += dfs(next);
		}
		
		return cur_sheep < 0 ? 0 : cur_sheep;
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
