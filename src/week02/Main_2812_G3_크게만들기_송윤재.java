package week02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_2812_G3_크게만들기_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st; 
    static int N, K, nums[];
    
    static void init() {
    	nums = new int[N];
    }
    
    static void input() throws IOException{
    	st = new StringTokenizer(br.readLine());
    	N = Integer.parseInt(st.nextToken());
    	K = Integer.parseInt(st.nextToken());
    	
    	init();
    	String str = br.readLine();
    	for(int i = 0; i < N; i++) {
    		nums[i] = str.charAt(i) - '0';
    	}
    }   
    
    static void solve() {
    	Stack<Integer> stack = new Stack<Integer>();
    	stack.push(nums[0]);
    	for(int i = 1; i < N; i++) {
    		while(!stack.isEmpty() && stack.peek() < nums[i] && K > 0) {
    			stack.pop();
    			K--;
    		}
    		stack.push(nums[i]);
    	}
    	while(!stack.isEmpty() && K > 0) {
    		stack.pop();
    		K--;
    	}
    	
    	Stack<Integer> result = new Stack<Integer>();
    	while(!stack.isEmpty()) result.push(stack.pop());
    	while(!result.isEmpty()) sb.append(result.pop());
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
