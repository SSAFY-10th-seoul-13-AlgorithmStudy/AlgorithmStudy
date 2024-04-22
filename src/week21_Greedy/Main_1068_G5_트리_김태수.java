package week21_Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_1068_G5_트리_김태수 {
	static ArrayList<Integer>[] tree;
	static int N, term, root, answer;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		tree = new ArrayList[N+1];
		
		for(int i = 0 ; i < N+1;i++) {
			tree[i] = new ArrayList<>();
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		root = -1;
		for(int i = 0 ; i <N;i++) {
			int target = Integer.parseInt(st.nextToken());
			if(target == -1) root = i;
			else tree[target].add(i);
		}
		
		term = Integer.parseInt(br.readLine());
		answer = 0;
		
		if(term == root) System.out.println(0);
		else {
			dfs(root);
			System.out.println(answer);
		}
	}
	
	static public void dfs(int idx) {
		if(tree[idx].size() == 0 || (tree[idx].size() == 1 && tree[idx].get(0) == term)) {
			answer++;
			return;
		}
		
		for(int i: tree[idx]) {
			if(i == term) continue;
			dfs(i);
		}
	}
}
