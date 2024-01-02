package week09_TopologySort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_20119_G1_클레어와물약_김희연 {
	static int n, m, l;
	static int[] indegree, drug;
	static List<Integer>[] list;
	static boolean[] visit;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		indegree = new int[m];
		drug = new int[m];
		list = new List[n+1];
		visit = new boolean[n+1];

		for(int i=1; i<=n; i++){
			list[i] = new ArrayList<>();
		}

		for(int i=0; i<m; i++){
			st = new StringTokenizer(br.readLine());

			int k = Integer.parseInt(st.nextToken());

			for(int j=0; j<k; j++){
				int a = Integer.parseInt(st.nextToken());
				list[a].add(i);
			}

			indegree[i] = k;
			int r = Integer.parseInt(st.nextToken());
			drug[i] = r;
		}

		l = Integer.parseInt(br.readLine());

		int[] ingredient = new int[l];

		st = new StringTokenizer(br.readLine());

		for(int i=0; i<l; i++){
			ingredient[i] = Integer.parseInt(st.nextToken());
		}

		topologySort(ingredient);

		System.out.println(sb);
	}

	public static void topologySort(int[] ingredient){
		HashSet<Integer> set = new HashSet<>();
		Queue<Integer> queue = new ArrayDeque<>();

		for(int i=0; i<l; i++){
			queue.offer(ingredient[i]);
			set.add(ingredient[i]);
		}

		while (!queue.isEmpty()){
			int now = queue.poll();

			for(int i : list[now]){
				indegree[i]--;
				if(indegree[i] == 0 && !set.contains(drug[i])){
					queue.offer(drug[i]);
					set.add(drug[i]);
				}
			}
		}

		List<Integer> result = new ArrayList<>(set);

		Collections.sort(result);

		sb.append(result.size()).append("\n");

		for(int i : result){
			sb.append(i).append(" ");
		}
	}
}