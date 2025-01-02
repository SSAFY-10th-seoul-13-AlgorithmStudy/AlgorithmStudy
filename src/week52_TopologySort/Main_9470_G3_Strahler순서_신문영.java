package week52_TopologySort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_9470_G3_Strahler순서_신문영 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			int K = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int P = Integer.parseInt(st.nextToken());
			
			boolean[] isConnected = new boolean[M];
			int[] order = new int[M];
			int[] maxOrder = new int[M];
			
			Set<Integer>[] strahler = new HashSet[M];
			for (int j = 0; j < M; j++) {
				strahler[j] = new HashSet<Integer>();
			}
			
			List<Integer>[] flow = new ArrayList[M];
			for (int j = 0; j < M; j++) {
				flow[j] = new ArrayList<Integer>();
			}
			
			for (int j = 0; j < P; j++) {
				st = new StringTokenizer(br.readLine());
				int A = Integer.parseInt(st.nextToken()) - 1;
				int B = Integer.parseInt(st.nextToken()) - 1;
				flow[A].add(B);
				isConnected[B] = true;
			}
			
			for (int j = 0; j < M; j++) {
				if (!isConnected[j]) {
					order[j] = maxOrder[j] = 1;
					Queue<Integer> queue = new ArrayDeque<>();
					queue.add(j);
					while (!queue.isEmpty()) {
						int currentNode = queue.poll();
						int currentOrder = order[currentNode];
						for (int next : flow[currentNode]) {
							if (order[next] == 0) {
								order[next] = maxOrder[next] = currentOrder;
								strahler[next].add(currentNode);
							} else {
								if (currentOrder > maxOrder[next]) {
									order[next] = maxOrder[next] = currentOrder;
									strahler[next].clear();
									strahler[next].add(currentNode);
								} else if (currentOrder == maxOrder[next] && !strahler[next].contains(currentNode)) {
									order[next] = currentOrder + 1;
									strahler[next].add(currentNode);
								}
							}
							queue.add(next);
						}
					}
				}
			}
			
			int answer = 0;
			for (int j = 0; j < M; j++) {
				answer = Math.max(answer, order[j]);
			}
			
			System.out.println(K + " " + answer);
		}
	}
}
