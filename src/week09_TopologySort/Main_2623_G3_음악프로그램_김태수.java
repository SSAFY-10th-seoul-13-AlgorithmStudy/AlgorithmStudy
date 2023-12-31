package week09_TopologySort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2623_G3_음악프로그램_김태수 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());	//가수 수
		int M = Integer.parseInt(st.nextToken());	//PD 수
		
		int[] inD = new int[N+1];
		ArrayList<Integer> result = new ArrayList<>();
		ArrayList<Integer>[] map = new ArrayList[N+1];
		for(int i=1;i<=N;i++) map[i] = new ArrayList<>();
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int K = Integer.parseInt(st.nextToken());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			map[from].add(to);
			inD[to]++;
			while(st.hasMoreTokens()) {
				from = to;
				to = Integer.parseInt(st.nextToken());
				map[from].add(to);
				inD[to]++;
			}
		}
		
		//위상정렬
		Queue<Integer> qq = new ArrayDeque<>();
		for(int i=1;i<=N;i++) if(inD[i] == 0) qq.add(i);
		
		while(!qq.isEmpty()) {
			int cur = qq.poll();
			result.add(cur);
			for(int next:map[cur]) if(--inD[next] == 0) qq.add(next);
		}
		//사이클이 있으면 N개보다 작다
		if(result.size() != N) System.out.println(0);
		else {
			StringBuilder sb = new StringBuilder();
			for(int i = 0;i<N;i++) sb.append(result.get(i)).append(" ");
			System.out.println(sb);	
		}
	}
}
