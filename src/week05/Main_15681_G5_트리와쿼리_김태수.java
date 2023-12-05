package week05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_15681_G5_트리와쿼리_김태수 {
	static ArrayList<Integer>[] tree;
	static int[] size;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		
		tree = new ArrayList[N+1];
		size = new int[N+1];
		for(int i = 1 ; i <= N;i++) {
			tree[i] = new ArrayList<Integer>();
		}
		
		for(int i = 0 ; i < N-1;i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			tree[from].add(to);
			tree[to].add(from);
		}
		
		countSubtree(R,-1);
		for(int i = 0; i < Q;i++) {
			int t = Integer.parseInt(br.readLine());
			System.out.println(size[t]);
		}
	}
	
	public static void countSubtree(int cur, int prev) {
		size[cur] = 1;
		for(Integer next: tree[cur]) {
			if(next == prev) continue;
			countSubtree(next, cur);
			size[cur] += size[next];
		}
	}
}
