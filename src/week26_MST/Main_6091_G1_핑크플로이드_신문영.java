package week26_MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_6091_G1_핑크플로이드_신문영 {
	static int[] parents;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Edge[] edgeList = new Edge[(N * (N - 1)) / 2];
		int index = 0;
		for (int i = 0; i < N - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = i + 1; j < N; j++) {
				edgeList[index++] = new Edge(Integer.parseInt(st.nextToken()), i, j);
			}
		}
		
		Arrays.sort(edgeList);
		
		parents = new int[N];
		for (int i = 0; i < N; i++) {
			parents[i] = i;
		}
		
		boolean[][] adj = new boolean[N][N];
		for (Edge edge : edgeList) {
			if (union(edge.start, edge.end)) {
				adj[edge.start][edge.end] = true;
				adj[edge.end][edge.start] = true;
			}
		}
		
		for (int i = 0; i < N; i++) {
			int cnt = 0;
			for (int j = 0; j < N; j++) {
				if (adj[i][j]) cnt++;
			}
			
			System.out.print(cnt);
			for (int j = 0; j < N; j++) {
				if (adj[i][j]) System.out.print(" " + (j + 1));
			}
			System.out.println();
		}
	}
	
	static class Edge implements Comparable<Edge> {
		int weight;
		int start;
		int end;
		
		public Edge(int weight, int start, int end) {
			this.weight = weight;
			this.start = start;
			this.end = end;
		}
		
		@Override
		public int compareTo(Edge o2) {
			return Integer.compare(this.weight, o2.weight);
		}
	}
	
	static int find(int a) {
		if (parents[a] == a) return a;
		return parents[a] = find(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if (aRoot == bRoot) return false;
		parents[bRoot] = aRoot;
		return true;
	}
}
