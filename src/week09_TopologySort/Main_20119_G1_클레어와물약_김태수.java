package week09_TopologySort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_20119_G1_클레어와물약_김태수 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	//물약 수
		int M = Integer.parseInt(st.nextToken());	//레시피 수
		//만들수 있는 물약들을 T/F로 저장
		boolean[] potions = new boolean[N+1];
		//레시피 보관: 이친구들의 차수를 빼면서 만들수 있는 레시피를 찾을겁니다.
		//recipes,results,inD는 같은 인덱스를 공유합니다.
		HashSet<Integer>[] recipes = new HashSet[M];
		int[] results = new int[M];
		int[] inD = new int[M];
		//포션이 인덱스로, 해당 포션이 필요한 recipe의 인덱스를 저장합니다.
		//입력예제 1을 예시로 하자면,
		//map[1] = [0,1], map[2] = [], map[3] = [2,3] ...
		ArrayList<Integer>[] map = new ArrayList[N+1];
		for(int i=0;i<=N;i++) map[i] = new ArrayList<>();
		
		for(int i = 0; i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int K = Integer.parseInt(st.nextToken());
			HashSet<Integer> ingr = new HashSet();
			for(int j=0;j<K;j++) {
				int P = Integer.parseInt(st.nextToken());
				ingr.add(P);
				map[P].add(i);
			}
			int end = Integer.parseInt(st.nextToken());
			results[i] = end;
			recipes[i] = ingr;
			inD[i] = ingr.size();
		}
		
		int L = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		Queue<Integer> qq = new ArrayDeque<>();
		//기본 약물들 큐에 넣고
		for(int i = 0;i<L;i++) {
			int P = Integer.parseInt(st.nextToken());
			potions[P] = true;
			qq.add(P);
		}
		while(!qq.isEmpty()) {
			int cur = qq.poll();
			//현재 약물이 재료로 들어가는 레시피들을 돌면서, 그 레시피가 완성되는지(inD=0)확인
			for(int idx:map[cur]) {
				if(--inD[idx] == 0) {
					int next = results[idx];
					//해당 약물을 이미 만들수 있다면 넘김
					if(potions[next]) continue;
					qq.add(next);
					potions[next] = true;
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		int size = 0;
		for(int i = 1 ; i <= N;i++) {
			if(potions[i]) {
				size++;
				sb.append(i).append(" ");
			}
		}
		System.out.println(size);
		System.out.println(sb);
	}
}
