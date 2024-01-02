package week09_TopologySort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2623_G3_음악프로그램_김희연 {
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
			int num = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());

			for(int j=0; j<num-1; j++){
				int b = Integer.parseInt(st.nextToken());
				list[a].add(b);
				indegree[b]++;
				a = b;
			}
		}

		topologySort();

		System.out.println(sb);
	}

	public static void topologySort(){
		List<Integer> result = new ArrayList<>();
		Queue<Integer> queue = new ArrayDeque<>();

		for(int i=1; i<=n; i++){
			if(indegree[i] == 0){
				queue.offer(i);
			}
		}

		while(!queue.isEmpty()){
			int now = queue.poll();

			result.add(now);
			for(int i : list[now]){
				indegree[i]--;
				if(indegree[i] == 0){
					queue.offer(i);
				}
			}
		}

		if(result.size() == n){
			for(int i : result) {
				sb.append(i).append("\n");
			}
		} else {
			System.out.println(0);
		}
	}
}