package week23_UnionFind;
 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1717_G5_집합의표현_김희연 {
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		parent = new int[n+1];

		for(int i=1; i<=n; i++){
			parent[i] = i;
		}

		for(int i=0; i<m; i++){
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			if(num == 0){
				union(a, b);
			} else{
				if(isEqual(a, b))
					sb.append("YES").append("\n");
				else
					sb.append("NO").append("\n");
			}
		}

		System.out.println(sb);
	}

	public static void union(int a, int b) {
		a = find(a);
		b = find(b);

		if (a != b)
			parent[b] = a;
	}

	public static int find(int v) {
		if (parent[v] != v)
			parent[v] = find(parent[v]);
		return parent[v];
	}

	public static boolean isEqual(int a, int b) {
		a = find(a);
		b = find(b);

		if (a == b)
			return true;
		return false;
	}
}