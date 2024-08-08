package week34_DynamicProgramming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_2169_G2_로봇조종하기_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, M, map[][], dp[][], temp[][];
	static int dr[] = {1, 0, 0};
	static int dc[] = {0, 1, -1};
	
	static void init() {
		map = new int[N + 1][M + 1];
		dp = new int[N + 1][M + 1];
		temp = new int[2][M + 1];
	}
		
	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		init();
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());			
			for(int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}

	static void solve() {		
		for(int i = 1; i <= M; i++) {
			dp[1][i] = dp[1][i - 1] + map[1][i];
		}
		
		for(int i = 2; i <= N; i++) {
            temp[0][1] = dp[i - 1][1] + map[i][1];
            for(int j = 2; j <= M; j++){
                temp[0][j] = Math.max(temp[0][j - 1], dp[i - 1][j]) + map[i][j];
            }
            
            temp[1][M] = dp[i - 1][M] + map[i][M];
            for(int j = M - 1; j > 0; j--) {
            	temp[1][j] = Math.max(temp[1][j + 1], dp[i - 1][j]) + map[i][j];
            }
            
            for(int j = 1; j <= M; j++) {
            	dp[i][j] = Math.max(temp[0][j], temp[1][j]);
            }
		}
		sb.append(dp[N][M]);
	}
	
	public static void main(String[] args) throws IOException {		
		input();
		solve();

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
