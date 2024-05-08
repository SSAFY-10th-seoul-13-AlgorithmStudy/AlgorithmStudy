package week23_UnionFind;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_16562_G4_친구비_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, M, k, A[], parents[];
	
	static void init() {
		A = new int[N + 1];
		parents = new int[N + 1];
		for(int i = 1, end = N + 1; i < end; i++) {
			parents[i] = i;
		}
	}
    
    static void input() throws IOException {
    	st = new StringTokenizer(br.readLine());
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	k = Integer.parseInt(st.nextToken());    	
    	init();
    	st = new StringTokenizer(br.readLine());
    	for(int i = 1, end = N + 1; i < end; i++) {
    		A[i] = Integer.parseInt(st.nextToken());
    	}
    	
    	for(int i = 0; i < M; i++) {
    		st = new StringTokenizer(br.readLine());
    		int v = Integer.parseInt(st.nextToken());
    		int w = Integer.parseInt(st.nextToken());
    		// 병합 되는 친구비를 더 저렴한 쪽으로 기록
    		A[find(v)] = Math.min(A[find(v)], A[find(w)]);
    		union(v, w);
    	}
    }
    
    static void solve(){
    	int result = 0;
    	A[0] = 10_000_001;
    	for(int i = 1, end = N + 1; i < end; i++) {
    		result += union(i, 0); // find(i)의 친구비 : i 그룹에서 가장 저렴한 친구비
    	}
    	
    	if(result > k) sb.append("Oh no");
    	else sb.append(result);
    }
    
    static int union(int a, int b) {
    	int aRoot = find(a);    	
    	int bRoot = find(b);
    	    	
    	if(aRoot == bRoot) return 0;
    	parents[bRoot] = aRoot;    	
    	return A[aRoot];
    }
    
    static int find(int v) {
    	if(v == parents[v]) return v;
    	return parents[v] = find(parents[v]);
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
