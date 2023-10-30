package week01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1756_G5_피자굽기_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st; 
    static int D, N, oven[], pizza[];
    
    static void init() {
    	oven = new int[D + 1];
    	pizza = new int[N];
    }
    
    static void input() throws IOException{
    	st = new StringTokenizer(br.readLine());
    	D = Integer.parseInt(st.nextToken());
    	N = Integer.parseInt(st.nextToken());
    	init();
    	int min = Integer.MAX_VALUE;
    	st = new StringTokenizer(br.readLine());
    	for(int i = 1; i < D + 1; i++) {
    		int temp = Integer.parseInt(st.nextToken());
    		min = Math.min(min, temp);
    		oven[i] = min;
    	}
    	
    	st = new StringTokenizer(br.readLine());
    	for(int i = 0; i < N; i++) {
    		pizza[i] = Integer.parseInt(st.nextToken());
    	}
    }   
    
    static void solve() {
    	int result = D + 1;
    	top:
    	for(int p : pizza) {
    		result--;
    		while(oven[result] < p) {
    			if(result == 0) break top;
    			result--;
    		}
    	}
    	sb.append(result);
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
