package week55_BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_1525_G2_퍼즐_신문영 {
	static int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int init = 0;
		for (int i = 0; i < 3; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				init *= 10;
				init += Integer.parseInt(st.nextToken());
			}
		}

		boolean findAnswer = false;
		int origin = 123456780;
		int answer = 0;
		Queue<Integer> queue = new ArrayDeque<>(); 
		Queue<Integer> temp = new ArrayDeque<>();
		Set<Integer> visited = new HashSet<>();
		queue.add(init);
		visited.add(init);
		while (!queue.isEmpty()) {
			int current = queue.poll();
			if (current == origin) {
				findAnswer = true;
				break;
			}
			
			String str = String.valueOf(current);
			
			if (str.length() != 9) {
				str = new StringBuilder().append(0).append(str).toString();
			}
			
			int r = 0, c = 0;
			int[][] map = new int[3][3];
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					map[i][j] = str.charAt(i * 3 + j) - '0';
					if (map[i][j] == 0) {
						r = i;
						c = j;
					}
				}
			}
			
			for (int i = 0; i < direction.length; i++) {
				int nextR = r + direction[i][0];
				int nextC = c + direction[i][1];
				
				if (nextR < 0 || nextC < 0 || nextR >= 3 || nextC >= 3) continue;
				
				map[nextR][nextC] ^= map[r][c];
				map[r][c] ^= map[nextR][nextC];
				map[nextR][nextC] ^= map[r][c];
				
				int next = 0;
				for (int k = 0; k < 3; k++) {
					for (int j = 0; j < 3; j++) {
						next *= 10;
						next += map[k][j];
					}
				}
				
				if (visited.contains(next)) {
					map[nextR][nextC] ^= map[r][c];
					map[r][c] ^= map[nextR][nextC];
					map[nextR][nextC] ^= map[r][c];
					continue;
				}
				
				temp.add(next);
				visited.add(next);
				
				map[nextR][nextC] ^= map[r][c];
				map[r][c] ^= map[nextR][nextC];
				map[nextR][nextC] ^= map[r][c];
			}
			
			if (queue.isEmpty()) {
				queue.addAll(temp);
				temp.clear();
				answer++;
			}
		}
		
		System.out.println(findAnswer ? answer : -1);
	}
}
