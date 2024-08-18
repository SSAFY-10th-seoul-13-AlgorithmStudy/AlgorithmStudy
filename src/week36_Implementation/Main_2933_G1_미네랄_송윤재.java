package week36_Implementation;

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

public class Main_2933_G1_미네랄_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int R, C, N, sticks[];
	static char[][] cave;	
	static boolean visited[][], isFall[][];
	static int dr[] = {1, -1, 0, 0};
	static int dc[] = {0, 0, 1, -1};
	
	static void init() {
		cave = new char[R + 1][C + 1];
	}
		
	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		init();
		for(int i = 1; i <= R; i++) {
			String str = br.readLine();
			for(int j = 1; j <= C; j++) {
				cave[i][j] = str.charAt(j - 1);
			}
		}
		N = Integer.parseInt(br.readLine());
		sticks = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			sticks[i] = Integer.parseInt(st.nextToken());
		}
	}

	static void solve() {
		for(int i = 0; i < N; i++) {	
			shoot(i % 2, R - sticks[i] + 1);
		}
		for(int i = 1; i <= R; i++) {
			for(int j = 1; j <= C; j++) {
				sb.append(cave[i][j]);
			}
			sb.append("\n");
		}
	}
	
	static void shoot(int dir, int r) {
		if(dir == 0) {
			for(int i = 1; i <= C; i++) {
				if(cave[r][i] == 'x') {
					cave[r][i] = '.';
					if(!isValid(r - 1, i))
						fall();
					if(!isValid(r, i + 1))
						fall();
					if(!isValid(r + 1, i))
						fall();
						
					return;
				}
			}
		} else {
			for(int i = C; i > 0; i--) {
				if(cave[r][i] == 'x') {
					cave[r][i] = '.';
					if(!isValid(r - 1, i)) {
						fall();						
					}
					if(!isValid(r, i - 1)) {
						fall();						
					}
					if(!isValid(r + 1, i)) {
						fall();						
					}
					return;
				}
			}
		}
	}
	
	static void fall() {
		List<int[]> candidate = new ArrayList<int[]>();
		int max = R + 1;
		boolean check[] = new boolean[C + 1];
		
		for(int i = R - 1; i > 0; i--) {
			for(int j = 1; j <= C; j++) {
				if(isFall[i][j]) { // 떨어질 미네랄이 있는 경우
					candidate.add(new int[] {i, j});
					if(check[j]) continue;
					check[j] = true;
					int count = 0;
					int cur = i;
					
					while(++cur <= R && cave[cur][j] != 'x') {
						count++;
					}
					max = Math.min(max, count);
				}
			}
		}		

		for(int[] can : candidate) {			
			cave[can[0]][can[1]] = '.';
			cave[can[0] + max][can[1]] = 'x';
		}
	}
	
	static boolean isValid(int r, int c) {
		visited = new boolean[R + 1][C + 1];
		if(r < 1 || r > R || c < 1 || c > C || cave[r][c] == '.') return true;
		
		
		Queue<int[]> que = new ArrayDeque<int[]>();
		que.offer(new int[] {r, c});
		visited[r][c] = true;
		
		while(!que.isEmpty()) {
			int[] cur = que.poll();
			int cr = cur[0];
			int cc = cur[1];
			if(cr == R) return true;
			for(int i = 0; i < 4; i++) {
				int nr = cr + dr[i];
				int nc = cc + dc[i];
				
				if(nr < 1 || nr > R || nc < 1 || nc > C) continue;
				if(visited[nr][nc] || cave[nr][nc] == '.') continue;

				visited[nr][nc] = true;
				que.offer(new int[] {nr, nc});				
			}
		}
		
		isFall = new boolean[R + 1][C + 1];
		
		for(int i = 1; i <= R; i++) {
			for(int j = 1; j <= C; j++) {
				isFall[i][j] = visited[i][j];
			}
		}
		
		return false;
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
