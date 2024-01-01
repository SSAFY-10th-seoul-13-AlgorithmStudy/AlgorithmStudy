package week09_TopologySort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2056_G4_작업_김희연 {
	static int n, e;
	static int[] indegree, time;
	static List<Integer>[] list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());

		indegree = new int[n + 1];
		time = new int[n + 1];
		list = new List[n + 1];

		for (int i = 1; i <= n; i++) {
			list[i] = new ArrayList<>();
		}

		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			time[i] = a;

			int b = Integer.parseInt(st.nextToken());

			for (int j = 0; j < b; j++) {
				int c = Integer.parseInt(st.nextToken());
				list[i].add(c);
				indegree[c]++;
			}
		}

		topologySort();
	}

	public static void topologySort() {
		int result = 0;
		int[] dp = new int[n + 1];
		Queue<Integer> queue = new ArrayDeque<>();

		for (int i = 1; i <= n; i++) {
			if (indegree[i] == 0) {
				queue.offer(i);
				dp[i] = time[i];
			}
		}

		while (!queue.isEmpty()) {
			int now = queue.poll();

			for (int j : list[now]) {
				dp[j] = Math.max(dp[j], dp[now] + time[j]);
				indegree[j]--;

				if (indegree[j] == 0) {
					queue.offer(j);
				}
			}
		}

		for(int i=1; i<=n; i++){
			result = Math.max(result, dp[i]);
		}

		System.out.println(result);
	}
}