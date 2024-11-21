package week46_BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_17616_G3_등수찾기_신문영 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken()) - 1;
		
		List<Integer>[] edgeList = new List[N];
		for (int i = 0; i < N; i++) {
			edgeList[i] = new ArrayList<>();
		}
		List<Integer>[] reverseList = new List[N];
		for (int i = 0; i < N; i++) {
			reverseList[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken()) - 1;
			int v = Integer.parseInt(st.nextToken()) - 1;
			edgeList[u].add(v);
			reverseList[v].add(u);
		}
		
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(X);
		
		boolean[] visited = new boolean[N];
		visited[X] = true;
		
		int lowCount = 0;
		while (!queue.isEmpty()) {
			int current = queue.poll();
			for (Integer next : edgeList[current]) {
				if (visited[next]) continue;
				visited[next] = true;
				queue.add(next);
				lowCount++;
			}
		}
		
		queue.add(X);
		
		int highCount = 0;
		while (!queue.isEmpty()) {
			int current = queue.poll();
			for (Integer next : reverseList[current]) {
				if (visited[next]) continue;
				visited[next] = true;
				queue.add(next);
				highCount++;
			}
		}
		
		int minRank = highCount + 1;
		int maxRank = N - (highCount + 1 + lowCount) + highCount + 1;
		
		System.out.println(minRank + " " + maxRank);
	}
}
