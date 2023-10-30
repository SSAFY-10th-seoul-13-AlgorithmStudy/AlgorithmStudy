package week01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1766_G2_문제집_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st; 
    static int N, M, indegree[];
    static List<Integer>[] adjList;
    
    static void init() {
    	indegree = new int[N + 1];
    	adjList = new List[N + 1];
    	for(int i = 1; i <= N; i++) {
    		adjList[i] = new ArrayList<>();
    	}
    }
    
    static void input() throws IOException{
    	st = new StringTokenizer(br.readLine());
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	init();
    	
    	for(int i = 0; i < M; i++) {
    		st = new StringTokenizer(br.readLine());
    		int from = Integer.parseInt(st.nextToken());
    		int to = Integer.parseInt(st.nextToken());
    		indegree[to]++;
    		adjList[from].add(to);
    	}
    }   
    
    static void solve() {
    	PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
    	for (int i = 1; i <=N; ++i) {
			if(indegree[i]==0) pq.offer(i);
		}
    	
    	while(!pq.isEmpty()) {
    		int cur = pq.poll();
    		sb.append(cur).append(" ");
    		for(int next : adjList[cur]) {
    			if(--indegree[next] == 0) pq.offer(next);
    		}
    	}
    }
    
	public static void main(String[] args) throws IOException{
		input();
		solve();
        
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
	}
}
