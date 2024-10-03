package week39_MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_1368_G2_물대기_신문영 {
	public static int[] parents;
    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		parents = new int[N + 1]; 
		for (int i = 0; i <= N; i++) {
			parents[i] = i;
		}
		
		List<Edge> edgeList = new ArrayList<>();
		for (int i = 1; i <= N; i++) {
			edgeList.add(new Edge(0, i, Integer.parseInt(br.readLine())));
		}
		
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				int weight = Integer.parseInt(st.nextToken());
				if (i == j) continue;
				edgeList.add(new Edge(i, j, weight));
			}
		}
		
		edgeList.sort(null);
		
		int answer = 0;
		for (Edge edge : edgeList) {
			if (union(edge.start, edge.end)) {
				answer += edge.weight;
			}
		}
		
		System.out.println(answer);
    }
    
    public static int find(int a) {
    	if (parents[a] == a) return a;
    	return parents[a] = find(parents[a]);
    }
    
    public static boolean union(int a, int b) {
    	int aRoot = find(a);
    	int bRoot = find(b);
    	
    	if (aRoot == bRoot) return false;
    	
    	parents[bRoot] = aRoot;
    	return true;
    }
    
    public static class Edge implements Comparable<Edge> {
    	int start;
    	int end;
    	int weight;
    	
		public Edge(int start, int end, int weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
    }
}