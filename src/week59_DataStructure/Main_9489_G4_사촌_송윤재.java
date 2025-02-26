package week59_DataStructure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_9489_G4_사촌_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, K, arr[], parent[], k_idx;
	
	static void init() {
		arr = new int[N + 1];
		parent = new int[N + 1];
	}
	
	static void input() throws IOException{
		init();
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			if(arr[i] == K)
				k_idx = i;
		}
	}
	
	static void solve() {
		Queue<Integer> que = new ArrayDeque<Integer>();
		que.offer(1);
		int before = arr[1];
		int p = 1;
		
		for(int i = 2; i <= N; i++) {
			int cur = arr[i];
			if(before + 1 < cur) { // 새로운 세대
				p = que.poll(); // 부모 변경
			}
			que.offer(i);
			before = cur;
			parent[i] = p;
		}
		int cnt = 0;
		for(int i = 2; i <= N ; i++) {
			if(parent[k_idx] != parent[i] && // 부모가 다르면서 
					parent[parent[k_idx]] == parent[parent[i]]) { // 조부모가 같은 경우
				cnt++;
			}
		}
		
		sb.append(cnt).append("\n");
	}
	
	public static void main(String[] args) throws IOException{
		while(true) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			if(N == 0 && K == 0)
				break;
			input();
			solve();
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
