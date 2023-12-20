package week07_DynamicProgramming;

import java.io.*;
import java.util.*;

//https://kukekyakya.tistory.com/417#google_vignette

public class Main_2213_G1_트리의독립집합_김아린 {
	// dp[i][0]: i번 노드가 선택될 때 최대 독립 집합 크기
	// dp[i][1]: i번 노드가 선택되지 않을 때 최대 독립 집합 크기
	static int[][] dp = new int[10001][2];

	// 인접 노드 리스트
	static ArrayList<Integer>[] graph = new ArrayList[10001];

	// 독립 집합 노드 리스트
	static ArrayList<Integer> ans = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		// 최대 독립 집합 찾기
		// 트리 루트부터 각 노드의 최대 독립 집합 계산 (노드 포함 O / 노드 포함 X)
		// 그니까 해당 노드의 자식노드를 포함하는 경우 / 포함하지 않는 경우로 나눠서.. 부모와 자식은 동시에 포함 불가
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			dp[i][0] = Integer.parseInt(st.nextToken());
			dp[i][1] = 0;
			graph[i] = new ArrayList<>();
		}

		// 트리 입력
		for (int i = 1; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			graph[b].add(a);
		}

		// 독립 집합 계산
		sum(1, -1);
		// 독립 집합 찾기
		findSet(1, dp[1][0] > dp[1][1], -1);

		// 정렬
		Collections.sort(ans);

		System.out.println(Math.max(dp[1][0], dp[1][1]));
		for (int i = 0; i < ans.size(); i++) {
			System.out.print(ans.get(i) + " ");
		}
	}

	// 독립 집합 계산
	public static void sum(int node, int prev) {
		for (int next : graph[node]) {
			if (next == prev)
				continue;
			sum(next, node);
			
			// 현재 노드 O
	        // 다음 노드 X인 가중치 더함
			dp[node][0] += dp[next][1];
			
			// 현재 노드 선택 X
	        // 다음 노드 O / 다음 노드 X 중 더 큰 경우 더함
			dp[node][1] += Math.max(dp[next][0], dp[next][1]);
		}
	}

	// 독립 집합을 찾는 함수
	public static void findSet(int node, boolean isIncluded, int prev) {
		// 현재 노드가 독립 집합에 포함되어 있다면 리스트에 추가
		if (isIncluded)
			ans.add(node);

		// 연결 노드 전체 돌면서
		for (int next : graph[node]) {
			if (next == prev) // 이전 노드 컷
				continue;

			// 다음 노드가 독립 집합에 포함되었는지?
			// 기본값이 현재 노드 O 다음 노드 X 
			boolean nextIncluded = false;

			// 현재 노드 X라면? 다음 노드 O X 중 더 큰 경우 선택
			if (!isIncluded)
				nextIncluded = dp[next][0] > dp[next][1];

			findSet(next, nextIncluded, node);
		}
	}
}
