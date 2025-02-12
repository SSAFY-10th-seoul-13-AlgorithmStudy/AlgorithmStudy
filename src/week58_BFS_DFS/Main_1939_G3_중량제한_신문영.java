package week58_BFS_DFS;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1939_G3_중량제한_신문영 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		List<Pair>[] adjList = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken()) - 1;
			int B = Integer.parseInt(st.nextToken()) - 1;
			int C = Integer.parseInt(st.nextToken());
			
			adjList[A].add(new Pair(B, C));
			adjList[B].add(new Pair(A, C));
		}
		
		st = new StringTokenizer(br.readLine());
		int factory = Integer.parseInt(st.nextToken()) - 1;
		int destination = Integer.parseInt(st.nextToken()) - 1;
		
		int[] dp = new int[N];
		for (int i = 0; i < N; i++) {
			dp[i] = Integer.MIN_VALUE;
		}
		
		Queue<Pair> queue = new PriorityQueue<>();
		queue.add(new Pair(factory, Integer.MAX_VALUE));
		
		boolean[] visited = new boolean[N];
		
		while (!queue.isEmpty()) {
			Pair current = queue.poll();
			
			if (visited[current.island]) continue;
			visited[current.island] = true;
			
			for (Pair adj : adjList[current.island]) {
				int passableWeight = Math.min(adj.weight, current.weight);
				
				if (dp[adj.island] >= passableWeight) continue;
				
				dp[adj.island] = passableWeight;
				
				queue.add(new Pair(adj.island, passableWeight));
			}
		}
		
		System.out.println(dp[destination]);
	}
	
	static class Pair implements Comparable<Pair> {
		int island;
		int weight;
		
		Pair(int island, int weight) {
			this.island = island;
			this.weight = weight;
		}

		@Override
		public int compareTo(Pair o) {
			return Integer.compare(o.weight, this.weight);
		}
	}
}