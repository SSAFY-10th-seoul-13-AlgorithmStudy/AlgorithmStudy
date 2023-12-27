package week08_DynamicProgramming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_1005_G3_ACMCraft_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, K, D[], indegree[], X, dp[];
	static ArrayList<Integer> graph[], reverse[];
	static Set<Integer> set;
	static boolean visited[];
	
	static void init() {
		D = new int[N + 1];
		indegree = new int[N + 1];
		graph = new ArrayList[N + 1];
		reverse = new ArrayList[N + 1];
		set = new HashSet<>(); // X를 건설하기 위해 건설해야하는 목록들
		visited = new boolean[N + 1];
		dp = new int[N + 1]; // dp[i] : i를 건설하기 위한 최소 시간
	}
	
	static void input() throws IOException{
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		init();
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1, length = N + 1; i < length; i++) {
			D[i] = Integer.parseInt(st.nextToken());
			graph[i] = new ArrayList<Integer>();
			reverse[i] = new ArrayList<Integer>();
		}
		
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			indegree[to]++;
			graph[from].add(to);
			reverse[to].add(from);
		}
		
		X = Integer.parseInt(br.readLine());
	}
	
	static void solve() {
		dfs(X);
		topologySort();
		sb.append(dp[X]).append("\n");
	}
	
	static void dfs(int cur) {
		visited[cur] = true;
		set.add(cur);
		for(int next : reverse[cur]){
			if(visited[next]) continue;
			dfs(next);
		}
	}
	
	static void topologySort() {
		Queue<Integer> que = new ArrayDeque<>();
		for(int i = 1, length = N + 1; i < length; i++) {
			if(indegree[i] == 0 && set.contains(i)) {
				dp[i] = D[i];
				que.offer(i);
			}
		}
		
		while(!que.isEmpty()) {
			int cur = que.poll();
			for(int next : graph[cur]) {
				dp[next] = Math.max(dp[next], dp[cur] + D[next]); // 건설하기 위해 필요한 비용 갱신
				if(--indegree[next] == 0 && set.contains(next)) que.offer(next);
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		int t = Integer.parseInt(br.readLine());
		for(int i = 0; i < t; i++) {
			input();
			solve();
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
