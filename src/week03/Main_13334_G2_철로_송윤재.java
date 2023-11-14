package week03;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_13334_G2_철로_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st; 
    static int N, people[][], d;
    
    static void init() {
    	people = new int[N][2];
    }
    
    static void input() throws IOException{
    	N = Integer.parseInt(br.readLine());
    	init();
    	
    	for(int i = 0; i < N; i++) {
    		st = new StringTokenizer(br.readLine());
    		int a = Integer.parseInt(st.nextToken());
    		int b = Integer.parseInt(st.nextToken());
    		people[i] = a < b ? new int[] {a, b} : new int[] {b, a}; 
    	}
    	d = Integer.parseInt(br.readLine());
    }   
    
    static void solve() {
    	Arrays.sort(people, (o1, o2) -> o1[1] - o2[1]);
    	PriorityQueue<Integer> pq = new PriorityQueue<>();
    	int max = 0;
    	int cnt = 0;
    	int start = Integer.MIN_VALUE;
    	int end = start + d;
    	
    	for (int[] p : people) {
			if(p[1] - p[0] > d || p[0] < start) continue;
			if(p[1] > end) {
				end = p[1];
				start = end - d;
				while(!pq.isEmpty() && pq.peek() < start) {
					pq.poll();
					cnt--;
				}
			}
			pq.add(p[0]);
			cnt++;
			max = Math.max(max, cnt);
		}
    	sb.append(max);
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
