package week51_UnionFind;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_20040_G4_사이클게임_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int p[], N, M;
	
	static void init() {
		p = new int[N];
		for(int i = 0; i < N; i++) {
			p[i] = i;
		}
	}
	
	static void input_solve() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		init();
		
		int result = 0;
		for(int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(!union(a, b)) {
				result = i;
				break;
			}
		}
		
		sb.append(result);
	}	
	
	static int find(int a){
		if(p[a] == a) return a;
		return p[a] = find(p[a]);
	}

	static boolean union(int a, int b){
		int pa = find(a);
		int pb = find(b);

		if(pa == pb) return false;
		p[pb] = pa;

		return true;
	}
	
	public static void main(String[] args) throws IOException {
		input_solve();

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
