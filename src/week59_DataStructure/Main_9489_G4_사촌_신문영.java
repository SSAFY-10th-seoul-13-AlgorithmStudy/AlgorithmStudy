package week59_DataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_9489_G4_사촌_신문영 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			
			if(n==0 && k==0) break;
			int target = 0;
			int[] arr = new int[n+1];
			// 부모 노드
			int[] parent = new int[n+1];
			int idx = -1;
			parent[0] = -1;
			arr[0] = -1;
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<=n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				if(arr[i] == k) target = i;
				// 연속으로 증가하는 수가 아니면 다른 그룹
				if(arr[i] != arr[i-1]+1) idx++;
				parent[i] = idx;
			}
			
			int answer = 0;
			for(int i=1; i<=n; i++) {
				// target과 같은 부모가 아니지만 조부모가 같은 노드(사촌)
				if(parent[i] != parent[target] && parent[parent[i]] == parent[parent[target]]) {
					answer++;
				}
			}
			sb.append(answer+"\n");
		}
		System.out.println(sb.toString());
	}
}
