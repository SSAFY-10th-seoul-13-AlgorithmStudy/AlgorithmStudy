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

public class Main_1451_G4_직사각형으로나누기_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st; 
    static int N, M;
    static long max, prefix_sum[][];
    
    static void init() {
    	prefix_sum = new long[N + 1][M + 1];
    }
    
    static void input() throws IOException{
    	st = new StringTokenizer(br.readLine());
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	init();
    	for(int i = 1; i < N + 1; i++) {
    		String str = br.readLine();
    		for(int j = 1; j < M + 1; j++) {
    			prefix_sum[i][j] = prefix_sum[i - 1][j] + prefix_sum[i][j - 1] 
    					- prefix_sum[i - 1][j - 1] + (long)(str.charAt(j - 1) - '0'); // i, j 까지의 직사각형 누적합 
    		}
    	}
    }   
    
    static void solve() {
    	divide(0, 0, N, M, 0, 1);
    	sb.append(max);
    }
    
    static void divide(int sr, int sc, int er, int ec, int depth, long temp) {
    	if(depth == 3) {
    		max = Math.max(max, temp);
    		return;
    	}
    	
    	if(sr == er || sc == ec) return;
    	
    	long all = prefix_sum[er][ec] - prefix_sum[er][sc] - prefix_sum[sr][ec] + prefix_sum[sr][sc];
    	
    	for(int i = sr + 1; i <= er; i++) { // 세로로 나누는 경우
    		long cur = prefix_sum[i][ec] - prefix_sum[i][sc] - prefix_sum[sr][ec] + prefix_sum[sr][sc];
    		divide(i, sc, er, ec, depth + 1, temp * cur); // 윗 부분
    		divide(sr, sc, i, ec, depth + 1, temp * (all - cur)); // 아래 부분
    	}
    	
    	for(int i = sc + 1; i <= ec; i++) { // 가로로 나누는 경우
    		long cur = prefix_sum[er][i] - prefix_sum[sr][i] - prefix_sum[er][sc] + prefix_sum[sr][sc];
    		divide(sr, i, er, ec, depth + 1, temp * cur); // 왼쪽 부분
    		divide(sr, sc, er, i, depth + 1, temp * (all - cur)); // 오른쪽 부분
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
