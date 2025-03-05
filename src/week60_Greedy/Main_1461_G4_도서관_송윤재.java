package week60_Greedy;

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

public class Main_1461_G4_도서관_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, M;
	static List<Integer> left, right;
	
	static void init() {
		left = new ArrayList<Integer>();
		right = new ArrayList<Integer>();
	}
	
	static void input() throws IOException{
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		init();
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			int in = Integer.parseInt(st.nextToken());
			if(in < 0) 
				left.add(-in);
			else
				right.add(in);
		}
	}
	
	static void solve() {
		Collections.sort(left);
		Collections.sort(right);
		int step = 0;
		int l_size = left.size();
		int r_size = right.size();
		
		step += check(left, l_size);
		step += check(right, r_size);
		
		int last = 0;
		
		if(left.size() == 0)
			last = right.get(r_size - 1);
		else if(right.size() == 0) 
			last = left.get(l_size - 1);
		else
			last = Math.max(right.get(r_size - 1), left.get(l_size - 1));
		
		sb.append(step - last);
	}
	
	static int check(List<Integer> list, int size) {
		int ret = 0;
		int idx = size - 1;
		while(idx >= 0) {
			ret += 2 * list.get(idx);
			if(idx < M)
				break;
			idx -= M;
		}
		
		return ret;
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
