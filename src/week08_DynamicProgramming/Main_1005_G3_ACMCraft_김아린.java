package week08_DynamicProgramming;

import java.io.*;
import java.util.*;

public class Main_1005_G3_ACMCraft_김아린 {
	static int N, K;
	static int[] ans, degree, bTime;
	static ArrayList<Integer>[] arr;
	static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		for (int testcase = 1; testcase <= T; testcase++) {
			st = new StringTokenizer(br.readLine());
			//건물개수 N, 규칙 개수 K
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			bTime = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				bTime[i] = Integer.parseInt(st.nextToken());
			}
			
			arr = new ArrayList[N+1];
            for (int i = 1; i <= N; i++) 
            	arr[i] = new ArrayList<>();
            
            degree = new int[N+1];
			
			int a, b;
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				arr[a].add(b);
                degree[b]++;
			}

            int end = Integer.parseInt(br.readLine());
            ans = new int[N+1];
            topologySort();

            System.out.println(ans[end]);
			
		}
		
 	}
	private static void topologySort() {
		Queue<Integer> q = new LinkedList<>();

        for(int i=1;i <= N;i++){
           if(degree[i] == 0) {
               ans[i] = bTime[i];
               q.add(i);
           }

        }

        for(int i = 1; i <= N; i++){

            int cur = q.poll();

            for(int next : arr[cur]){
                if(ans[next] < ans[cur] + bTime[next]){
                	ans[next] = ans[cur] + bTime[next];
                }

                if(--degree[next] == 0){
                    q.add(next);
                }

            }
        }
	}
}
