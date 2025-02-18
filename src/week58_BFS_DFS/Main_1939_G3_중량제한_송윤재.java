package week58_BFS_DFS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_1939_G3_중량제한_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, M, dp[], S, E;
	static List<Node>[] graph;
	static final int INF = 1_000_000_001;
	
	static class Node implements Comparable<Node>{
		int vertex;
		int weight;
		
		public Node(int vertex, int weight) {
			this.vertex = vertex;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(o.weight, this.weight);
		}
	}
	
	static void init() {
		dp = new int[N + 1];		
		graph = new List[N + 1];
		for(int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
	}
	
	static void input() throws IOException{
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		init();
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			graph[a].add(new Node(b, c));
			graph[b].add(new Node(a, c));
		}
		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
	}
	
	static void solve() {
		boolean visited[] = new boolean[N + 1];
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(S, INF));
		dp[S] = INF;
		
		while(!pq.isEmpty()){
			Node cur = pq.poll();
			
			if(visited[cur.vertex]) continue;
			visited[cur.vertex] = true;
			
			for(Node next : graph[cur.vertex]){
				if(dp[next.vertex] < Math.min(dp[cur.vertex], next.weight)){
					dp[next.vertex] = Math.min(dp[cur.vertex], next.weight);
					pq.offer(next);
				}
			}
		}
		sb.append(dp[E]);
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
