package week23_UnionFind;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_13306_P4_트리_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, Q, parents[], ancestor[];
	static List<int[]> qList;
	
	static void init() {
		parents = new int[N + 1]; // 바로 윗 부모
		ancestor = new int[N + 1]; // 조상
		for(int i = 1; i <= N; i++) {
			ancestor[i] = i;
		}
		qList = new ArrayList<>();
	}
    
    static void input() throws IOException {
    	st = new StringTokenizer(br.readLine());
    	N = Integer.parseInt(st.nextToken());
    	Q = Integer.parseInt(st.nextToken());
    	init();
    	for(int i = 2; i <= N; i++) {
    		parents[i] = Integer.parseInt(br.readLine());    		
    	}
    	
    	for(int i = 0, end = N - 1 + Q; i < end; i++) {
    		st = new StringTokenizer(br.readLine());
    		int x = Integer.parseInt(st.nextToken());
    		if(x == 0) {
    			int b = Integer.parseInt(st.nextToken());
    			qList.add(new int[] {x, b});
    		} else {
    			int c = Integer.parseInt(st.nextToken());
    			int d = Integer.parseInt(st.nextToken());
    			qList.add(new int[] {x, c, d});
    		}
    	}
    }
    
    static void solve(){
    	/*
    	 * 문제에서 원하는 것은 하나의 union이던 트리에서 특정 edge를 지우고 서로 다른 두 노드가 서로소인지 판단하는 문제
    	 * edge를 지우는 행위가 N-1번. 곧 모든 edge를 지울 때 까지 이뤄짐
    	 * 
    	 * 순서를 뒤집어서 생각하면 모든 간선이 끊어진 상태에서 edge를 이어주고,
    	 * 중간 중간 서로소인지 판단한다면 기존에 알던 union find 문제와 유사하게 접근 할 수 있음
    	 */
    	
    	Stack<Boolean> stack = new Stack<>(); 
    	for(int i = qList.size() - 1; i >= 0; i--) { // 역순으로 쿼리 진행
    		int[] cur = qList.get(i);
    		if(cur[0] == 0) {
    			union(cur[1], parents[cur[1]]); // 바로 윗 부모와 연결
    		} else {
    			stack.push(find(cur[1]) == find(cur[2])); // 서로소인지 판단
    		}    		
    	}
    	
    	while(!stack.isEmpty()) { // 역순으로 진행했으므로 stack에서 꺼내는 순서가 곧 올바른 순서
    		sb.append(stack.pop() ? "YES" : "NO").append("\n");
    	}
    }
    
    static boolean union(int a, int b) {
    	int aRoot = find(a);
    	int bRoot = find(b);
    	
    	if(aRoot == bRoot) return false;
    	ancestor[bRoot] = aRoot;
    	return true;
    }
    
    static int find(int v) {
    	if(v == ancestor[v]) return v;
    	return ancestor[v] = find(ancestor[v]);
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
