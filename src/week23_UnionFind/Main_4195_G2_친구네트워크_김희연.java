package week23_UnionFind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_4195_G2_친구네트워크_김희연 {
	static int[] parent;
	static int[] level;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for(int t=0; t<T; t++){
			int f = Integer.parseInt(br.readLine());

			parent = new int[f*2];
			level = new int[f*2];

			for(int i=0; i<f*2; i++){
				parent[i] = i;
				level[i] = 1;
			}

			int idx = 0;
			Map<String, Integer> map = new HashMap<>();

			for(int i=0; i<f; i++){
				st = new StringTokenizer(br.readLine());
				String a = st.nextToken();
				String b = st.nextToken();

				if(!map.containsKey(a)){
					map.put(a, idx++);
				}

				if(!map.containsKey(b)){
					map.put(b, idx++);
				}

				sb.append(union(map.get(a), map.get(b)) + "\n");
			}
		}

		System.out.println(sb);
	}

	public static int union(int a, int b) {
		a = find(a);
		b = find(b);
		if (a != b) {
			parent[b] = a;
			level[a] += level[b];

			level[b] = 1;
		}

		return level[a];
	}

	public static int find(int v) {
		if (parent[v] != v)
			parent[v] = find(parent[v]);
		return parent[v];
	}
}