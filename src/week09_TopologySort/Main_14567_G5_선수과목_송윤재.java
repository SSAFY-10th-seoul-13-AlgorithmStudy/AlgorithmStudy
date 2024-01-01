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

public class Main_14567_G5_선수과목_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, M, indegree[], result[];
	static List<Integer> graph[];
	
	static void init() {
		indegree = new int[N + 1];
		graph = new List[N + 1];
		result = new int[N + 1];
		for(int i = 1; i < N + 1; i++) {
			graph[i] = new ArrayList<>();
		}
	}

	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		init();
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			graph[a].add(b);
			indegree[b]++;
		}
	}

	static void solve() {
		topologySort();
		for(int i = 1; i < N + 1; i++) {
			sb.append(result[i]).append(" ");
		}
	}
	
	static void topologySort() {
		int week = 1;
		Queue<Integer> que = new ArrayDeque<>();
		
		for(int i = 1; i < N + 1; i++) {
			if(indegree[i] == 0) que.offer(i);
		}
		
		while(!que.isEmpty()) {
			int size = que.size();
			for(int i = 0; i < size; i++) { // 한 사이클 당 한 주를 뜻함
				int cur = que.poll();
				result[cur] = week; // 듣게되는 주차 기록
				for(int next : graph[cur]) {
					if(--indegree[next] == 0) que.offer(next);
				}
			}
			week++;
		}
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
