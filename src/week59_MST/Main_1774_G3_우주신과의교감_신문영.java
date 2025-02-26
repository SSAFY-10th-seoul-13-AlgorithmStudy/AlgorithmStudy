package week59_MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1774_G3_우주신과의교감_신문영 {
	static int[] parents;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		parents = new int[N];
		int[][] gods = new int[N][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int X = Integer.parseInt(st.nextToken());
			int Y = Integer.parseInt(st.nextToken());
			gods[i][0] = X;
			gods[i][1] = Y;
			parents[i] = i;
		}
		
		Queue<Edge> edgeQueue = new PriorityQueue<>();
		for (int from = 0; from < N; from++) {
			for (int to = 0; to < N; to++) {
				if (from != to) {
					double distance = Math.sqrt(Math.pow(Math.abs(gods[from][0] - gods[to][0]), 2) + Math.pow(Math.abs(gods[from][1] - gods[to][1]), 2));
					edgeQueue.add(new Edge(from, to, distance));
				}
			}
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			union(from, to);
		}
		
		double answer = 0;
		while (!edgeQueue.isEmpty()) {
			Edge edge = edgeQueue.poll();
			if (!union(edge.from, edge.to)) {
				answer += edge.weight;
			}
		}
		
		System.out.printf("%.2f", answer);
	}
	
	static int find(int node) {
		if (parents[node] == node) return node;
		return parents[node] = find(parents[node]);
	}
	
	static boolean union(int a, int b) {
		int aParent = find(a);
		int bParent = find(b);
		
		if (aParent == bParent) return true;
		
		if (aParent < bParent) {
			parents[bParent] = aParent;
		} else {
			parents[aParent] = bParent;
		}
		
		return false;
	}
	
	static class Edge implements Comparable<Edge> {
		int from;
		int to;
		double weight;
		
		Edge (int from, int to, double weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.weight, o.weight);
		}
	}
}