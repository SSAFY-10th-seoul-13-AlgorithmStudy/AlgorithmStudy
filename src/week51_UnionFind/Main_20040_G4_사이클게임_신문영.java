package week51_UnionFind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_20040_G4_사이클게임_신문영 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[] parents = new int[n];
		for (int i = 0; i < n; i++) {
			parents[i] = i;
		}
		
		int answer = 0;
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			int parentA = find(a, parents);
			int parentB = find(b, parents);
			
			if (parentA != parentB) {
				if (parentA < parentB) parents[parentB] = parentA;
				else parents[parentA] = parentB;
			} else {
				answer = i + 1;
				break;
			}
		}
		
		System.out.println(answer);
	}
	
	public static int find(int a, int[] parents) {
		if (parents[a] == a) return a;
		return parents[a] = find(parents[a], parents);
	}
}