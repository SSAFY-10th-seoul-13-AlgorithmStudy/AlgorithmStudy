package week09_TopologySort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2623_G3_음악프로그램_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, M, indegree[];
	static List<Integer> graph[];
	
	static void init() {
		indegree = new int[N + 1];
		graph = new List[N + 1];
		for(int i = 1; i < N + 1; i++) graph[i] = new ArrayList<Integer>();
	}

	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		init();
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int from = 0;
			if(num != 0) from = Integer.parseInt(st.nextToken());
			for(int j = 1; j < num; j++) {
				int to = Integer.parseInt(st.nextToken());
				graph[from].add(to);
				indegree[to]++;
				from = to;
			}
		}
	}

	static void solve() {
		ArrayList<Integer> orderList = topologySort();
		if(orderList.size() == N) {
			for(int i : orderList) {
				sb.append(i).append("\n");
			}
		}
		else sb.append(0); // 사이클 생긴 경우
	}	
	
	static ArrayList<Integer> topologySort() {
		ArrayList<Integer> orderList = new ArrayList<>();
		Queue<Integer> que = new ArrayDeque<Integer>();
		for(int i = 1; i < N + 1; i++) {
			if(indegree[i] == 0) que.offer(i);
		}
		
		while(!que.isEmpty()) {
			int cur = que.poll();
			orderList.add(cur);
			
			for(int next : graph[cur]) {
				if(--indegree[next] == 0) que.offer(next);
			}
		}
		
		return orderList;
	}
	
	public static void main(String[] args) throws IOException {
		input();
		solve();

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
