import java.util.*;
import java.io.*;

class Point {
	int r, c;

	Point(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

public class Main_2636_G4_치즈_김아린 {
	static int n, m;
	static int[][] cheese;
	static boolean[][] visited;
	static int[] dr = { 0, 0, -1, 1 };
	static int[] dc = { -1, 1, 0, 0 };
	static int t, count;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		cheese = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < m; j++) {
				cheese[i][j] = Integer.parseInt(st.nextToken());
				if (cheese[i][j] == 1)
					count++;
			}
		}

		bfs();
	}

	public static void bfs() {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(0, 0));
		visited = new boolean[n][m];
		visited[0][0] = true;

		int melted = 0;
		t++;

		while (!q.isEmpty()) {
			Point p = q.poll();
			for (int i = 0; i < 4; i++) {
				int nr = p.r + dr[i];
				int nc = p.c + dc[i];

				if (nr < 0 || nc < 0 || nr >= n || nc >= m || visited[nr][nc])
					continue;

				visited[nr][nc] = true;

				if (cheese[nr][nc] == 0) {
					q.add(new Point(nr, nc));
				} else {
					cheese[nr][nc] = 0;
					melted++;
				}
			}
		}

		count -= melted;

		if (count == 0) {
			System.out.println(t);
			System.out.println(melted);
			return;
		} else {
			bfs();
		}
	}
}