package week61_BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_16437_G3_양구출작전_신문영 {
	static List<Integer>[] adjList; 
	static long[] islands;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		adjList = new ArrayList[n+1];
		for(int i=1; i<n+1; i++) {
			adjList[i] = new ArrayList<>();
		}
		islands = new long[n+1];
		StringTokenizer st;
		for(int i=2; i<n+1; i++) {
			st = new StringTokenizer(br.readLine());
			
			char c = st.nextToken().charAt(0);
			int a = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			
			adjList[p].add(i);
			islands[i] = a;
			if(c=='W') {
				islands[i] *= -1;
			}
		}
		
		dfs(1,-1);
		System.out.println(islands[1]);
	}
	
	static void dfs(int idx, int parent) {
		for(int next : adjList[idx]) {
			dfs(next, idx);
		}
		
		if(parent != -1) {
			if(islands[idx]>0) {
				islands[parent] += islands[idx];
			}
		}
	}
}
