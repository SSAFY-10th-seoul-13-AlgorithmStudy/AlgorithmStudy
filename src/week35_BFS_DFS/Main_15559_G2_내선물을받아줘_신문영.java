package week35_BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_15559_G2_내선물을받아줘_신문영 {
	static int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		Map<Character, Integer> directionMap = new HashMap<>();
		directionMap.put('E', 0);
		directionMap.put('W', 1);
		directionMap.put('S', 2);
		directionMap.put('N', 3);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(stringTokenizer.nextToken());
		int M = Integer.parseInt(stringTokenizer.nextToken());
		
		char[][] map = new char[N][M];
		for (int i = 0; i < N; i++) {
			String inputs = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = inputs.charAt(j);
			}
		}
		
		boolean[][] visited = new boolean[N][M];
		boolean[][] gifted = new boolean[N][M];
		Queue<Pair> queue = new ArrayDeque<>();
		Queue<Pair> tracker = new ArrayDeque<>();
		
		int answer = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visited[i][j]) {
					queue.add(Pair.of(i, j));
					tracker.add(Pair.of(i, j));
					visited[i][j] = true;
					
					boolean isDuplicated = false;
					while (!queue.isEmpty()) {
						Pair current = queue.poll();
						
						int directionIndex = directionMap.get(map[current.i][current.j]);
						
						int nextI = current.i + direction[directionIndex][0];
						int nextJ = current.j + direction[directionIndex][1];
						
						if (gifted[nextI][nextJ]) {
							isDuplicated = true;
							break;
						}
						
						if (visited[nextI][nextJ]) continue;
						
						queue.add(Pair.of(nextI, nextJ));
						tracker.add(Pair.of(nextI, nextJ));
						visited[nextI][nextJ] = true;
					}

					if (!isDuplicated) {
						answer++;
					}
					
					while (!tracker.isEmpty()) {
						Pair path = tracker.poll();
						gifted[path.i][path.j] = true;
					}
				}
			}
		}
		
		System.out.println(answer);
	}
	
	static class Pair {	
		int i;
		int j;
		
		Pair (int i, int j) {
			this.i = i;
			this.j = j;
		}
		
		static Pair of(int i, int j) {
			return new Pair(i, j);
		}
	}
}