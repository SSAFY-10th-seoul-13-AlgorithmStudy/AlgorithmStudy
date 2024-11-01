package week42_Implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_16920_G2_확장게임_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, M, P, S[];
	static char[][] map;
	static Queue<int[]>[] que;
	static int dr[] = {1, -1, 0, 0};
	static int dc[] = {0, 0, 1, -1};
	
	static void init() {
		S = new int[P + 1];
		map = new char[N][M];
		que = new ArrayDeque[P + 1];
		for(int i = 1; i <= P; i++) {
			que[i] = new ArrayDeque<>();
		}
	}
	
	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		init();
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= P; i++) {
			S[i] = Integer.parseInt(st.nextToken()); 
		}
		
		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] != '.' && map[i][j] != '#') {
					que[map[i][j] - '0'].offer(new int[] {i, j});
				}
			}
		}
	}

	static void solve() {
		boolean flag = true;
		while(flag) {
			flag = false;
			for(int p = 1; p <= P; p++) {
				int expandable = S[p];
				while (expandable > 0) {
					boolean isExpandable = false;
					for(int i = 0, size = que[p].size(); i < size; i++) {
						int[] cur = que[p].poll();
						for(int d = 0; d < 4; d++) {
							int nr = cur[0] + dr[d];
							int nc = cur[1] + dc[d];
							
							if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
							if(map[nr][nc] != '.') continue;
							
							que[p].offer(new int[] {nr, nc});
							map[nr][nc] = (char) (p + '0');
							isExpandable = true;
						}
					}
					if(isExpandable) {
						flag = true;
						expandable--;
					} else {
						expandable = 0;
					}
				}
			}
		}
		int ans[] = new int[P + 1];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] != '#' && map[i][j] != '.') {
					ans[map[i][j] - '0']++;
				}
			}
		}
		
		for(int i = 1; i <= P; i++) {
			sb.append(ans[i]).append(" ");
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
