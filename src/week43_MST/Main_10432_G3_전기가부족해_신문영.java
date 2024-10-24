package week43_MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_10432_G3_전기가부족해_신문영 {
	static int[] city;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		city = new int[N + 1];
		for (int i = 0; i < N + 1; i++) {
			city[i] = i;
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			city[Integer.parseInt(st.nextToken())] = 0;
		}
		
		Queue<Edge> edgeList = new PriorityQueue<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			edgeList.add(new Edge(u, v, w));
		}
		
		int answer = 0;
		while (!edgeList.isEmpty()) {
			Edge edge = edgeList.poll();
			if (!union(edge.u, edge.v)) {
				answer += edge.weight;
			}
			
			boolean isFullPowered = true;
			for (int i = 0; i < N + 1; i++) {
				if (city[i] != 0) {
					isFullPowered = false;
					break;
				}
			}
			
			if (isFullPowered) break;
		}
		
		System.out.println(answer);
	}
	
	public static int find(int a) {
		if (city[a] == a) return a;
		return city[a] = find(city[a]);
	}
	
	public static boolean union(int a, int b) {
		int parentA = find(a);
		int parentB = find(b);
		
		if ((parentA == parentB)) return true;
		
		if (parentA == 0) {
			city[parentB] = parentA;
		} else {
			city[parentA] = parentB;
		}

		return false;
	}
	
	
	public static class Edge implements Comparable<Edge> {
		int u;
		int v;
		int weight;
		
		public Edge(int u, int v, int weight) {
			this.u = u;
			this.v = v;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}
}