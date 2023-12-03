package week04;

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
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_14476_G2_최대공약수하나빼기_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st; 
    static int N, arr[], lgcd[], rgcd[];
    
    static void init() {
    	arr = new int[N + 1];
    	lgcd = new int[N + 2];
    	rgcd = new int[N + 2];
    }
    
    static void input() throws IOException{
    	N = Integer.parseInt(br.readLine());
    	init();
    	st = new StringTokenizer(br.readLine());
    	for(int i = 1; i < N + 1; i++) {
    		arr[i] = Integer.parseInt(st.nextToken());
    	}
    }   
    
    static void solve() {
    	lgcd[1] = arr[1];
    	for(int i = 2; i < N + 1; i++) {
    		lgcd[i] = gcd(arr[i], lgcd[i - 1]);
    	}
    	rgcd[N] = arr[N];
    	for(int i = N - 1; i > 0; i--) {
    		rgcd[i] = gcd(arr[i], rgcd[i + 1]);
    	}
    	
    	int temp_gcd;
    	int max = 0;
    	int temp = -1;
    	
    	for(int i = 1; i < N + 1; i++) {
    		temp_gcd = gcd(lgcd[i - 1], rgcd[i + 1]);
    		if(max < temp_gcd && arr[i] % temp_gcd != 0) {
    			max = temp_gcd;
    			temp = arr[i];
    		}
    	}
    	
    	if(max == 0) sb.append(-1);
    	else sb.append(max).append(" ").append(temp);
    }
    
    static int gcd(int a, int b) { // 유클리드 호제법
    	while(a != 0) {
    		int r = b % a;
    		b = a;
    		a = r;
    	}
    	return b;
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
