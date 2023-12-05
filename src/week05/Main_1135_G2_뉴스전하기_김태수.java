package week05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1135_G2_뉴스전하기_김태수 {
	static ArrayList<Integer>[] tree;
	static int[] dp;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		tree = new ArrayList[N+1];
		for(int i = 0 ; i<=N;i++) {
			tree[i] = new ArrayList<>();
		}
		dp = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0;i< N;i++) {
			int temp = Integer.parseInt(st.nextToken());
			if(temp == -1) continue;
			tree[temp].add(i);
		}
		System.out.println(findRoute(0));
	}
	public static int findRoute(int node) {
		int count = 0;
		int maxVal = 0;
		//if(tree[node].size() == 0) return 1;
		Queue<Integer> pq = new PriorityQueue<>();
		for(Integer next: tree[node]) {
			dp[next] = findRoute(next);
			pq.add(-dp[next]);
		}
		while(!pq.isEmpty()) {
			int temp = pq.poll();
			count--;
			maxVal = Math.min(temp+count, maxVal);
		}
		return -maxVal;
	}
}
