package week26_MST;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_6091_G1_핑크플로이드_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, parents[];
	static List<Integer>[] graph;
	static PriorityQueue<Edge> edges;	
	
	static class Edge implements Comparable<Edge>{
		int u, v, cost;

		public Edge(int u, int v, int cost) {
			this.u = u;
			this.v = v;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.cost, o.cost);
		}		
	}
	
	static void init() {
		parents = new int[N + 1];
		for(int i = 1; i <= N; i++) {
			parents[i] = i;
		}
		edges = new PriorityQueue<>();
		graph = new List[N + 1];
		for(int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
	}
	
	static void input() throws IOException {
		N = Integer.parseInt(br.readLine());
		
		init();
		for(int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = i + 1; j <= N; j++) {
				int cost = Integer.parseInt(st.nextToken());
				edges.offer(new Edge(i, j, cost));
			}
		}
	}

	static void solve() {
		while(!edges.isEmpty()) { // 가중치가 낮은 edge부터 꺼냄
			Edge edge = edges.poll();
			int u = edge.u;
			int v = edge.v;
			
			if(union(u, v)) { // 아직 연결되지 않은 상태면 연결
				graph[u].add(v);
				graph[v].add(u);
			}
		}
		
		for(int i = 1; i <= N; i++) {
			Collections.sort(graph[i]); // 오름차순 정렬
			sb.append(graph[i].size()).append(" ");
			for(int n : graph[i]) 
				sb.append(n).append(" ");
			sb.append("\n");
		}
	}
	
    static int find(int a) {
    	if(a == parents[a]) return a;
    	return parents[a] = find(parents[a]);
    }
    
    static boolean union(int a, int b) {
    	int pa = find(a);
    	int pb = find(b);
    	
    	if(pa == pb) return false;
    	parents[pb] = pa;
    	return true;    	
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
