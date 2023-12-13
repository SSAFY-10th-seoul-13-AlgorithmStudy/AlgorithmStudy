package week06_DataStructure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_5430_G5_AC_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int n, size;
	static char p[];
	static ArrayDeque<Integer> dq;
	
	static void init() {
		p = new char[size];
		dq = new ArrayDeque<Integer>();
	}
	
	static void input() throws IOException{
		String str = br.readLine();
		size = str.length();
		init();
		
		for(int i = 0; i < size; i++) {
			p[i] = str.charAt(i);
		}
		
		n = Integer.parseInt(br.readLine());		
		st = new StringTokenizer(br.readLine(), "[,]");
		for(int i = 0; i < n; i++) {
			dq.add(Integer.parseInt(st.nextToken()));
		}
	}
	
	static void solve() {
		boolean flag = true;
		
		for(int i = 0; i < size; i++) {
			if(p[i] == 'R') flag = !flag;
			else {
				if(dq.isEmpty()) {
					sb.append("error\n");
					return;
				}
				if(flag) dq.pollFirst();
				else dq.pollLast();
			}
		}
		
		sb.append("[");
		if(flag) {
			if(!dq.isEmpty()) sb.append(dq.pollFirst());
			while(!dq.isEmpty()) {
				sb.append(",").append(dq.pollFirst());
			}
		}
		else {
			if(!dq.isEmpty()) sb.append(dq.pollLast());
			while(!dq.isEmpty()) {
				sb.append(",").append(dq.pollLast());
			}
		}
		sb.append("]\n");
	}
	
	public static void main(String[] args) throws IOException{
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 0; test_case < T; test_case++) {
			input();
			solve();
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
