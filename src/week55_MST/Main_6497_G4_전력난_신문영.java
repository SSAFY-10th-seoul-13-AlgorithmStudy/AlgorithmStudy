package week55_MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_6497_G4_전력난_신문영 {
	static int[] parents;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		do {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			if (m == 0 && n == 0) return;
			
			parents = new int[m];
			for (int i = 0; i < m; i++) {
				parents[i] = i;
			}
			
			int answer = 0;
			List<Edge> edgeList = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				int to = Integer.parseInt(st.nextToken());
				int from = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				edgeList.add(new Edge(to, from, cost));
				answer += cost;
			}
			
			edgeList.sort(null);
			
			for (Edge edge : edgeList) {
				if (union(edge.to, edge.from)) {
					answer -= edge.weight;
				}
			}
			
			System.out.println(answer);
		} while(true);
	}
	
	static int find(int house) {
		if (parents[house] == house) return house;
		return parents[house] = find(parents[house]);
	}
	
	static boolean union(int h1, int h2) {
		int parentH1 = find(h1);
		int parentH2 = find(h2);
		
		if (parentH1 == parentH2) return false;
		
		parents[parentH2] = parentH1; 
		return true;
	}
	
	
	static class Edge implements Comparable<Edge> {
		int to;
		int from;
		int weight;

		public Edge(int to, int from, int weight) {
			this.to = to;
			this.from = from;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
}
