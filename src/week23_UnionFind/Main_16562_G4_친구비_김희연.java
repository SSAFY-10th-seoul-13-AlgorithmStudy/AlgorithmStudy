package week23_UnionFind;
 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16562_G4_친구비_김희연 {
	static int[] parent;
	static int[] money;
	static boolean[] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		parent = new int[n+1];
		money = new int[n+1];
		visit = new boolean[n+1];

		for(int i=1; i<=n; i++){
			parent[i] = i;
		}

		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=n; i++){
			money[i] = Integer.parseInt(st.nextToken());
		}

		for(int i=0; i<m; i++){
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			union(a, b);
		}

		int sum = 0;

		for(int i=1; i<=n; i++){
			int rootIdx = find(i);

			if(visit[rootIdx]){
				visit[i] = true;
				continue;
			}

			sum += money[rootIdx];

			visit[rootIdx] = true;
			visit[i] = true;
		}

		if(sum > k)
			System.out.println("Oh no");
		else
			System.out.println(sum);
	}

	public static void union(int a, int b) {
		a = find(a);
		b = find(b);

		if (a != b)
			if(money[a] > money[b])
				parent[a] = b;
			else
				parent[b] = a;
	}

	public static int find(int v) {
		if (parent[v] != v)
			parent[v] = find(parent[v]);
		return parent[v];
	}
}