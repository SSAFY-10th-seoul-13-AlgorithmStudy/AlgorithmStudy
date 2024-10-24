package week43_MST;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_10423_G3_전기가부족해_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, M, K, parent[];
	static Set<Integer> stations;
	static PriorityQueue<Cable> cables;
	
	static class Cable implements Comparable<Cable>{
		int u, v, w;
		
		Cable(int u, int v, int w){
			this.u = u;
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Cable o) {
			return Integer.compare(this.w, o.w);
		}
	}
	
	static void init() {
		parent = new int[N + 1];
		for(int i = 1; i <= N; i++) {
			parent[i] = i;
		}
		stations = new HashSet<Integer>();
		cables = new PriorityQueue<>();
	}
			
	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		init();
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < K; i++) {
			stations.add(Integer.parseInt(st.nextToken()));
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			cables.offer(new Cable(u, v, w));
		}
	}

	static void solve() {
		int cost = 0;
		while(!cables.isEmpty()) {
			Cable cur = cables.poll();
			if(union(cur.u, cur.v))
				cost += cur.w;
		}
		sb.append(cost);
	}
	
	static int find(int a) {
		if(a == parent[a]) return a;
		return parent[a] = find(parent[a]);
	}
	
	static boolean union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		if(pa == pb) return false;

		// 둘 다 연결되어 있으면? -> 연결 안함
		if(stations.contains(pa) && stations.contains(pb))
			return false;

		// 둘 중 하나가 발전소에 연결되어 있으면? -> 연결된 쪽으로 연결
		else if(stations.contains(pa)){
			parent[pb] = pa;
		}
		else if(stations.contains(pb)){
			parent[pa] = pb;
		}

		// 둘 다 연결 안되어있으면? -> 그냥 연결
		else {
			parent[pa] = pb;
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		input();
		solve();

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
