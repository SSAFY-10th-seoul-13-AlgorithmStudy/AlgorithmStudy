package week55_MST;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_6497_G4_전력난_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, M, total;
	static List<Edge> graph[];
	static boolean visited[];
	
	static class Edge implements Comparable<Edge>{
		int v, cost;
		
		Edge(int v, int cost){
			this.v = v;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.cost, o.cost);
		}
	}
	
	static void init() {
		total = 0;
		graph = new List[N];
		visited = new boolean[N];
		for(int i = 0; i < N; i++) {
			graph[i] = new ArrayList<Edge>();
		}
	}
	
	static void input() throws IOException {
		init();
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			graph[u].add(new Edge(v, cost));
			graph[v].add(new Edge(u, cost));
			total += cost;
		}
	}
	
	static void solve() {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.offer(new Edge(0, 0));
		int temp = 0;
		
		while(!pq.isEmpty()) {
			Edge cur = pq.poll();
			int cur_v = cur.v;
			int cur_cost = cur.cost;
			
			if(visited[cur_v]) continue;
			
			temp += cur_cost;
			visited[cur_v] = true;
			
			for(Edge next : graph[cur_v]) {
				if(!visited[next.v])
					pq.offer(next);
			}
		}
		
		sb.append(total - temp).append("\n");
	}
	
	public static void main(String[] args) throws IOException {
		while(true) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			if(N == 0 && M == 0) break;
			input();
			solve();
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
