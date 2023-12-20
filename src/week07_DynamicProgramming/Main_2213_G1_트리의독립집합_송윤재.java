package week07_DynamicProgramming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main_2213_G1_트리의독립집합_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, W[], dp[][];
	static List<Integer> graph[], res;
	static boolean visited[];
	
	static void init() {
		W = new int[N + 1];
		graph = new List[N + 1];
		dp = new int[N + 1][2];
		visited = new boolean[N + 1];
		res = new ArrayList<Integer>();
	}
	
	static void input() throws IOException{
		N = Integer.parseInt(br.readLine());
		init();
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1, size = N + 1; i < size; i++) {
			W[i] = Integer.parseInt(st.nextToken());
			dp[i][1] = W[i]; // 선택된 경우 기본 가중치
			graph[i] = new ArrayList<Integer>();
		}
		
		for(int i = 1, size = N; i < size; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			graph[u].add(v);
			graph[v].add(u);
		}
	}
	
	static void solve() {
		dfs(1);
		int attend = dp[1][1] > dp[1][0] ? 1 : 0; // 1번 노드를 선택했는지
		sb.append(attend == 1 ? dp[1][1] : dp[1][0]).append("\n"); // 최대 독립집함의 크기 출력
		
		trace(1, attend);
		Collections.sort(res);
		
		for(int i : res) sb.append(i).append(" ");
	}
	
	static void dfs(int cur) {
		visited[cur] = true;
		
		for(int next : graph[cur]) {
			if(visited[next]) continue;
			dfs(next);
			
			dp[cur][0] += Math.max(dp[next][1], dp[next][0]); // 선택하지 않은 경우
			dp[cur][1] += dp[next][0]; // 선택한 경우
		}
		visited[cur] = false;
	}
	
	static void trace(int cur, int attend) {
		visited[cur] = true;
		if(attend == 1) { // 선택한 경우
			res.add(cur);
			for(int next : graph[cur]) {
				if(!visited[next]) 
					trace(next, 0);
			}
		}
		else {
			for(int next : graph[cur]) { // 선택하지 않은 경우 가중치가 높은 경우를 따라감
				if(!visited[next]) {
					if(dp[next][1] > dp[next][0])
						trace(next, 1);
					else 
						trace(next, 0);
				}
			}
		}
		visited[cur] = false;
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
