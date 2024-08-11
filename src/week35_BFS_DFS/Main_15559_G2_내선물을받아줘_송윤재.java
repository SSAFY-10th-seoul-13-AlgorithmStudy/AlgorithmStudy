package week35_BFS_DFS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_15559_G2_내선물을받아줘_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, M, count, cycle;
	static char[][] map;
	static int visited[][];
	
	static void init() {
		map = new char[N][M];
		visited = new int[N][M];
	}
		
	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		init();
		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
			}
		}
	}

	static void solve() {		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(visited[i][j] == 0) {	
					cycle++;
					dfs(i, j);
				}
			}
		}
		sb.append(count);
	}
	
	static void dfs(int r, int c) {
		visited[r][c] = cycle;
		
		int nr = r + move_r(map[r][c]);
		int nc = c + move_c(map[r][c]);
		
		if(visited[nr][nc] == 0) dfs(nr, nc);
		else if(visited[nr][nc] == cycle) count++;
	}
	
	static int move_r(char dir) {
		switch(dir) {
		case 'S':
			return 1;
		case 'N':
			return -1;
		default:
			return 0;				
		}
	}	

	static int move_c(char dir) {
		switch(dir) {
		case 'E':
			return 1;
		case 'W':
			return -1;
		default:
			return 0;				
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
