package week03;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_2263_G1_트리의순회_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st; 
    static int N, inOrder[], postOrder[];
    
    static void init() {
    	inOrder = new int[N];
    	postOrder = new int[N];
    }
    
    static void input() throws IOException{
    	N = Integer.parseInt(br.readLine());
    	init();
    	
    	st = new StringTokenizer(br.readLine());
    	for(int i = 0; i < N; i++) {
    		inOrder[i] = Integer.parseInt(st.nextToken());
    	}
    	st = new StringTokenizer(br.readLine());
    	for(int i = 0; i < N; i++) {
    		postOrder[i] = Integer.parseInt(st.nextToken());
    	}
    }   
    
    static void solve() {
    	makePreOrder(0, N - 1, 0, N - 1);
    }
    
    static void makePreOrder(int in_start, int in_end, int post_start, int post_end) {
    	if(in_start > in_end || post_start > post_end) return;
    	
    	int root = postOrder[post_end];
    	sb.append(root).append(" ");    	
    	
    	int idx = in_start;
    	
    	while(inOrder[idx] != root && idx <= in_end) idx++;
    	
    	makePreOrder(in_start, idx - 1, post_start, post_start + idx - in_start - 1);
    	makePreOrder(idx + 1, in_end, post_start + idx - in_start, post_end - 1);
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
