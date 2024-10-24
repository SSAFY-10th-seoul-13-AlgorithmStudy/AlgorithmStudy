package week43_Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_10282_G4_해킹_신문영 {
	static List<Node>[] list;
	static int[] dist;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken()) - 1;
			
			list = new ArrayList[n + 1];
			dist = new int[n + 1];
			for (int j = 0; j < n + 1; j++) {
				list[j] = new ArrayList<>();
			}
			
			Arrays.fill(dist, Integer.MAX_VALUE);
			
			for (int j = 0; j < d; j++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken()) - 1;
				int b = Integer.parseInt(st.nextToken()) - 1;
				int s = Integer.parseInt(st.nextToken());
				list[b].add(new Node(a, s));
			}
			
			Queue<Node> queue = new PriorityQueue<>();
			boolean[] check = new boolean[n + 1];
			queue.add(new Node(c, 0));
			dist[c] = 0;
			
			while (!queue.isEmpty()) {
				Node current = queue.poll();
				
				int currentV = current.v;
				
				if (check[currentV]) continue;
				check[currentV] = true;
				
				for (Node node : list[currentV]) {
					if (dist[node.v] > dist[currentV] + node.weight) {
						dist[node.v] = dist[currentV] + node.weight;
						queue.add(new Node(node.v, dist[node.v]));
					}
				}
			}
			
			int count = 0;
			int max = -1;
			for (int j = 0; j < n + 1; j++) {
				if (dist[j] != Integer.MAX_VALUE) {
					if (dist[j] > max) {
						max = dist[j];
					}
					count++;
				}
			}
			
			System.out.println(count + " " + max);
		}
	}
	
	public static class Node implements Comparable<Node> {
		int v;
		int weight;
		
		public Node(int v, int weight) {
			this.v = v;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	
}