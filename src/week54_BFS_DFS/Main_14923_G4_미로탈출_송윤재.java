package week54_BFS_DFS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_14923_G4_미로탈출_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, M, Hx, Hy, Ex, Ey, map[][];
	static boolean visited[][][];
	static int dr[] = {0, 0, 1, -1};
	static int dc[] = {1, -1, 0, 0};
	
	static void init() {
		map = new int[N + 1][M + 1];
		visited = new boolean[N + 1][M + 1][2];
	}
	
	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		Hx = Integer.parseInt(st.nextToken());
		Hy = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		Ex = Integer.parseInt(st.nextToken());
		Ey = Integer.parseInt(st.nextToken());
		
		init();
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j<= M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	static void solve() {
		Queue<int[]> que = new ArrayDeque<int[]>();
		que.offer(new int[] {Hx, Hy, 0, 0});
		visited[Hx][Hy][0] = true;
		
		while(!que.isEmpty()) {
			int[] cur = que.poll();
			int cr = cur[0];
			int cc = cur[1];
			int magic = cur[2];
			int depth = cur[3];
			
			if(cr == Ex && cc == Ey) {
				sb.append(depth);
				return;
			}
			
			for(int i = 0; i < 4; i++) {
				int nr = cr + dr[i];
				int nc = cc + dc[i];
				
				if(nr < 1 || nr > N || nc < 1 || nc > M) continue;
				
				if(map[nr][nc] == 1) {
					if(magic == 1) continue;
					if(visited[nr][nc][1]) continue;
					visited[nr][nc][1] = true;
					que.offer(new int[] {nr, nc, 1, depth + 1});
				} else {
					if(visited[nr][nc][magic]) continue;
					visited[nr][nc][magic] = true;
					que.offer(new int[] {nr, nc, magic, depth + 1});
				}
			}
		}
		sb.append(-1);
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
