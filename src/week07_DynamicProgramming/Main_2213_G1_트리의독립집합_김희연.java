package week07_DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_2213_G1_트리의독립집합_김희연 {
	static int[] w;
	static ArrayList<Integer>[] tree;
	static ArrayList<Integer> res = new ArrayList<>();
	static int[][] memo;
	static boolean[] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());

		w = new int[n+1];
		tree = new ArrayList[n+1];
		memo = new int[n+1][2];
		visit = new boolean[n+1];

		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=n; i++){
			w[i] = Integer.parseInt(st.nextToken());

			tree[i] = new ArrayList();
		}

		for(int i=1; i<n; i++){
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			tree[a].add(b);
			tree[b].add(a);
		}

		dfs(1);

		visit = new boolean[n+1];

		if(memo[1][0] > memo[1][1]){
			System.out.println(memo[1][0]);
			trace(1, 0);
		} else{
			System.out.println(memo[1][1]);
			trace(1, 1);
		}

		Collections.sort(res);

		for(int num : res){
			sb.append(num + " ");
		}

		System.out.println(sb);
	}

	public static void dfs(int cur){
		int size = tree[cur].size();

		memo[cur][0] = 0;
		memo[cur][1] = w[cur];
		visit[cur] = true;

		for(int i=0; i<size; i++){
			int next = tree[cur].get(i);
			if(!visit[next]){
				dfs(next);

				memo[cur][0] += Math.max(memo[next][0], memo[next][1]);
				memo[cur][1] += memo[next][0];
			}
		}
	}

	public static void trace(int cur, int inc){
		visit[cur] = true;

		if(inc == 1){
			res.add(cur);
			for(int next : tree[cur]){
				if(!visit[next])
					trace(next, 0);
			}
		} else{
			for(int next : tree[cur]){
				if(!visit[next]){
					if(memo[next][1] > memo[next][0]){
						trace(next, 1);
					} else trace(next, 0);
				}
			}
		}
	}
}
