package week10_BackTracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_12100_G2_2048Easy_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, board[][], max;
	
	static void init() {
		board = new int[N][N];
	}

	static void input() throws IOException {
		N = Integer.parseInt(br.readLine());
		
		init();
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) 
				board[i][j] = Integer.parseInt(st.nextToken());
		}
	}
	
	static void solve() {
		int[][] cur = new int[N][N];
		for(int i = 0; i < N; i++) 
			for(int j = 0; j < N; j++)
				cur[i][j] = board[i][j]; // deep copy
		dfs(0, cur);
		sb.append(max);
	}
	
	static void dfs(int depth, int[][] cur) {
		if(depth == 5) {
			for(int i = 0; i < N; i++) 
				for(int j = 0; j < N; j++)  
					max = Math.max(max, cur[i][j]);
			return;
		}

		int[][] next;
		
		// left
		next = new int[N][N];
		for(int i = 0; i < N; i++) { 
			int index = 0; // 새로운 숫자가 들어갈 인덱스
			for(int j = 0; j < N; j++) {
				if(cur[i][j] == 0) continue; // 탐색하는 공간이 비어있으면 continue
				
				if(next[i][index] == 0) // 해당 위치가 비어있으면 넣어줌. index는 변하지 않음
					next[i][index] = cur[i][j];
				else if(next[i][index] == cur[i][j])
					next[i][index++] = cur[i][j] * 2; // 해당 위치와 같은 숫자면 합쳐줌. index를 이동시키면 중복으로 더해지지 않음
				else next[i][++index] = cur[i][j]; // 새로운 숫자를 쌓아야되는 상황. 다음 index에 새로운 숫자를 넣어줌
			}
		}
		dfs(depth + 1, next);
		
		// right
		next = new int[N][N];
		for(int i = 0; i < N; i++) { 
			int index = N - 1;
			for(int j = N - 1; j >= 0; j--) {
				if(cur[i][j] == 0) continue;
				
				if(next[i][index] == 0)
					next[i][index] = cur[i][j];
				else if(next[i][index] == cur[i][j])
					next[i][index--] = cur[i][j] * 2;
				else next[i][--index] = cur[i][j];
			}
		}
		
		dfs(depth + 1, next);
		
		// up
		next = new int[N][N];
		for(int j = 0; j < N; j++) { 
			int index = 0;
			for(int i = 0; i < N; i++) {
				if(cur[i][j] == 0) continue;
				
				if(next[index][j] == 0)
					next[index][j] = cur[i][j];
				else if(next[index][j] == cur[i][j])
					next[index++][j] = cur[i][j] * 2;
				else next[++index][j] = cur[i][j];
			}
		}
		dfs(depth + 1, next);
		
		// down
		next = new int[N][N];
		for(int j = 0; j < N; j++) { 
			int index = N - 1;
			for(int i = N - 1; i >= 0; i--) {
				if(cur[i][j] == 0) continue;
				
				if(next[index][j] == 0)
					next[index][j] = cur[i][j];
				else if(next[index][j] == cur[i][j])
					next[index--][j] = cur[i][j] * 2;
				else next[--index][j] = cur[i][j];
			}
		}
		dfs(depth + 1, next);
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
