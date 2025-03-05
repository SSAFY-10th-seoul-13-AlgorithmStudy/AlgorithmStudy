package week59_MST;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1774_G3_우주신과의교감_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, M, parent[], cnt;
	static Point[] points;
	
	static class Point{
		int x, y;
		Point(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	static class Edge implements Comparable<Edge>{
		int from, to;
		double cost;
		
		Edge(int from, int to){
			this.from = from;
			this.to = to;
			this.cost = Math.sqrt(Math.pow(points[from].x - points[to].x, 2) + Math.pow(points[from].y - points[to].y, 2));
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.cost, o.cost);
		}		
	}
	
	static void init() {
		parent = new int[N + 1];
		for(int i = 1; i <= N; i++) {
			parent[i] = i;
		}
		points = new Point[N + 1];
	}
	
	static void input() throws IOException{
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		init();
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			points[i] = new Point(x, y);
		}
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			if(union(x, y)) {
				cnt++;
			}
		}
	}
	
	static void solve() {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				if(i == j) continue;
				pq.offer(new Edge(i, j));
			}
		}
		
		double total = 0;
		
		while(!pq.isEmpty()) {
			if(cnt == N - 1) break;
			Edge cur = pq.poll();
			if(union(cur.from, cur.to)) {
				total += cur.cost;
				cnt++;
			}
		}
		sb.append(String.format("%.2f", total));
	}
	
	static int find(int v) {
		if(parent[v] == v) return v;
		return parent[v] = find(parent[v]);
	}
	
	static boolean union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		if(pa == pb) return false;
		parent[pb] = pa;
		return true;
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
