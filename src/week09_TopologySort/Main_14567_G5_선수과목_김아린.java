package week09_TopologySort;

import java.io.*;
import java.util.*;

public class Main_14567_G5_선수과목_김아린 {
	static int N, M;
	static ArrayList<Integer>[] arr;
	static int[] degree, ans;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new ArrayList[N + 1];

		for (int i = 1; i <= N; i++)
			arr[i] = new ArrayList<Integer>();

		degree = new int[N + 1];
		ans = new int[N + 1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			arr[from].add(to);
			degree[to]++;
		}

		topology();
		
		for (int i = 1; i < ans.length; i++) {
			System.out.print(ans[i] + " ");
		}
	}

	static void topology() {
		Queue<Integer> q = new LinkedList<>();

		for (int i = 1; i <= N; i++)
			if (degree[i] == 0) {
				q.offer(i);
				ans[i] = 1;
			}

		while (!q.isEmpty()) {
			int num = q.poll();

			for (int i : arr[num]) {
				degree[i]--;

				if (degree[i] == 0) {
					q.offer(i);
					ans[i] = ans[num] + 1;
				}
			}
		}
	}

}
