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
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_2295_G4_세수의합_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st; 
    static int N, d, U[];
    
    static void init() {
    	U = new int[N];
    }
    
    static void input() throws IOException{
    	N = Integer.parseInt(br.readLine());
    	init();
    	for(int i = 0; i < N; i++) {
    		U[i] = Integer.parseInt(br.readLine());
    	}
    }   
    
    static void solve() {
    	Arrays.sort(U);
    	
    	for(int k = N - 1; k >= 0; k--) {
    		for(int x = N - 1; x >= 0; x--) {
    			if(U[k] < U[x]) continue; // 백트래킹
    			for(int y = N - 1; y >= 0; y--) {
    				if(U[k] < U[x] + U[y]) continue; // 백트래킹
    				for(int z = N - 1; z >= 0; z--) {
//    					System.out.println(x + " " + y + " " + z + " " + k);
    					if(U[k] == U[x] + U[y] + U[z]) {
    						sb.append(U[k]);
    						return;
    					}
    				}
    			}
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
