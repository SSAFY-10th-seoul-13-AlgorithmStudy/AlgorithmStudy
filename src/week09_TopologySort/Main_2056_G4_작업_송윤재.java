package week09_TopologySort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2056_G4_작업_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, time[], indegree[], dp[];
	static List<Integer> graph[];
	
	static void init() {
		time = new int[N + 1];
		indegree = new int[N + 1];
		graph = new List[N + 1];
		for(int i = 1; i < N + 1; i++) graph[i] = new ArrayList<>();
		dp = new int[N + 1];
		// dp[n] : n번 노드까지 작업을 완료할 때까지 최소 시간. n번 노드의 진입 차수가 0이 될 때 보장됨
	}

	static void input() throws IOException {
		N = Integer.parseInt(br.readLine());
		
		init();
		
		for(int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			time[i] = Integer.parseInt(st.nextToken());
			dp[i] = time[i];
			
			int works = Integer.parseInt(st.nextToken());
			for(int j = 0; j < works; j++) {
				int work = Integer.parseInt(st.nextToken());
				indegree[i]++;
				graph[work].add(i);
			}
		}
	}

	static void solve() {
		topologySort();
		Arrays.sort(dp);
		// 번호가 예쁘게 매겨져 있긴 하지만 위상정렬 상 마지막 노드가 작업이 끝나도
		// 중간에 끝나지 않은 작업이 있을 수 있으므로 정렬을 시켜줌
		sb.append(dp[N]);
	}
	
	static void topologySort() {
		Queue<Integer> que = new ArrayDeque<>();
		for(int i = 1; i < N + 1; i++) {
			if(indegree[i] == 0) que.offer(i);
		}
		
		while(!que.isEmpty()) {
			int cur = que.poll();
			for(int next : graph[cur]) {
				dp[next] = Math.max(dp[next], dp[cur] + time[next]); // 선행 작업을 모두 완료한 시간을 알기 위하므로 최대값으로 갱신해 줌
				if(--indegree[next] == 0) que.offer(next);
			}
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
