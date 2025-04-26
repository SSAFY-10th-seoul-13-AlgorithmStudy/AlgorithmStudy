package week61_BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1704_G4_이분그래프_신문영 {
	static final int NOT_COLORED = 0;
	static final int WHITE = 1;
	static final int BLACK = -1;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			
			int[] visited = new int[V];
			List<Integer>[] adjList = new ArrayList[V];
			for (int j = 0; j < V; j++) {
				adjList[j] = new ArrayList<>();
			}
			
			for (int j = 0; j < E; j++) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken()) - 1;
				int v = Integer.parseInt(st.nextToken()) - 1;
				
				adjList[u].add(v);
				adjList[v].add(u);
			}
			
			boolean isBipartite = true;
			loop:
			for (int j = 0; j < V; j++) {
				int currentColor = WHITE;
				if (visited[j] == NOT_COLORED) {
					Queue<Integer> queue = new ArrayDeque<>();
					Queue<Integer> temp = new ArrayDeque<>();
					queue.add(j);
					visited[j] = currentColor;
					currentColor *= -1;
					while (!queue.isEmpty()) {
						int current = queue.poll();
						for (Integer adj : adjList[current]) {
							if (visited[adj] * currentColor == -1) {
								isBipartite = false;
								break loop;
							}
							
							if (visited[adj] == currentColor) continue;
							
							visited[adj] = currentColor;
							temp.add(adj);
						}
						
						if (queue.isEmpty()) {
							currentColor *= -1;
							queue.addAll(temp);
							temp.clear();
						}
					}
				}
			}
			
			System.out.println(isBipartite ? "YES" : "NO");
		}
	}
}
