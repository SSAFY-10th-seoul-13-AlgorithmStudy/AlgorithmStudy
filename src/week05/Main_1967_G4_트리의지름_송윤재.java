package week05;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_1967_G4_트리의지름_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, lastNode, max;
	static ArrayList<Node>[] list;
	static boolean visited[];
	
	static class Node{
		int num, cost;
		public Node(int num, int cost) {
			this.num = num;
			this.cost = cost;
		}
	}
	
	static void init() {
		list = new ArrayList[N + 1];
		for(int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}
		visited = new boolean[N + 1];
		max = 0;
		lastNode = 1;
	}
	
	static void input() throws IOException{
		N = Integer.parseInt(br.readLine());
		init();
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			list[a].add(new Node(b, cost));
			list[b].add(new Node(a, cost));
		}
	}
	
	static void solve() {
		dfs(1, 0);
		visited = new boolean[N + 1];
		dfs(lastNode, 0);
		sb.append(max);
	}
	
	static void dfs(int cur, int dist) {
		visited[cur] = true;
		for(Node node : list[cur]) {
			if(visited[node.num]) continue;
			dfs(node.num, dist + node.cost);
		}
		if(max < dist) {
			lastNode = cur;
			max = dist;
		}
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
