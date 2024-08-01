package week33_BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2638_G3_치즈_신문영 {
	static int N;
	static int M;
	static final int AIR = 0;
	static final int CHEESE = 1;
	static final int INSIDE = 2;
	static int[][] direction = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];

		Queue<Pair> cheeseQueue = new ArrayDeque<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == CHEESE) {
					cheeseQueue.add(Pair.of(i, j));
				}
			}
		}
		
		int answer = 0;
		while (cheeseQueue.size() > 0) {
			boolean[][] visited = new boolean[N][M];
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] != CHEESE && !visited[i][j]) {
						boolean isOutside = false;
						Queue<Pair> queue = new ArrayDeque<>();
						Queue<Pair> tracker = new ArrayDeque<>();
						Pair starting = Pair.of(i, j);
						queue.add(starting);
						tracker.add(starting);
						visited[i][j] = true;
						while (!queue.isEmpty()) {
							Pair pair = queue.poll();
							
							if (!isOutside && Pair.isEdge(pair)) isOutside = true;
							
							for (int k = 0; k < 4; k++) {
								int nextX = pair.x + direction[k][0];
								int nextY = pair.y + direction[k][1];
								
								if (!(nextX >= 0 && nextY >= 0 && nextX < N && nextY < M)) continue;
								if (visited[nextX][nextY]) continue;
								
								if (map[nextX][nextY] != CHEESE) {
									queue.add(Pair.of(nextX, nextY));
									visited[nextX][nextY] = true;
									
									tracker.add(Pair.of(nextX, nextY));
								}
							}
						}
						
						for (Pair pair: tracker) {
							map[pair.x][pair.y] = isOutside ? AIR : INSIDE;
						}
					}
				}
			}
			
			List<Pair> meltedList = new ArrayList<>();
			for (Pair cheese : cheeseQueue) {
				int outsideContact = 0;
				int cornerCheck = 0;
				for (int i = 0; i < 4; i++) {
					int adjX = cheese.x + direction[i][0];
					int adjY = cheese.y + direction[i][1];
					
					if (!(adjX >= 0 && adjY >= 0 && adjX < N && adjY < M)) {
						outsideContact++;
						continue;
					}
					
					if (map[adjX][adjY] == AIR) outsideContact++;
				}
				
				if (outsideContact >= 2 || cornerCheck >= 2) meltedList.add(cheese);
			}
			
			for (Pair melted : meltedList) {
				map[melted.x][melted.y] = AIR; 
				cheeseQueue.remove(melted);
			}
			
			answer++;
		}
		
		System.out.println(answer);
	}
	
	static class Pair {
		int x;
		int y;
		
		static Pair of(int x, int y) {
			Pair pair = new Pair();
			pair.x = x;
			pair.y = y;
			return pair;
		}
		
		static boolean isEdge(Pair pair) {
			return pair.x == 0 || pair.y == 0 || pair.x == N - 1 || pair.y == M - 1;
		}
	}
}
