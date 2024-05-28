package week24_MST;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_2887_P5_행성터널_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, parents[];
	static Planet X[], Y[], Z[];
	
	static class Planet implements Comparable<Planet>{
		int num, loc;
		
		
		public Planet(int num, int loc) {
			this.num = num;
			this.loc = loc;
		}

		@Override
		public int compareTo(Planet o) {			
			return Integer.compare(loc, o.loc);
		}		
	}
	
	static class Edge implements Comparable<Edge>{
		int u, v, cost;

		public Edge(int u, int v, int cost) {
			this.u = u;
			this.v = v;
			this.cost = cost;
		}		

		@Override
		public int compareTo(Edge o) {			
			return Integer.compare(cost, o.cost);
		}		
	}
	
	static void init() {
		parents = new int[N];
		for(int i = 0; i < N; i++) {
			parents[i] = i;
		}
		X = new Planet[N];
		Y = new Planet[N];
		Z = new Planet[N];
	}
    
    static void input() throws IOException {
    	N = Integer.parseInt(br.readLine());
    	init();
    	for(int i = 0; i < N; i++) {
    		st = new StringTokenizer(br.readLine());
    		X[i] = new Planet(i, Integer.parseInt(st.nextToken()));
    		Y[i] = new Planet(i, Integer.parseInt(st.nextToken()));
    		Z[i] = new Planet(i, Integer.parseInt(st.nextToken()));
    	}
    }
    
    static void solve(){
    	int result = 0;
    	Arrays.sort(X);
    	Arrays.sort(Y);
    	Arrays.sort(Z);
    	PriorityQueue<Edge> pq = new PriorityQueue<>();
    	
    	for(int i = 0; i < N - 1; i++) {
    		pq.add(new Edge(X[i].num, X[i + 1].num, X[i + 1].loc - X[i].loc));
    		pq.add(new Edge(Y[i].num, Y[i + 1].num, Y[i + 1].loc - Y[i].loc));
    		pq.add(new Edge(Z[i].num, Z[i + 1].num, Z[i + 1].loc - Z[i].loc));
    	}
    	
    	while(!pq.isEmpty()) {
    		Edge edge = pq.poll();
    		if(union(edge.u, edge.v)) result += edge.cost;
    	}
    	sb.append(result);
    }
    
    static int find(int a) {
    	if(a == parents[a]) return a;
    	return parents[a] = find(parents[a]);
    }
    
    static boolean union(int a, int b) {
    	int pa = find(a);
    	int pb = find(b);
    	
    	if(pa == pb) return false;
    	parents[pb] = pa;
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
