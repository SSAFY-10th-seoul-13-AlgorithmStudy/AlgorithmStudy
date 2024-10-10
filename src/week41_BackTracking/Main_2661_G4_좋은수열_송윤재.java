package week41_BackTracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main_2661_G4_좋은수열_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int result[], N;
	static boolean flag;
	
	static void init() {
		result = new int[N];
	}
		
	static void input() throws IOException {
		N = Integer.parseInt(br.readLine());
	}

	static void solve() {
		dfs(0, new int[N]);
		for(int i = 0; i < N; i++) {
			sb.append(result[i]);
		}
	}
	
	static void dfs(int depth, int arr[]) {
		if(flag) return;
		if(depth == N) {
			result = arr.clone();
			flag = true;
			return;
		}
		
		for(int next = 1; next < 4; next++) {
			if(isGood(depth - 1, arr, next)) {
				arr[depth] = next;
				dfs(depth + 1, arr);
				arr[depth] = 0;
			}
		}
	}
	
	static boolean isGood(int index, int arr[], int key) {		
		ArrayDeque<Integer> list = new ArrayDeque<>();
		ArrayDeque<Integer> temp = new ArrayDeque<>();
		list.add(key);
		
		while(index >= 0) {
			temp.add(arr[index]);
			if(list.size() == temp.size()) {
				if(list.toString().equals(temp.toString())) {
					return false;
				} else {
					list.add(temp.pollFirst());
				}
			}
			index--;
		}
		
		return true;
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
