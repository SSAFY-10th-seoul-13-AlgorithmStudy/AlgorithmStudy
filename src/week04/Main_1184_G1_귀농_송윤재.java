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

public class Main_1184_G1_귀농_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N, map[][], prefix_sum[][], count;
    
    static void init() {
    	map = new int[N + 1][N + 1];
    	prefix_sum = new int[N + 1][N + 1];
    }
    
    static void input() throws IOException{
    	N = Integer.parseInt(br.readLine());
    	init();
    	for(int i = 1; i < N + 1; i++) {
    		st = new StringTokenizer(br.readLine());
    		for(int j = 1; j < N + 1; j++) {
    			map[i][j] = Integer.parseInt(st.nextToken());
    		}
    	}
    }   
    
    static void solve() {
    	for(int i = 1; i < N + 1; i++) {
    		for(int j = 1; j < N + 1; j++) {
    			prefix_sum[i][j] = prefix_sum[i - 1][j] + prefix_sum[i][j - 1] 
    					- prefix_sum[i - 1][j - 1]  + map[i][j];
    		}
    	}
    	for(int sr = 1; sr < N + 1; sr++) {
    		for(int sc = 1; sc < N + 1; sc++) {
    			for(int er = sr; er < N + 1; er++) {
    				for(int ec = sc; ec < N + 1; ec++) {
    					int cur = prefix_sum[er][ec] - prefix_sum[sr - 1][ec] - prefix_sum[er][sc - 1] + prefix_sum[sr - 1][sc - 1];
//    					System.out.println("sr : " + sr + " sc : " + sc + " er : " + er + " ec : " + ec);
//    					System.out.println("cur : " + cur);
    					left_check(er, sc, cur);
    					right_check(er, ec, cur);
    				}
    			}
    		}
    	}
    	sb.append(count);
    }
    
    static void left_check(int sr, int ec, int target) {
    	for(int i = sr + 1; i < N + 1; i++) {
    		for(int j = ec - 1; j > 0; j--) {
    			int cur = prefix_sum[i][ec - 1] - prefix_sum[sr][ec - 1] - prefix_sum[i][j - 1] + prefix_sum[sr][j  - 1];
				if(target == cur) count++;
    		}
    	}
    }
    
    static void right_check(int sr, int sc, int target) {
    	for(int i = sr + 1; i < N + 1; i++) {
    		for(int j = sc + 1; j < N + 1; j++) {
    			int cur = prefix_sum[i][j] - prefix_sum[sr][j] - prefix_sum[i][sc] + prefix_sum[sr][sc];
				if(target == cur) count++;
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
