package week09_TopologySort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_14567_G5_선수과목_김희연 {
	static int n, m;
	static int[] indegree;
	static List<Integer>[] list;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		indegree = new int[n+1];
		list = new List[n+1];

		for(int i=1; i<=n; i++){
			list[i] = new ArrayList<>();
		}

		for(int i=0; i<m; i++){
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list[a].add(b);
			indegree[b]++;
		}

		topologySort();

		System.out.println(sb);
	}

	public static void topologySort(){
		int[] result = new int[n+1];
		Queue<Integer> queue = new ArrayDeque<>();

		for(int i=1; i<=n; i++){
			if(indegree[i] == 0){
				queue.add(i);
			}
		}

		int semester = 0;
		while(!queue.isEmpty()){
			int size = queue.size();
			semester++;

			for(int i=0; i<size; i++){
				int now = queue.poll();
				result[now] = semester;

				for(int j : list[now]){
					indegree[j]--;

					if(indegree[j] == 0){
						queue.offer(j);
					}
				}
			}
		}

		for(int i=1; i<=n; i++){
			sb.append(result[i]).append(" ");
		}
	}
}
