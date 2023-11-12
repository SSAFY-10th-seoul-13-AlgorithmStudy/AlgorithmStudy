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
import java.util.HashSet;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_20366_G3_같이눈사람만들래_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st; 
    static int N, snow[];
    
    static class Snowman implements Comparable<Snowman> {
    	int a, b, size;

		public Snowman(int a, int b, int size) {
			super();
			this.a = a;
			this.b = b;
			this.size = size;
		}

		@Override
		public int compareTo(Snowman o) {			
			return this.size - o.size;
		}
    }
    
    static void init() {
    	snow = new int[N];
    }
    
    static void input() throws IOException{
    	N = Integer.parseInt(br.readLine());
    	init();
    	st = new StringTokenizer(br.readLine());
    	for(int i = 0; i < N; i++) {
    		snow[i] = Integer.parseInt(st.nextToken());
    	}
    }    
    
    static void solve() {
    	List<Snowman> snowmans = new ArrayList<Snowman>(N * N);
    	
    	for(int i = 0; i < N - 1; i++) {
    		for(int j = i + 1; j < N; j++) {
    			snowmans.add(new Snowman(i, j, snow[i] + snow[j]));
    		}
    	} // 가능한 눈사람 만들기 N ^ 2
    	
    	Collections.sort(snowmans); // N * logN
    	
    	int size = snowmans.size();
    	int min = Integer.MAX_VALUE;
    	for (int i = 1; i < size; i++) {
    		Snowman snow1 =  snowmans.get(i);
    		Snowman snow2 =  snowmans.get(i - 1);
    		if(snow1.a == snow2.a || snow1.a == snow2.b || snow1.b == snow2.a || snow1.b == snow2.b)
    			continue;
    		min = Math.min(min, snow1.size - snow2.size);
			if(min == 0) {
				sb.append(0);
				return;
			}
		}
    	sb.append(min);
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
