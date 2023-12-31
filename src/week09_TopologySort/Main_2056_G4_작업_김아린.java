package week09_TopologySort;

import java.io.*;
import java.util.*;

public class Main_2056_G4_작업_김아린 {
	static int N;
	static int[] ans, indegree, Time;
	static ArrayList<Integer>[] arr;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
        	
		N = Integer.parseInt(br.readLine());
    	Time = new int[N+1];
    	indegree = new int[N+1];
    	ans = new int[N+1];
        arr = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) 
          	arr[i] = new ArrayList<>();
            
        
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            Time[i] = Integer.parseInt(st.nextToken());
            
            int tmp = Integer.parseInt(st.nextToken());
            for (int j = 1; j <= tmp; j++) {
                int b = Integer.parseInt(st.nextToken());
                arr[b].add(i);
                indegree[i]++;
            }
        }
        topologySort();
        
        //ans 배열에는 각 작업을 수행하는데 걸리는 최소 시간
        //ans[N] = 가장 많은 작업들이 앞서는 작업(즉, 가장 오래 걸리는 작업)의 완료 시간
        //Arrays.sort(ans)는 가장 오래 걸리는 작업의 완료 시간을 찾는 데, 곧 모든 작업을 완료하는 데 필요한 최소 시간
        Arrays.sort(ans);
        System.out.println(ans[N]);
		
 	}
	private static void topologySort() {
		Queue<Integer> q = new LinkedList<>();

        for(int i=1;i <= N;i++){
           if(indegree[i] == 0) {
               ans[i] = Time[i];
               q.add(i);
           }

        }

        for(int i = 1; i <= N; i++){

            int x = q.poll();

            for(int next : arr[x]){
            	ans[next] = Math.max(ans[x] + Time[next], ans[next]);

                if(--indegree[next] == 0){
                    q.add(next);
                }

            }
        }
	}
}