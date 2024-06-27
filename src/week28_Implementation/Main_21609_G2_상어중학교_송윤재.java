package week28_Implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;
import java.util.Queue;

public class Main_21609_G2_상어중학교_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, M, map[][];
	static int dr[] = {-1,  1, 0, 0};
	static int dc[] = {0,  0, -1, 1};
	static boolean visited[][];
	
	static void init() {
		map = new int[N][N];
	}
	
	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		init();
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}

	static void solve() {
		int point = 0;
		
		while(true) {
			visited = new boolean[N][N];
			int[] big_group = find_big_group();
			if(big_group[2] < 2) break;
			int r = big_group[0];
			int c = big_group[1];			
			point += big_group[2] * big_group[2];
			delete(r, c, map[r][c]);
			fall();		
			rotate();			
			fall();
		}
		
		sb.append(point);
	}
	
	static int[] find_big_group() {
		int r = 0, c = 0, max_block = 0, max_zero = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(map[i][j] > 0 && !visited[i][j]) {
					int[] bfs_result = bfs(i, j, map[i][j]);
					if(bfs_result[0] > max_block) {						
						r = i;
						c = j;
						max_block = bfs_result[0];
						max_zero = bfs_result[1];
					} else if(bfs_result[0] == max_block && bfs_result[1] >= max_zero) {						
						r = i;
						c = j;
						max_zero = bfs_result[1];
					}
				}
			}
		}
		return new int[] {r, c, max_block};
	}
	
	static int[] bfs(int r, int c, int num) {
		boolean m_visited[][] = new boolean[N][N];
		int block_count = 0;
		int zero_count = 0;
		Queue<int[]> que = new ArrayDeque<>();
		que.offer(new int[] {r, c});
		visited[r][c] = true;
		m_visited[r][c] = true;
		
		while(!que.isEmpty()) {
			int[] cur = que.poll();
			int cr = cur[0];
			int cc = cur[1];
			block_count++;
			if(map[cr][cc] == 0)
				zero_count++;
			
			for(int i = 0; i < 4; i++) {
				int nr = cr + dr[i];
				int nc = cc + dc[i];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
				if(m_visited[nr][nc]) continue;
				
				if(map[nr][nc] == num || map[nr][nc] == 0) {
					que.offer(new int[] {nr, nc});
					visited[nr][nc] = true;
					m_visited[nr][nc] = true;
				}
			}
		}
		
		return new int[] {block_count, zero_count};
	}
	
	static void delete(int r, int c, int num) {
		boolean m_visited[][] = new boolean[N][N];
		Queue<int[]> que = new ArrayDeque<>();
		que.offer(new int[] {r, c});
		m_visited[r][c] = true;
		
		while(!que.isEmpty()) {
			int[] cur = que.poll();
			int cr = cur[0];
			int cc = cur[1];
			map[cr][cc] = -2; // 빨간색으로 표시
			
			for(int i = 0; i < 4; i++) {
				int nr = cr + dr[i];
				int nc = cc + dc[i];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
				if(m_visited[nr][nc]) continue;
				
				if(map[nr][nc] == num || map[nr][nc] == 0) {
					que.offer(new int[] {nr, nc});
					m_visited[nr][nc] = true;
				}
			}
		}
	}
	
	static void fall() {
		for(int j = 0; j < N; j++) {
			for(int i = N - 1; i >= 0; i--) {
				if(map[i][j] < 0) continue;
				int num = map[i][j];
				int cur = i;
				map[i][j] = -2;
				while(cur < N - 1 && map[cur + 1][j] == -2) {
					cur++;
				}
				
				map[cur][j] = num;
			}
		}
	}
	
	static void rotate() {
		int[][] new_map = new int[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				new_map[i][j] = map[j][N - 1 - i];
			}
		}
		map = new_map;
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
