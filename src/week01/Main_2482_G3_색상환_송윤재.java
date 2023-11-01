package week01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_2482_G3_색상환_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st; 
    static final int mod = 1000000003;
    static int N, K, dp[][];
    
    static void init() {
    	dp = new int[N + 1][K + 1];
    }
    
    static void input() throws IOException{
    	N = Integer.parseInt(br.readLine());
    	K = Integer.parseInt(br.readLine());
    	init();
    }   
    
    static void solve() {
    	for(int i = 1; i <= N; i++) {
    		dp[i][1] = i;
    	}
    	
    	for(int i = 4; i <= N; i++) {
    		int temp = i < K ? i : K;
    		for(int j = 2; j <= temp; j++) {
    			dp[i][j] = (dp[i - 1][j] + dp[i - 2][j - 1]) % mod;
    		}
    	}
    	sb.append(dp[N][K]);
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
