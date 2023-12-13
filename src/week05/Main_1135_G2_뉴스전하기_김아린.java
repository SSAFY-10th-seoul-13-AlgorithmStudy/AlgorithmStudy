package week05;

import java.io.*;
import java.util.*;

public class Main_1135_G2_뉴스전하기_김아린 {
	static ArrayList<Integer>[] list;
	static int[] dp;

	public static void main(String[] args) throws IOException {
		// depth가 가장 깊은 트리부터 탐색하는 것이 최적해를 포함한다........
		// 후위순회를 통해 노드를 탐색함
		// 자식들을 깊이 순대로 정렬한 후 탐색한다
		// 여러 서브트리 중 가장 오래 걸린 시간을 출력한다
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());

		list = new ArrayList[n];
		int start = 0;
		dp = new int[n];
		for (int i = 0; i < n; i++) {
			list[i] = new ArrayList<>();
			int a = Integer.parseInt(st.nextToken());
			if (a == -1) {
				start = i;
			} else {
				list[a].add(i);
			}
		}
		int min = findTree(start);
		System.out.println(min);
	}

	private static int findTree(int start) {
		for(int next : list[start]) {
			dp[next] = 1 + findTree(next);
		}
		Collections.sort(list[start], (o1, o2) -> {return dp[o2] - dp[o1];});
		int res = 0;
		for(int i = 0; i < list[start].size(); i++) {
			int num = list[start].get(i);
			dp[num] += i;
			res = Math.max(res, dp[num]);
		}
		return res;
	}
}
