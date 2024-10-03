package week40_TwoPointer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_13144_G4_ListOfUniqueNumbers_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, arr[];
	
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
	}

	static void solve() {
		int left = 0;
		long result = 0;
		Set<Integer> set = new HashSet<>();
		for(int right = 0; right < N; right++) {
			while(set.contains(arr[right])) {
				set.remove(arr[left]);
				result += right - left++;
			}
			set.add(arr[right]);
		}
		int remain = set.size();
		for(int i = 1; i <= remain; i++) {
			result += i;
		}
		sb.append(result);
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
