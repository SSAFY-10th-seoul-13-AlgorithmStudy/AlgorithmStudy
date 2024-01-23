package week11_BinarySearch;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main_2110_G4_공유기설치_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, C, home[];
	
	static void init() {
		home = new int[N];
	}
	
	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		init();
		
		for(int i = 0; i < N; i++) 
			home[i] = Integer.parseInt(br.readLine());
	}

	static void solve() {
		Arrays.sort(home);
		int result = 0;
		int start = 1;
		int end = home[N - 1] - home[0];
		
		if(C == 2) {
			sb.append(end);
			return;
		}
		
		
		while(start < end) {
			int mid = (start + end) / 2;
			int cnt = 1;
			int cur = home[0];
			for (int i = 0; i < N; i++) {
                if (home[i] - cur >= mid) {
                    cnt++;
                    cur = home[i];
                }
            }

            if (cnt >= C) {
            	result = mid;
            	start = mid + 1;
            } else {
            	end = mid;
            }
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
