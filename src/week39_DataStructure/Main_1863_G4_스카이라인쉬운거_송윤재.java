package week39_DataStructure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_1863_G4_스카이라인쉬운거_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;	
		
	/*
	 * 높이가 바뀐다 => 올라가거나 내려간다.
	 * 올라간다 => 무조건 건물 하나 추가
	 * 내려간다 => 이미 만들어진 건물인지 check => 스택을 써서 쌓아올린 애들을 확인
	 * 스택에 푸시 할 때 마다 cnt++
	 */
	static void input_solve() throws IOException {
		Stack<Integer> stack = new Stack<Integer>();
		int cnt = 0;
		int n = Integer.parseInt(br.readLine());		
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			if(y == 0) {
				stack.clear();
			} else if(stack.isEmpty() || stack.peek() < y) {
				stack.push(y);
				cnt++;
			} else {
				while(!stack.isEmpty() && stack.peek() > y) {
					stack.pop();
				}
				if(stack.isEmpty() || stack.peek() < y) {
					stack.push(y);
					cnt++;
				}
			}
		}
		sb.append(cnt);
	}
	
	public static void main(String[] args) throws IOException {		
		input_solve();

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
