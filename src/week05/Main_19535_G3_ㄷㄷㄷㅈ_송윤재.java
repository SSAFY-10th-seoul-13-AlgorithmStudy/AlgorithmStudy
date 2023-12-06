package week05;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_19535_G3_ㄷㄷㄷㅈ_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N;
	static long indegree[];
	static ArrayList<Edge> Edges;
	
	static class Edge{
		int u, v;
		public Edge(int u, int v) {
			this.u = u;
			this.v = v;
		}
	}
	
	static void init() {
		indegree = new long[N + 1];
		Edges = new ArrayList<Edge>();
	}
	
	static void input() throws IOException{
		N = Integer.parseInt(br.readLine());
		
		init();
		
		for(int i = 0, end = N - 1; i < end; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			indegree[u]++;
			indegree[v]++;
			Edges.add(new Edge(u, v));
		}
	}
	
	static void solve() {
		boolean visited[] = new boolean[N + 1];
		long cntD = 0, cntG = 0;
		
		for(Edge edge : Edges) {
			int u = edge.u;
			int v = edge.v;
			cntD += (indegree[u] - 1) * (indegree[v] - 1);
			// 간선 기준으로 양 노드에 연결 된 간선(진입 차수)의 곱만큼 'ㄷ' 모양이 만들어 질 수 있다.
			
			if(!visited[u] && indegree[u] > 2) {
				cntG += (indegree[u] * (indegree[u] - 1) * (indegree[u] - 2)) / 6;
				visited[u] = true;
			}
			
			if(!visited[v] && indegree[v] > 2) {
				cntG += (indegree[v] * (indegree[v] - 1) * (indegree[v] - 2)) / 6;
				visited[v] = true;
			}
			// 진입 차수가 3 이상인 경우에만 'ㅈ' 모양을 만들 수 있고
			// 만들 수 있는 경우의 수는 인접한 간선 n개 중 3개를 선택하는 경우이므로 nC3이 된다.
		}
		
		if(cntD > 3 * cntG) sb.append("D");
		else if(cntD < 3 * cntG) sb.append("G");
		else sb.append("DUDUDUNGA");
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
