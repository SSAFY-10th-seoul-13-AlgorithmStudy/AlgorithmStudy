package week46_BFS_DFS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_17616_G3_등수찾기_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, M, X, count;
	static List<Integer>[] for_tree, rev_tree;
	static boolean visited[];
		
	static void init() {
		for_tree = new List[N + 1];
		rev_tree = new List[N + 1];
		for(int i = 1; i <= N; i++) {
			for_tree[i] = new ArrayList<Integer>();
			rev_tree[i] = new ArrayList<Integer>();
		}
	}
			
	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		init();
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			for_tree[b].add(a);
			rev_tree[a].add(b);
		}
	}

	static void solve() {
		visited = new boolean[N + 1];
		count = 0;
		dfs(for_tree, X);
		int u = count + 1;
		
		visited = new boolean[N + 1];
		count = 0;
		dfs(rev_tree, X);
		int v = N - count;
		
		sb.append(u).append(" ").append(v);
	}
	
	static void dfs(List<Integer>[] tree, int cur) {
		visited[cur] = true;
		
		for(int next : tree[cur]) {
			if(visited[next]) continue;
			count++;
			dfs(tree, next);
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
