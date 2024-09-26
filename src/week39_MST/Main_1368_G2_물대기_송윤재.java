package week39_MST;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1368_G2_물대기_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, W[], P[][];
	
	static class Edge implements Comparable<Edge>{
		int to, cost;
		public Edge(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.cost, o.cost);
		}
	}
	
	static void init() {
		W = new int[N];
		P = new int[N][N];
	}
		
	static void input() throws IOException {
		N = Integer.parseInt(br.readLine());
		init();
		for(int i = 0; i < N; i++) {
			W[i] = Integer.parseInt(br.readLine());
		}
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				P[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}

	static void solve() {
		int result = Integer.MAX_VALUE;
		
		for(int i = 0; i < N; i++) { // 복잡도 300
			result = Math.min(result, MST(i));
		}
		
		sb.append(result);
	}
	
	static int MST(int start) {
		boolean visited[] = new boolean[N];
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		int total_cost = W[start];
		visited[start] = true;
		
		for(int i = 0; i < N; i++) { // 복잡도 300
			if(i == start) continue;
			pq.offer(new Edge(i, W[i]));
			pq.offer(new Edge(i, P[start][i]));
		}
		
		while(!pq.isEmpty()) { // 모든 엣지.. 300 * 300
			Edge edge = pq.poll();
			int cur = edge.to;
			int cost = edge.cost;
			if(visited[cur]) continue;
			visited[cur] = true;
			total_cost += cost;
			for(int i = 0; i < N; i++) { // 복잡도 300
				if(visited[i]) continue;
				pq.offer(new Edge(i, P[cur][i]));
			}
		}		
		
		return total_cost;
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
