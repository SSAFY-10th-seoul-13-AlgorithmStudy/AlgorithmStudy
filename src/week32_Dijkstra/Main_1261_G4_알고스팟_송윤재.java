package week32_Dijkstra;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1261_G4_알고스팟_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int M, N, map[][];
	static int dr[] = {1, -1, 0, 0};
	static int dc[] = {0, 0, 1, -1};
	
	static void init() {
		map = new int[N][M];
	}
		
	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		init();
		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
	}

	static void solve() {
		sb.append(bfs());
	}	
	
	static int bfs() {
		int visited[][] = new int[N][M];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				visited[i][j] = 10001;
			}
		}
		
		PriorityQueue<int[]> que = new PriorityQueue<>((o1, o2) ->{
			return o1[2] - o2[2];
		});
		que.offer(new int[] {0, 0, 0}); // 좌표, 무기 사용 횟수
		
		while(!que.isEmpty()) {			
			int[] cur = que.poll();
			int cr = cur[0];
			int cc = cur[1];
			int aoj = cur[2];
			
			if(cr == N - 1 && cc == M - 1) {
				return aoj;
			}
			
			for(int i = 0; i < 4; i++) {
				int nr = cr + dr[i];
				int nc = cc + dc[i];
				int na = aoj;
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
				
				if(map[nr][nc] == 1) na++; // 벽 부수고 이동하는 경우		
				
				if(visited[nr][nc] <= na) continue;
				que.offer(new int[] {nr, nc, na});
				
				visited[nr][nc] = na;
			}
		}
		
		return -1;
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
