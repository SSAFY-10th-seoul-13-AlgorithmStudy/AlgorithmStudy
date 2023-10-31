package week01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1766_G2_문제집_김태수 {
	static class Node{
		int vertex;
		Node next;
		public Node(int vertex, Node next) {
			this.vertex = vertex;
			this.next = next;
		}
		@Override
		public String toString() {
			return "Node [vertex=" + vertex + ", next=" + next + "]";
		}
	}
	
	static int N,M;
	static Node[] adjList;
	static int[] inDegree;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		adjList = new Node[N+1];
		inDegree = new int[N+1];
		
		for (int i = 0; i < M; ++i) {
			st = new StringTokenizer(in.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adjList[from] = new Node(to,adjList[from]);
			++inDegree[to];
		}
		ArrayList<Integer> orderList = topologySort();
		if(orderList.size()==N) {
			StringBuilder sb = new StringBuilder();
			for (int o : orderList) {
				sb.append(o).append(" ");
			}
			System.out.println(sb);
		}else {
			System.out.println("cycle");
		}
	}
	
	private static ArrayList<Integer> topologySort() {
		ArrayList<Integer> orderList = new ArrayList<>();
		PriorityQueue<Integer> queue = new PriorityQueue();
		for (int i = 1; i <=N; ++i) {
			if(inDegree[i]==0) queue.offer(i);
		}
		 
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			orderList.add(cur);
			
			for(Node temp=adjList[cur];temp != null;temp = temp.next) {
				if(--inDegree[temp.vertex]==0) queue.offer(temp.vertex);
			}
		}
		return orderList;
	}
}
