package week02;

import java.io.*;
import java.util.*;

public class Main_1799_G1_비숍_김아린 {
	static int[][] map;
	static ArrayList<int[]> listB, listW;
	static int N, sizeB, sizeW, ansB, ansW;
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());

		map = new int[N][N];
		listB = new ArrayList<>();
		listW = new ArrayList<>();

		// 놓을 수 있음 = 1, 놓을 수 없음 = 0
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) {
					if ((i + j) % 2 == 0)
						listB.add(new int[] { i, j });
					else
						listW.add(new int[] { i, j });
				}
			}
		}

		sizeB = listB.size();
		sizeW = listW.size();

		backtrackingB(0, 0);
		backtrackingW(0, 0);

		System.out.println(ansB + ansW);
	}

	private static void backtrackingB(int idx, int cnt) {
		if (idx == sizeB) {
			ansB = Math.max(cnt, ansB);
			return;
		}

		if (sizeB - idx + cnt < ansB)
			return;

		if (check(listB.get(idx))) {
			map[listB.get(idx)[0]][listB.get(idx)[1]] = 2;
			backtrackingB(idx + 1, cnt + 1);
			map[listB.get(idx)[0]][listB.get(idx)[1]] = 0;
		}
		backtrackingB(idx + 1, cnt);

	}

	private static void backtrackingW(int idx, int cnt) {
		if (idx == sizeW) {
			ansW = Math.max(cnt, ansW);
			return;
		}

		if (sizeW - idx + cnt < ansW)
			return;

		int[] tmp = listW.get(idx);
		if (update(tmp)) {
			map[tmp[0]][tmp[1]] = 2;
			backtrackingW(idx + 1, cnt + 1);
			map[tmp[0]][tmp[1]] = 0;
		}
		backtrackingW(idx + 1, cnt);

	}

	private static void print(int[][] ints) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(ints[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}

	static boolean check(int[] temp) {
		int dr[] = { -1, -1, 1, 1 };
		int dc[] = { -1, 1, 1, -1 };
		for (int i = 0; i < 4; i++) {
			int nr = temp[0] + dr[i];
			int nc = temp[1] + dc[i];
			while (nr >= 0 && nr < N && nc >= 0 && nc < N) {
				if (map[nr][nc] == 2)
					return false;
				nr += dr[i];
				nc += dc[i];
			}
		}
		return true;
	}

	private static boolean update(int[] temp) {
		for (int i = 0; i < N; i++) {
			int curYplus = temp[1] - temp[0] + i;

			int curYminus = temp[1] + temp[0] - i;

			if (curYplus >= 0 && curYplus < N) {
				if (map[i][curYplus] == 2)
					return false;
			}

			if (curYminus >= 0 && curYminus < N) {
				if (map[i][curYminus] == 2)
					return false;
			}
		}
		return true;
	}

}