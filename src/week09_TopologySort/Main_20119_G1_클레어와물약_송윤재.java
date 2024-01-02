package week09_TopologySort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_20119_G1_클레어와물약_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, M, indegree[], Y[], drugs[];
	static List<Integer> graph[];
	
	static void init() {
		indegree = new int[M]; // indgree[i] : i번째 레시피를 만들기 위해 필요한 간선의 수
		drugs = new int[M]; // drugs[i] : i번째 레시피로 만들 수 있는 약물
		graph = new List[N + 1]; // graph[o] : o 약물로 만들 수 있는 레시피의 인덱스 
		for(int i = 1; i < N + 1; i++) graph[i] = new ArrayList<Integer>();
	}

	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		init();
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			for(int j = 0; j < k; j++) 
				graph[Integer.parseInt(st.nextToken())].add(i);
			indegree[i] = k;
			int r = Integer.parseInt(st.nextToken());
			drugs[i] = r; // 실제 만들고자 하는 약물은 r
		}
		
		int L = Integer.parseInt(br.readLine());
		Y = new int[L];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < L; i++) Y[i] = Integer.parseInt(st.nextToken());
	}

	static void solve() {
		HashSet<Integer> orderSet = topologySort();
		ArrayList<Integer> result = new ArrayList<>();
		
		for(int i : orderSet)
			result.add(i);
		
		Collections.sort(result);
		 
		sb.append(result.size()).append("\n");
		for(int i : result)
			sb.append(i).append(" ");
	}
	
	static HashSet<Integer> topologySort() {
		HashSet<Integer> orderSet = new HashSet<>(); // 가능한 약물 중복 방지를 위해 HashSet 사용
		Queue<Integer> que = new ArrayDeque<Integer>();
		
		for(int i : Y) {
			que.offer(i);
			orderSet.add(i);
		}
		
		while(!que.isEmpty()) {
			int cur = que.poll();
			
			for(int recipe : graph[cur]) {
				if(--indegree[recipe] == 0) {
					int drug = drugs[recipe];
					if(orderSet.contains(drug)) continue;
					que.offer(drug);
					orderSet.add(drug);
				}
			}
		}
		
		return orderSet;
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
