package week47_Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_24042_G1_횡단보도_신문영 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		List<Node>[] list = new List[N];
		for (int i = 0; i < N; i++) {
			list[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken()) - 1;
			int v = Integer.parseInt(st.nextToken()) - 1;
			
			list[u].add(new Node(v, i));
			list[v].add(new Node(u, i));
		}
		
		long[] dist = new long[N];
		Arrays.fill(dist, Long.MAX_VALUE);
		
		Queue<Node> queue = new PriorityQueue<>();
		queue.add(new Node(0, 0));
		dist[0] = 0;
		while (!queue.isEmpty()) {
			Node current = queue.poll();
			if (dist[current.v] < current.t) continue;
			
			for (Node next : list[current.v]) {
				long order = current.t % M;
				order = next.t - order;
				if (order < 0) order += M;
				long nextTime = order + current.t;
				
				if (dist[next.v] > nextTime) {
					dist[next.v] = nextTime;
					queue.add(new Node(next.v, nextTime));
				}
			}
		}
		
		System.out.println(dist[N - 1] + 1);
	}
	
	public static class Node implements Comparable<Node> {
		int v;
		long t;
		
		Node (int v, long t) {
			this.v = v;
			this.t = t;
		}

		@Override
		public int compareTo(Node o) {
			return Long.compare(this.t, o.t);
		}
	}
}