package week09_TopologySort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_14567_G5_선수과목_김태수 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());	//선수과목 수
		int M = Integer.parseInt(st.nextToken());	//선수과목 조건
		
		ArrayList<Integer>[] map = new ArrayList[N+1];
		int[] inD = new int[N+1];
		for(int i = 1 ; i <= N;i++) map[i] = new ArrayList<>();
		for(int i = 0; i < M;i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			map[from].add(to);
			inD[to]++;
		}
		
		Queue<Integer> qq = new ArrayDeque<>();
		
		for(int i = 1 ;i <= N;i++) if(inD[i] == 0) qq.add(i);
		int sem = 0;
		int[] result = new int[N+1];
		
		while(!qq.isEmpty()) {
			sem++;
			//같은 차수면 같은 학기
			for(int i = 0,end=qq.size();i<end;i++) {
				int cur = qq.poll();
				result[cur] = sem;
				for(int j = 0, end1 = map[cur].size(); j < end1;j++) {
					int next = map[cur].get(j);
					if(--inD[next] == 0) qq.add(next);
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i<=N;i++) sb.append(result[i]).append(" ");
		System.out.println(sb);
		
	}
}
