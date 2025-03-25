package week61_BFS_DFS;

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

public class Main_1707_G4_이분그래프_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int V, E, visited[];
	static List<Integer> graph[];
	
	static void init() {
		visited = new int[V + 1];
		graph = new List[V + 1];
		for(int i = 1; i <= V; i++) {
			graph[i] = new ArrayList<Integer>();
		}
	}
	
	static void input() throws IOException{
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		init();
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			graph[u].add(v);
			graph[v].add(u);
		}
	}
	
	static void solve() {
		for(int i = 1; i <= V; i++) {
			if(visited[i] == 0) {
				if(!bfs(i)) {
					sb.append("NO").append("\n");
					return;
				}
			}
		}
		sb.append("YES").append("\n");
	}
	
	static boolean bfs(int start) {
		Queue<Integer> que = new ArrayDeque<Integer>();
		que.offer(start);
		visited[start] = 1;
		int toggle = 1;
		
		while(!que.isEmpty()) {
			toggle *= -1;
			for(int i = 0, size = que.size(); i < size; i++) {
				int cur = que.poll();
				for(int next : graph[cur]) {
					if(visited[next] == 0) {
						visited[next] = toggle;
						que.offer(next);
					} else if(visited[next] == -toggle) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException{
		int K = Integer.parseInt(br.readLine());
		for(int i = 0; i < K; i++) {
			input();
			solve();
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
