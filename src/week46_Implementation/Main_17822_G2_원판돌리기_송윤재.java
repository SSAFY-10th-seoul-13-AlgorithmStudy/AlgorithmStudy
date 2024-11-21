package week46_Implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_17822_G2_원판돌리기_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, M, T, disks[][], orders[][];
	static int dr[] = {-1, 1, 0, 0};
	static int dc[] = {0, 0, -1, 1};
		
	static void init() {
		disks = new int[N + 1][M];
		orders = new int[T][3];
	}
			
	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		init();
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				disks[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 3; j++) {
				orders[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}

	static void solve() {
		for(int[] order : orders) {
			rotate(order);			
			if(!erase())
				even();
		}		
		
		int result = 0;
		for(int i = 1; i <= N; i++) {
			for(int j = 0; j < M; j++) {
				result += disks[i][j];
			}
		}
		sb.append(result);
	}
	
	static void rotate(int[] order) {
		// 원판 돌리기

		int d = order[1];
		int k = order[2];
		
		for(int n = 1; n <= N; n++) {
			if(n % order[0] == 0) { // 원판 번호가 배수이면
				int[] temp = new int[M];
				
				if(d == 0) { // 시계 방향
					for(int i = 0; i < M; i++) {
						if(i - k >= 0) {
							temp[i] = disks[n][i - k];					
						} else {
							temp[i] = disks[n][M + i - k];
						}
					}
				} else { // 반시계 방향
					for(int i = 0; i < M; i++) {
						if(i + k < M) {
							temp[i] = disks[n][i + k];					
						} else {
							temp[i] = disks[n][i + k - M];
						}
					}
				}
				
				disks[n] = temp;
			}
		}
	}
	
	static boolean erase() {
		boolean flag = false;
		
		for(int i = 1; i <= N; i++) {
			for(int j = 0; j < M; j++) {
				if(disks[i][j] != 0) {
					if(bfs(i, j)) flag = true;
				}
			}
		}
		
		return flag;
	}
	
	static boolean bfs(int r, int c) {
		int n = disks[r][c];
		Queue<int[]> que = new ArrayDeque<int[]>();
		boolean visited[][] = new boolean[N + 1][M];
		que.offer(new int[] {r, c});
		visited[r][c] = true;
		
		while(!que.isEmpty()) {
			int[] cur = que.poll();
			
			for(int i = 0; i < 4; i++) {
				int nr = cur[0] + dr[i];
				int nc = cur[1] + dc[i];
				
				if(nr > N || nr <= 0) continue;
				if(nc == M) nc = 0;
				if(nc == -1) nc = M - 1;
				
				if(visited[nr][nc]) continue;
				
				if(disks[nr][nc] == n) {
					disks[r][c] = 0; // 이 조건문 안걸리면 원본이 바뀌지 않도록
					visited[nr][nc] = true;
					disks[nr][nc] = 0;
					que.offer(new int[] {nr, nc});
				}				
			}
		}
		
		return disks[r][c] == 0;
	}
	
	static void even() {
		int n = 0;
		int sum = 0;
		for(int i = 1; i <= N; i++) {
			for(int j = 0; j < M; j++) {
				if(disks[i][j] != 0) {
					n++;
					sum += disks[i][j];
				}
			}
		}
		
		double avg = (double)sum / n;
//		System.out.println(avg);

		for(int i = 1; i <= N; i++) {
			for(int j = 0; j < M; j++) {
				if(disks[i][j] < avg && disks[i][j] != 0) {
					disks[i][j] += 1;
				} else if(disks[i][j] > avg) {
					disks[i][j] -= 1;
				}
			}
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
