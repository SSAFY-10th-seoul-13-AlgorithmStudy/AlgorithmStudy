package week10_BackTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14712_G5_넴모넴모Easy_김희연 {
	static int answer;
	static int n, m;
	static boolean[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new boolean[n+1][m+1];

		dfs(0);

		System.out.println(answer);
	}

	public static void dfs(int cnt) {
		if (cnt == n * m) {
			answer++;
			return;
		}

		int x = cnt / m + 1;
		int y = cnt % m + 1;

		if (map[x-1][y] && map[x][y-1] && map[x-1][y-1]) {
			dfs(cnt + 1);
		} else {
			dfs(cnt + 1);
			map[x][y] = true;
			dfs(cnt + 1);
			map[x][y] = false;
		}
	}
}