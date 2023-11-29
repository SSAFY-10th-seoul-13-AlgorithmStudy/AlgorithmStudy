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

public class Main_2571_G3_색종이3_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N, map[][];
//    static List<int[]> s_point;
    
    static void init() {
    	map = new int[101][101];
    	for(int i = 1; i < 101; i++) {
    		for(int j = 1; j < 101; j++) {
    			map[i][j] = -10001; // 만들어 질 수 있는 최대 값은 10000이므로 무조건 음수로 만들기 위한 값
    		}
    	}
//    	s_point = new ArrayList<int[]>(N);
    }
    
    static void input() throws IOException{
    	N = Integer.parseInt(br.readLine());
    	init();
    	for(int i = 0; i < N; i++) {
    		st = new StringTokenizer(br.readLine());
    		int a = Integer.parseInt(st.nextToken());
    		int b = Integer.parseInt(st.nextToken());
//    		s_point.add(new int[] {a, b});
    		for(int j = 0; j < 10; j++) {
    			Arrays.fill(map[b + j], a, a + 10, 1);
    		}
    	}
    }   
    
    static void solve() {
    	int prefix_sum[][] = new int[101][101];
    	for(int i = 1; i < 101; i++) {
    		for(int j = 1; j < 101; j++) {
    			prefix_sum[i][j] = prefix_sum[i - 1][j] + prefix_sum[i][j - 1] 
    					- prefix_sum[i - 1][j - 1]  + map[i][j];
    		}
    	}
    	
    	int max = 0;    	
    	
    	/**
    	 * 최대 직사각형이 나오는 경우는 주어진 시작점과 끝점을 기준으로 생성 될 수 있다고 생각함
    	 * 결론 : 색종이가 많이 겹치게 되면 시작점이 아니더라도 최대 직사각형이 될 수 있음
    	 */
    	/*
    	for(int[] point1 : s_point) {
    		for(int[] point2 : s_point) {
    			if(point1[1] >= point2[1] + 10 || point1[0] >= point2[0] + 10) continue;
    			int cur = prefix_sum[point2[1] + 9][point2[0] + 9] - prefix_sum[point1[1] - 1][point2[0] + 9]
    					- prefix_sum[point2[1] + 9][point1[0] - 1] + prefix_sum[point1[1] - 1][point1[0] - 1];
    			max = Math.max(max, cur);
    		}
    	}
    	*/
    	
    	 for (int sr = 1; sr < 101; sr++) {
             for (int sc = 1; sc < 101; sc++) {	
                 for (int er = sr + 1; er < 101; er++) {
                     for (int ec = sc + 1; ec < 101; ec++) {	
                         int cur = prefix_sum[er][ec] - prefix_sum[sr - 1][ec] - prefix_sum[er][sc - 1] + prefix_sum[sr - 1][sc - 1];
                         if (cur < 0) break; // 구멍을 만난 경우
                         max = Math.max(cur, max);
                     }
                 }
             }
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
