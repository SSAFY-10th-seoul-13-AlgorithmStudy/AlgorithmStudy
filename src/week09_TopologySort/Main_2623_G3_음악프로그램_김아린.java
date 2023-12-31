package week09_TopologySort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2623_G3_음악프로그램_김아린 {
	static int N, M;
	static int[] indegree;
	static ArrayList<Integer> ans, order[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		indegree = new int[N + 1];
		ans = new ArrayList<>();
		order = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++)
			order[i] = new ArrayList<>();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int from = Integer.parseInt(st.nextToken());
			while (st.hasMoreTokens()) {
				int to = Integer.parseInt(st.nextToken());
				order[from].add(to);
				indegree[to]++;
				from = to;
			}
		}
		topology();
	}

	public static void topology() {
		// 위상정렬
		Queue<Integer> q = new ArrayDeque<>();
		for (int i = 1; i <= N; i++) {
			if (indegree[i] == 0) {
				q.add(i);
			}
		}

		while (!q.isEmpty()) {
			int cur = q.poll();
			ans.add(cur);
			for (int next : order[cur]) {
				if (--indegree[next] == 0)
					q.add(next);
			}
		}

		// 사이클 확인
		// https://kim6394.tistory.com/228
		if (ans.size() != N) {
			System.out.println(0);
		} else {
			for (int a : ans) {
				System.out.println(a);
			}
		}
	}
}
