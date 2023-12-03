package week05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_1967_G4_트리의지름_김태수 {
	static int start, end, maxVal;
	static ArrayList<int[]>[] tree;
	static boolean[] visited;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		if(N == 1) {
			System.out.println(0);
			return;
		}
		tree = new ArrayList[N+1];
		for(int i = 1 ; i <= N;i++) {
			tree[i] = new ArrayList<>();
		}
		
		for(int i = 1 ; i < N;i++) {
			st = new StringTokenizer(br.readLine());
			int nodeNum = Integer.parseInt(st.nextToken());
			int child = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			tree[nodeNum].add(new int[] {child, weight});
			tree[child].add(new int[] {nodeNum, weight});
		}
		visited = new boolean[N+1];
		maxVal = -1;
		dfs(1,0);

		visited = new boolean[N+1];
		maxVal = -1;
		dfs(end,0);
		System.out.println(maxVal);
	} 
	public static void dfs(int node, int totalWeight) {
		if(tree[node].size() == 1 && visited[tree[node].get(0)[0]]) {
			//System.out.println(node + " " + totalWeight);
			maxVal = Math.max(totalWeight, maxVal);
			if(maxVal == totalWeight) end = node;
			//System.out.println("end "+ end + " " + maxVal);
			return;
		}
		visited[node] = true;
		for(int[] next: tree[node]) {
			if(visited[next[0]]) continue;
			dfs(next[0], totalWeight + next[1]);
		}
	}
}
