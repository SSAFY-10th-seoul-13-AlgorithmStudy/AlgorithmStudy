package week08_DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1005_G3_ACMCraft_±èÈñ¿¬ {
	static int N, K;
	static List<Integer>[] list;
	static int[] D, indegree, dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		while(T --> 0){
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			D = new int[N+1];
			list = new ArrayList[N+1];
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<=N; i++){
				D[i] = Integer.parseInt(st.nextToken());
				list[i] = new ArrayList<>();
			}

			indegree = new int[N+1];
			for(int i=0; i<K; i++){
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				list[a].add(b);
				indegree[b]++;
			}

			int W = Integer.parseInt(br.readLine());

			dp = new int[N+1];
			topologySort();
			sb.append(dp[W]).append("\n");
		}
		System.out.println(sb);
	}

	public static void topologySort(){
		Queue<Integer> queue = new LinkedList<>();
		for(int i=1; i<=N; i++){
			dp[i] = D[i];

			if(indegree[i] == 0){
				queue.offer(i);
			}
		}

		while(!queue.isEmpty()){
			int cur = queue.poll();

			for(Integer i : list[cur]){
				dp[i] = Math.max(dp[i], dp[cur] + D[i]);
				indegree[i]--;

				if(indegree[i] == 0)
					queue.offer(i);
			}
		}
	}
}