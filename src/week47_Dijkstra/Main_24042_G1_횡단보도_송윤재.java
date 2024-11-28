package week47_Dijkstra;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_24042_G1_횡단보도_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, M;
	static long dist[];
	static List<Node>[] graph;
	static final long INF = Long.MAX_VALUE;
	
	static class Node implements Comparable<Node>{
		int idx;
		long cost;
		
		Node(int idx, long cost){
			this.idx = idx;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return Long.compare(this.cost, o.cost);
		}
	}
		
	static void init() {
		dist = new long[N + 1];
		for(int i = 2; i <= N; i++) {
			dist[i] = INF;
		}
		graph = new List[N + 1];
		for(int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<Node>();
		}
	}
			
	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		init();
		for(int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			graph[u].add(new Node(v, i));
			graph[v].add(new Node(u, i));
		}		
	}

	static void solve() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(1, 0));
		
		boolean visited[] = new boolean[N + 1];
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			int cur_idx = cur.idx;
			if(visited[cur_idx]) continue;
			visited[cur_idx] = true;
			
			for(Node next : graph[cur.idx]) {
				long cycle = (long)(dist[cur.idx] / M) * M;
				if(next.cost > cur.cost % M) {
					if(dist[next.idx] > cycle + next.cost) {
						dist[next.idx] = cycle + next.cost;
						pq.offer(new Node(next.idx, dist[next.idx]));
					}
				} else {
					if(dist[next.idx] > cycle + M + next.cost) {
						dist[next.idx] = cycle + M + next.cost;
						pq.offer(new Node(next.idx, dist[next.idx]));
					}
				}
			}
		}
		sb.append(dist[N]);
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
