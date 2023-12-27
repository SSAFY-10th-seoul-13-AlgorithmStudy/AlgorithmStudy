package week08_DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1005_G3_ACMCraft_김태수 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		while(T-- > 0) {
			st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken()); //건물 개수
			int K = Integer.parseInt(st.nextToken()); //규칙 개수
			
			int[] time = new int[N+1]; //건물당 건설 시간
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i <= N;i++) {
				time[i] = Integer.parseInt(st.nextToken());
			}
			//위상정렬
			int[] inD = new int[N+1];
			ArrayList<Integer>[] map = new ArrayList[N+1];
			for(int i = 1; i<=N;i++) map[i] = new ArrayList<>();
			for(int i = 0;i<K;i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				map[from].add(to);
				inD[to]++;
			}
			
			int target = Integer.parseInt(br.readLine());
			//idx에 해당하는 건물이 지어질때까지 필요한 시간
			int[] result = new int[N+1];
			
			Queue<Integer> qq = new ArrayDeque<Integer>();
			
			for(int i =1; i<=N;i++) if(inD[i] == 0) qq.add(i);
			
			while(!qq.isEmpty()) {
				int cur = qq.poll();
				for(int next: map[cur]) {
					result[next] = Math.max(result[next], result[cur] + time[cur]);
					//inD가 0이 아니다: 아직 건설중인 선행 건물이 남아있다
					if(--inD[next] == 0) qq.add(next);
				}
			}
			System.out.println(result[target] + time[target]);
		}
	}
}
