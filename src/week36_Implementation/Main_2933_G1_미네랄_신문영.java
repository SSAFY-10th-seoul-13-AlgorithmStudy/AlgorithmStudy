package week36_Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main_2933_G1_미네랄_신문영 {
	final static char EMPTY = '.';
	final static char MINERAL = 'x';
	static int[][] direction = {
			{0, -1},
			{0, 1},
			{-1, 0},
			{1, 0},
	};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		char[][] cave = new char[R][C];
		
		for (int i = 0; i < R; i++) {
			String input = br.readLine();
			for (int j = 0; j < C; j++) {
				cave[i][j] = input.charAt(j);
			}
		}
		
		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			int height = Integer.parseInt(st.nextToken());
			if (i % 2 == 0) {
				for (int j = 0; j < C; j++) {
					if (cave[R - height][j] == MINERAL) {
						cave[R - height][j] = EMPTY;
						break;
					}
				}
			} else {
				for (int j = C - 1; j >= 0; j--) {
					if (cave[R - height][j] == MINERAL) {
						cave[R - height][j] = EMPTY;
						break;
					}
				}
			}
			
			boolean[][] clustered = new boolean[R][C];
			List<Pair> hoveredCluster = new ArrayList<>();
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					if (cave[r][c] == MINERAL && !clustered[r][c]) {
						boolean isHover = true;
						Queue<Pair> queue = new ArrayDeque<>();
						List<Pair> path = new ArrayList<>(); 
						queue.add(Pair.of(r, c));
						path.add(Pair.of(r, c));
						clustered[r][c] = true;
						
						while (!queue.isEmpty()) {
							Pair current = queue.poll();
							
							if (current.x == R - 1) isHover = false;
							
							for (int k = 0; k < 4; k++) {
								int nextX = current.x + direction[k][0];
								int nextY = current.y + direction[k][1];
								
								if (!(nextX >= 0 && nextY >= 0 && nextX < R && nextY < C)) continue;
								if (clustered[nextX][nextY]) continue;
								if (cave[nextX][nextY] == EMPTY) continue;
								
								queue.add(Pair.of(nextX, nextY));
								path.add(Pair.of(nextX, nextY));
								clustered[nextX][nextY] = true;
							}
						}
						
						if (isHover) {
							hoveredCluster = path;
						}
					}
				}
			}
			
			if (!hoveredCluster.isEmpty()) {
				boolean[][] hovered = new boolean[R][C];
				hoveredCluster.stream()
					.forEach(path -> hovered[path.x][path.y] = true);
				
				List<Pair> movedCluster;
				do {
					movedCluster = hoveredCluster.stream()
							.map(path -> Pair.of(path.x + 1, path.y))
							.filter(path -> path.x >= 0 && path.y >= 0 && path.x < R && path.y < C && (cave[path.x][path.y] != MINERAL || hovered[path.x][path.y]))
							.collect(Collectors.toList());
					
					if (movedCluster.size() == hoveredCluster.size()) {
						hoveredCluster.stream()
							.forEach(path -> {
								cave[path.x][path.y] = EMPTY;
								hovered[path.x][path.y] = false;
							});
						
						movedCluster.stream()
							.forEach(path -> cave[path.x][path.y] = MINERAL);
						
						hoveredCluster = movedCluster;
						
						hoveredCluster.stream()
							.forEach(path -> hovered[path.x][path.y] = true);
					}
				} while (movedCluster.size() == hoveredCluster.size());
			}
		}
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				System.out.print(cave[i][j]);
			}
			System.out.println();
		}
	}
	
	static class Pair {
		int x;
		int y;
		
		Pair (int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		static Pair of (int x, int y) {
			return new Pair(x, y);
		}
		
		public String toString() {
			return "[" + x + ", " + y + "]";
		}
	}
}
