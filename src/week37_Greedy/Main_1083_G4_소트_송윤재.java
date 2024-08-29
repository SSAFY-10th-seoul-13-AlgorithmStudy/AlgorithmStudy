package week37_Greedy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_1083_G4_소트_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, arr[], S;
	
	static void init() {
		arr = new int[N];
	}
		
	static void input() throws IOException {
		N = Integer.parseInt(br.readLine());
		init();
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		S = Integer.parseInt(br.readLine());
	}

	static void solve() {
		int idx = 0;
		while(S > 0 && idx < N) {
			int temp_idx = idx;
			int max = 0;
			int max_idx = 0;
			/*
			 * 최대값 찾는 과정.
			 * 찾은 최대값이 이동할 목적지(idx) 까지의 거리가 S보다 크면 안됨.
			 */
			while(temp_idx < N && temp_idx - idx <= S) {
				if(arr[temp_idx] > max) {
					max = arr[temp_idx];
					max_idx = temp_idx;					
				}
				temp_idx++;
			}
			temp_idx = max_idx;
			while(temp_idx > idx) { // 목적지까지 스왑
				swap(temp_idx--);
			}
			idx++;
		}
		
		for(int i = 0; i < N; i++) {
			sb.append(arr[i]).append(" ");
		}
	}
	
	static void swap(int idx) {
		int temp = arr[idx - 1];
		arr[idx - 1] = arr[idx];
		arr[idx] = temp;
		S--; // 스왑하면서 스왑 가능 횟수 감소
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
