package week54_BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_14923_G4_미로탈출_신문영 {
	static final int NOT_USED = 0;
	static final int USED = 1;
	static int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int Hx = Integer.parseInt(st.nextToken()) - 1;
		int Hy = Integer.parseInt(st.nextToken()) - 1;
		st = new StringTokenizer(br.readLine());
		int Ex = Integer.parseInt(st.nextToken()) - 1;
		int Ey = Integer.parseInt(st.nextToken()) - 1;
		int[][] map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][][] visited = new int[2][N][M];
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < N; j++) {
				for (int j2 = 0; j2 < M; j2++) {
					visited[i][j][j2] = Integer.MAX_VALUE;
				}
			}
		}
		visited[NOT_USED][Hx][Hy] = 0;
		
		Queue<Pair> queue = new ArrayDeque<>();
		queue.add(new Pair(Hx, Hy, 1));
		while (!queue.isEmpty()) {
			Pair current = queue.poll();
			for (int i = 0; i < direction.length; i++) {
				int nextR = current.r + direction[i][0];
				int nextC = current.c + direction[i][1];
				
				if (nextR < 0 || nextC < 0 || nextR >= N || nextC >= M) continue;
				
				if (current.staff < 1 && map[nextR][nextC] == 1) continue;
				
				int currentStaff = current.staff;
				// empty, just pass
				if (map[nextR][nextC] == 0) {
					int mode = currentStaff >= 1 ? NOT_USED : USED;
					if (visited[mode][nextR][nextC] <= visited[mode][current.r][current.c] + 1) continue;
					visited[mode][nextR][nextC] = visited[mode][current.r][current.c] + 1;
				} 
				// wall
				else {
					// can use magic
					if (current.staff >= 1) {
						if (visited[USED][nextR][nextC] <= visited[NOT_USED][current.r][current.c] + 1) continue;
						visited[USED][nextR][nextC] = visited[NOT_USED][current.r][current.c] + 1;
						currentStaff--;
					} 
					// already using magic
					else continue;
				}
				
				queue.add(new Pair(nextR, nextC, currentStaff));
			}
		}
		
		if (visited[USED][Ex][Ey] == Integer.MAX_VALUE && visited[NOT_USED][Ex][Ey] == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(Math.min(visited[USED][Ex][Ey], visited[NOT_USED][Ex][Ey]));
		}
	}
	
	static class Pair {
		int r;
		int c;
		int staff;
		
		Pair(int r, int c, int staff) {
			this.r = r;
			this.c = c;
			this.staff = staff;
		}
	}
}