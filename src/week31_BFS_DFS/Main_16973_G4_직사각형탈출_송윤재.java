package week31_BFS_DFS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_16973_G4_직사각형탈출_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, M, H, W, sr, sc, fr, fc, map[][];
	static int dr[] = {1, -1, 0, 0};
	static int dc[] = {0, 0, 1, -1};
	static boolean visited[][];
	
	static void init() {
		map = new int[N + 1][M + 1];
		visited = new boolean[N + 1][M + 1];
	}
		
	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		init();
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		sr = Integer.parseInt(st.nextToken());
		sc = Integer.parseInt(st.nextToken());
		fr = Integer.parseInt(st.nextToken());
		fc = Integer.parseInt(st.nextToken());
	}

	static void solve() {
		Queue<int[]> que = new ArrayDeque<int[]>();
		que.offer(new int[] {sr, sc});
		visited[sr][sc] = true;
		int depth = 0;
		int result = -1;
		
		top:
		while(!que.isEmpty()) {
			int size = que.size();
			while(size-- > 0) {
				int[] cur = que.poll();
				if(cur[0] == fr && cur[1] == fc) {
					result = depth;
					break top;
				}
				for(int i = 0; i < 4; i++) {
					int nr = cur[0] + dr[i];
					int nc = cur[1] + dc[i];				

					if(!isInside(nr, nc)) continue;
					if(visited[nr][nc]) continue;
					if(!isValid(nr, nc, i)) continue;
					visited[nr][nc] = true;
					
					que.offer(new int[] {nr, nc});
				}
			}
			depth++;
		}
		sb.append(result);
	}
	
	static boolean isInside(int r, int c) {
		return r > 0 && r + H - 1 <= N && c > 0 && c + W - 1 <= M;
	}
	
	static boolean isValid(int r, int c, int dir) {
		switch(dir) {
			case 0:
				for(int i = c; i < c + W; i++) 
					if(map[r + H - 1][i] == 1) return false;				
				break;
			case 1:
				for(int i = c; i < c + W; i++) 
					if(map[r][i] == 1) return false;				
				break;
			case 2:
				for(int i = r; i < r + H; i++) 
					if(map[i][c + W - 1] == 1) return false;				
				break;
			case 3:
				for(int i = r; i < r + H; i++) 
					if(map[i][c] == 1) return false;				
				break;	
		}
		
		return true;
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
