package week52_TopologySort;

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

public class Main_9470_G3_Strahler순서_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int K, M, P, indegree[], strahler[];
	static List<Integer>[] graph;
	static boolean flag[];
	
	static void init() {
		indegree = new int[M + 1];
		strahler = new int[M + 1];
		graph = new List[M + 1];
		flag = new boolean[M + 1];
		for(int i = 1; i <= M; i++) {
			graph[i] = new ArrayList<Integer>();
		}
	}
	
	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		init();
		
		for(int i = 0; i < P; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			indegree[b]++;
			graph[a].add(b);
		}
	}	
	
	static void solve() {
		Queue<Integer> que = new ArrayDeque<Integer>();
		
		for(int i = 1; i <= M; i++) {
			if(indegree[i] == 0) {
				strahler[i] = 1;
				que.offer(i);
			}
		}
		
		while(!que.isEmpty()) {
			int cur = que.poll();
			for(int next : graph[cur]) {
				if(strahler[next] < strahler[cur]){ // 들어오는 순서가 더 높은 경우 
					strahler[next] = strahler[cur];
					flag[next] = false;
				}
				else if(strahler[next] == strahler[cur]) // 2개 이상인 경우
					flag[next] = true;
				

				if(--indegree[next] == 0) {
					if(flag[next])
						strahler[next]++;
					que.offer(next);
				}
			}
		}
		
//		for(int i = 1; i <= M; i++) {
//			System.out.println(i + " : " + strahler[i]);
//		}
		
		sb.append(K).append(" ").append(strahler[M]).append("\n");
	}
	
	public static void main(String[] args) throws IOException {
		int tc = Integer.parseInt(br.readLine());
		for(int i = 1; i <= tc; i++) {
			input();
			solve();
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
