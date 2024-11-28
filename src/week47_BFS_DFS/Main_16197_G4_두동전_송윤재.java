package week47_BFS_DFS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_16197_G4_두동전_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, M;
	static char map[][];
	static int[] dr = {1, -1, 0, 0};
	static int[] dc = {0, 0, 1, -1};
		
	static void init() {
		map = new char[N][M];
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
		sb.append(bfs());
	}
	
	static int bfs() {
		int k = 0;
		int[] init_coin = new int[4];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] == 'o') {
					init_coin[k++] = i;
					init_coin[k++] = j;
				}
			}
		}
		
		Queue<int[]> que = new ArrayDeque<int[]>();
		boolean visited[][][][] = new boolean[N][M][N][M];
		
		que.offer(init_coin);
		visited[init_coin[0]][init_coin[1]][init_coin[2]][init_coin[3]] = true; // 초기 위치 방문처리
		int count = 0;
		
		while(!que.isEmpty() && count < 10) {
			count++;
			for(int q = 0, size = que.size(); q < size; q++) {
				int[] cur = que.poll();
				
				for(int d = 0; d < 4; d++) {
					int nr1 = cur[0] + dr[d];
					int nc1 = cur[1] + dc[d];
					int nr2 = cur[2] + dr[d];
					int nc2 = cur[3] + dc[d];
					
					if((nr1 < 0 || nr1 >= N || nc1 < 0 || nc1 >= M) 
							!= (nr2 < 0 || nr2 >= N || nc2 < 0 || nc2 >= M))// 하나만 떨어진 경우
						return count;
					if(nr1 < 0 || nr1 >= N || nc1 < 0 || nc1 >= M 
							|| nr2 < 0 || nr2 >= N || nc2 < 0 || nc2 >= M) // 둘 다 나간경우
						continue;
					
					if(map[nr1][nc1] == '#') { // 벽 만나면 이동 X
						nr1 = cur[0]; nc1 = cur[1];
					} 
					if(map[nr2][nc2] == '#') {
						nr2 = cur[2]; nc2 = cur[3];
					}
					
					if(visited[nr1][nc1][nr2][nc2]) continue;
					
					visited[nr1][nc1][nr2][nc2] = true;
					que.offer(new int[] {nr1, nc1, nr2, nc2});
				}
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
