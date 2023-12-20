package week07_DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_2213_G1_트리의독립집합_김태수 {
	static int[] vertices;
	static int[][] dp;
	static int N, maxVal;
	static HashSet<Integer>[] edges;
	static boolean[] visited;
	static ArrayList<Integer>[][] route;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new int[N+1][2];
		vertices = new int[N];
		edges = new HashSet[N+1];
		route  = new ArrayList[N+1][2];
		for(int i = 1 ; i <= N;i++) {
			route[i][0] = new ArrayList<>();
			route[i][1] = new ArrayList<>();
		}
		for(int i = 1 ; i <= N;i++) {
			edges[i] = new HashSet<>();
		}
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N;i++) {
			vertices[i] = Integer.parseInt(st.nextToken());
		}
		for(int i = 0 ; i < N-1 ;i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			edges[from].add(to);
			edges[to].add(from);
		}
		visited = new boolean[N+1];
		visited[1] = true;
		makeSet(1);
		StringBuilder sb = new StringBuilder();
		if(dp[1][0] > dp[1][1]) {
			System.out.println(dp[1][0]);
			Collections.sort(route[1][0]);
			for(int i: route[1][0]) sb.append(i).append(" ");
			System.out.println(sb);
		}
		else {
			System.out.println(dp[1][1]);
			Collections.sort(route[1][1]);
			for(int i: route[1][1]) sb.append(i).append(" ");
			System.out.println(sb);
		}
		//System.out.println(Math.max(dp[1][0], dp[1][1]));
	}
	
	public static void makeSet(int idx) {
		dp[idx][1] = vertices[idx-1];
		HashSet<Integer> current = edges[idx];
		route[idx][1].add(idx);
		for(int i: current) {
			if (visited[i]) continue;
			visited[i] = true;
			if(dp[i][0] == 0) makeSet(i);
			visited[i] = false;
			dp[idx][1] += dp[i][0];
			for(int j: route[i][0]) route[idx][1].add(j);
			if(dp[i][1] > dp[i][0]) {
				dp[idx][0] += dp[i][1];
				for(int j: route[i][1]) route[idx][0].add(j);
			} 
			else {
				dp[idx][0] += dp[i][0];
				for(int j: route[i][0]) route[idx][0].add(j);
			} 
		}
	}
}
