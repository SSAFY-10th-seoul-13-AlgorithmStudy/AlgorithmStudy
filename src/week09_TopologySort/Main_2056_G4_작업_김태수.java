package week09_TopologySort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2056_G4_작업_김태수 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] inD = new int[N+1];
		int[] times = new int[N+1];
		int[] dp = new int[N+1];
		StringTokenizer st = null;
		ArrayList<Integer>[] map = new ArrayList[N+1];
		for(int i=1;i<=N;i++) map[i] = new ArrayList<>();
		
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());
			dp[i] = time;
			times[i] = time;
			int M = Integer.parseInt(st.nextToken());
			for(int j = 0;j<M;j++) {
				int from = Integer.parseInt(st.nextToken());
				map[from].add(i);
				inD[i]++;
			} 
		}
		
		Queue<Integer> qq = new ArrayDeque<>();
		
		for(int i =1;i<=N;i++) if(inD[i] == 0) qq.add(i);
		
		while(!qq.isEmpty()) {
			int cur = qq.poll();
			for(int next:map[cur]) {
				dp[next] = Math.max(dp[cur] + times[next], dp[next]);
				if(--inD[next] == 0) qq.add(next);
			}
		}
		Arrays.sort(dp);
		System.out.println(dp[N]);
	}
}
