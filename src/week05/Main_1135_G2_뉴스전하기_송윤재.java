package week05;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1135_G2_뉴스전하기_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, dp[], max;
	static ArrayList<Integer> list[];
	
	static class Node implements Comparable<Node>{
		int num, time;
		
		public Node(int num, int size) {
			this.num = num;
			this.time = size;
		}

		@Override
		public int compareTo(Node o) {
			return o.time - this.time;
		}
	}
	
	static void init() {
		list = new ArrayList[N];
		dp = new int[N]; // 전파하는데 걸리는 시간
		for(int i = 0; i < N; i++) {
			list[i] = new ArrayList<Integer>();
		}
	}
	
	static void input() throws IOException{
		N = Integer.parseInt(br.readLine());
		init();
		st = new StringTokenizer(br.readLine());
		st.nextToken(); // 민식이꺼 버림
		for(int i = 1; i < N; i++) {
			int parent = Integer.parseInt(st.nextToken());
			list[parent].add(i);
		}
	}
	
	static void solve() {
		sb.append(dfs(0));
	}
	
	static int dfs(int cur) {
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		for(int next : list[cur]) {
			dp[next] = dfs(next);
			pq.offer(new Node(next, dp[next]));
		}
		int count = 1, max = 0;
		while(!pq.isEmpty()) {
			Node next = pq.poll();
			max = Math.max(max, next.time + count++);
		}
		return max;
	}
	
	public static void main(String[] args) throws IOException{
		input();
		solve();
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
